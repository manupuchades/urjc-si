// reviewModel.js
var mongoose = require('mongoose');
// Setup schema
var reviewSchema = mongoose.Schema({
    bookId: {
        type: String,
        required: true
    },
    comment: String,
    rating: {
        type: Number,
        required: true
    },
    user: {
        type: String,
        required: true
    }
});
// Export Review model
var Review = module.exports = mongoose.model('review', reviewSchema);
module.exports.get = function (callback, limit) {
    Review.find(callback).limit(limit);
}