const supertest = require('supertest');
const app = require('../../src/app');
const request = supertest(app);

const { GenericContainer } = require("testcontainers");
let AWS = require('aws-sdk');
const createTable = require('../../src/db/createTable.js');

let filmData = [{ "id": "1", "title": "Captain Fantastic", "director": "Matt Ross" },
                { "id": "2", "title": "Green Book", "director": "Peter Farrelly" }];

const FILMS_URI = '/api/films/';
jest.setTimeout(1000000);

let dynamoDBContainer;

describe('Films API REST End to End Tests', () => {

    beforeEach(async () => {
        dynamoDBContainer = await new GenericContainer("amazon/dynamodb-local", "1.13.6")
            .withExposedPorts(8000)
            .start();

        AWS.config.update({
            region: process.env.AWS_REGION || 'local',
            endpoint: process.env.AWS_DYNAMO_ENDPOINT || 'http://localhost:' + dynamoDBContainer.getMappedPort(8000),
            accessKeyId: "xxxxxx", // No es necesario poner nada aquí
            secretAccessKey: "xxxxxx" // No es necesario poner nada aquí
        });

        await createTable('films');
    });

    afterEach(async () => {
        await dynamoDBContainer.stop();
    });

    test('Create new Film', async () => {
        // CREATE FILM
        const response = await request.post(FILMS_URI).send(filmData[1])
            .expect('Content-type', /json/)
            .expect(201);

        expect(response.body.id).toBeDefined();
        expect(response.body.title).toEqual(filmData[1].title);
        expect(response.body.director).toEqual(filmData[1].director);
    })

    test('Retrieve all Films', async () => {
        const emptyResponse = await request.get(FILMS_URI)
            .expect('Content-type', /json/)
            .expect(200);

        expect(emptyResponse.body.length).toBe(0);

        // Populate DB
        await request.post(FILMS_URI).send(filmData[0])
            .expect('Content-type', /json/)
            .expect(201);


        const fullResponse = await request.get(FILMS_URI)
            .expect('Content-type', /json/)
            .expect(200);

        expect(fullResponse.body.length).toBe(1);

        let [{ id, title, director }] = fullResponse.body;
        expect(title).toEqual(filmData[0].title);
        expect(director).toEqual(filmData[0].director);
    })

})
