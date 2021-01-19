// userModel.js
var mongoose = require('mongoose');

// Setup schema
var userSchema = mongoose.Schema({
    nick: {
        type: String,
        required: [true, 'Nick is mandatory'],
        unique: true
    },
    password: {
        type: String,
        required: true
    },
    email: {
        type: String,
        required: [true, 'Email is mandatory'],
        unique: true
    }
});

// Export User model
module.exports = mongoose.model('user', userSchema);