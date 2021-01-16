const WebSocket = require('ws');

const wss = new WebSocket.Server();

var wsconnection;

exports.connection = () => {
    wss.on('connection', function connection(ws, req) {
        wsconnection = ws;
        console.log('User connected');
        
        ws.on('message', function (msg) {
            console.log('WebSocket message received:' + msg);
        });
    });
};

exports.send = (message) => {
    wsconnection.send(message.data)
};