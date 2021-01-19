// Import express
let express = require('express');
// Import file system
let fs = require('fs');
// Import https
var https = require('https')
// Import Body parser
let bodyParser = require('body-parser');
// Import Mongoose
let mongoose = require('mongoose');
// Initialize the app
let app = express();
// Import routes
let apiRoutes = require("./api-routes/api-routes")

// Configure bodyparser to handle post requests
app.use(bodyParser.urlencoded({
    extended: true
}));
app.use(bodyParser.json()); 

// Connect to Mongoose and set connection variable
mongoose.connect('mongodb://localhost/bookshelf', { useNewUrlParser: true});
var db = mongoose.connection;

// Added check for DB connection
if(!db)
    console.log("Error connecting db")
else
    console.log("Db connected successfully")

// Setup server port
var port = process.env.PORT || 8080;

// Use Api routes in the App
app.use(apiRoutes);

// Launch app to listen to specified port on https
https.createServer({
    key: fs.readFileSync('certificates/server.key'),
    cert: fs.readFileSync('certificates/server.cert')
  }, app).listen(port, function () {
     console.log("Running Bookshelf on port " + port);
});
