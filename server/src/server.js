var express = require('express');
var app = express();

var bodyParser = require('body-parser');

var apiRoutes = require("./routes/eolicplant.routes");
var expressWs = require('express-ws')(app);

var socket = require('./connection/ws.connection').connection;

const db = require("./connection/db.connection");
db.sequelize.sync();

// parse requests of content-type - application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: true }));
// parse requests of content-type - application/json
app.use(bodyParser.json());

app.use(express.static('./public'));

// Setup server port
var port = process.env.PORT || 3000;

// define a root route
app.use('/api/eoloplants', apiRoutes);
app.ws('/notifications', socket);

// listen for requests
app.listen(port, () => {
  console.log("Server is running on port: " + port);
});