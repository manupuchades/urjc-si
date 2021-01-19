// userController.js
// import User model
var User = require("../model/userModel");

// import jsonwebtoken
var jwt = require('jsonwebtoken');

// import bcryptjs - hashing function 
var bcrypt = require('bcryptjs');

// import config
var config = require('../config/config');

// User Register function
exports.register = function(req, res) {
    User.create({
        nick : req.body.nick,
        email : req.body.email,
        password : bcrypt.hashSync(req.body.password, 10)
    }, function(err, user) {
        if (err) {
            return res.status(500).send("Error ocurred while registering the user.");
        }
        user.password = undefined;

        var token = jwt.sign({ id: user._id }, 
            config.secret, {
            expiresIn: 86400});  // expires in 24 hours

        res.status(201).header('Authorization', 'Bearer '+ token).json(user);
    });
};

// User Sign function
exports.login = function(req, res) {
    User.findOne({nick: req.body.username}, function(err, user) {
        if (err) throw err;
        if (!user) {
                console.log(">>> User not found" + " : " + req.body.object);

                res.status(401).json({ message: 'Authentication failed. Incorrect email/password.'});
        } else {
            var passwordIsValid = bcrypt.compareSync(req.body.password, user.password);
            if (!passwordIsValid) {
                console.log(">>> Wrong password");

                res.status(401).json({ message: 'Authentication failed. Incorrect email/password.'});
            }
            var token = jwt.sign({ id: user._id }, 
                config.secret, {
                expiresIn: 86400});  // expires in 24 hours
            res.status(201).header('Authorization', 'Bearer '+ token).json({message : "Welcome back!"});
        }
    });
};