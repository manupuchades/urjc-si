// api-routes.js

// Initialize express router
let router = require('express').Router();
// Set default API response
router.get('/', function (req, res) {
    res.json({
        status: 'API is Working',
        message: 'Welcome to Bookshelf crafted with love!',
    });
});
// Import book controller
var bookController = require('../controller/bookController');
// Import book controller
var reviewController = require('../controller/reviewController');
// Book routes
router.route('/books')
    .get(bookController.index)
    .post(bookController.new);

router.route('/books/:book_id')
    .get(bookController.view)
    .patch(bookController.update)
    .put(bookController.update)
    .delete(bookController.delete);

router.route('/reviews')
    .get(reviewController.index)
    .post(reviewController.new);

router.route('/reviews/:review_id')
    .get(reviewController.view)
    .patch(reviewController.update)
    .put(reviewController.update)
    .delete(reviewController.delete);

// Export API routes
module.exports = router;