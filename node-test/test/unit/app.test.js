const supertest = require('supertest');
const app = require('../../src/app');
const request = supertest(app);

const AWS = require('aws-sdk');
jest.mock('aws-sdk');

let filmData = [{ "id": "1", "title": "Captain Fantastic", "director": "Matt Ross" },
                { "id": "2", "title": "Green Book", "director": "Peter Farrelly" }];

const FILMS_URI = '/api/films/';

describe('Films API REST Mocked Unit Tests', () => {

    test('Create new Film', async () => {
        let film = filmData[0];

        const mockedImplementation = {
            put: (params, cb) => cb(null, film)
        }

        AWS.DynamoDB.DocumentClient.mockImplementation(() => mockedImplementation);

        const response = await request.post(FILMS_URI).send(film)
            .expect('Content-type', /json/)
            .expect(201);

        expect(response.body.id).toBeDefined();
        expect(response.body.title).toEqual(film.title);
        expect(response.body.director).toEqual(film.director);
    })

    test('Retrieve all Films', async () => {
        let films = { "Items": filmData };

        const mockedImplementation = {
            scan: (params, cb) => cb(null, films)
        }

        AWS.DynamoDB.DocumentClient.mockImplementation(() => mockedImplementation);

        const response = await request.get(FILMS_URI)
            .expect('Content-type', /json/)
            .expect(200);

        expect(response.body.length).toBe(2);
        expect(response.body).toEqual(filmData);
    })

})
