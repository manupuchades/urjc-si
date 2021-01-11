const WebSocket = require('ws');

var wsconnection;

exports.connection = () => {
    wss.on('connection', function connection(ws, req) {
        wsconnection = ws;
        console.log('User connected');
        
        ws.on('message', function (msg) {
            console.log('Message received:' + msg);
        });
    });
};

exports.send = (message) => {
    wsconnection.send(message.data)
};