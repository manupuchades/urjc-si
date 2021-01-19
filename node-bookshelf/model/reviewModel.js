// reviewModel.js
const mongoose = require('mongoose');
const User = require('./userModel.js');

// Setup schema
var reviewSchema = mongoose.Schema({
    bookId: {
        type: String,
        required: true
    },
    comment: String,
    rating: {
        type: Number,
        min: [0, 'Rating must be at least 0'],
        max: [5, 'Rating must be less or equals than 5'],
        required: true
    },
    user: {
        type: String,
        required: true
    }
});
// Export Review model
var Review = module.exports = mongoose.model('review', reviewSchema);