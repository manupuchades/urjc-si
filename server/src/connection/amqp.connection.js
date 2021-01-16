var amqp = require('amqplib/callback_api');

var amqpchannel;

exports.connection = () => {
    amqp.connect('amqp://localhost', function (error, connection) {
        connection.createChannel(function (error, channel) {
            amqpchannel = channel;
            console.log('AMQP connection established');

            channel.consume('queue', function (message) {
                console.log('AMQP message received:' + msg);
            })
        })
    });
};

exports.send = (message) => {
    amqpchannel.sendToQueue('queue', Buffer.from(message.data));
};

process.on('exit', (code) => {
    amqpchannel.close();
    console.log(`Closing rabbitmq channel`);
});