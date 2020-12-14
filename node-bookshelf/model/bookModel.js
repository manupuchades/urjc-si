// bookModel.js
var mongoose = require('mongoose');
// Setup schema
var bookSchema = mongoose.Schema({
    author: {
        type: String,
        required: true
    },
    description: String,
    edition: {
        type: Date,
        default: Date.now
    },
    publisher: String,
    title: {
        type: String,
        required: true
    }
});
// Export Book model
var Book = module.exports = mongoose.model('book', bookSchema);
module.exports.get = function (callback, limit) {
    Book.find(callback).limit(limit);
}