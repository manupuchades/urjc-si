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
const bookController = require('../controller/bookController');
// Import review controller
const reviewController = require('../controller/reviewController');
// Import user controller
const userController = require('../controller/userController');
// Import authorization
const authentication = require('../security/authentication');


// Book routes
router.route('/books')
    .get(bookController.index)
    .post(authentication.verify,bookController.new);

router.route('/books/:book_id')
    .get(authentication.verify, bookController.view)
    .patch(authentication.verify, bookController.update)
    .put(authentication.verify, bookController.update)
    .delete(authentication.verify, bookController.delete);

router.route('/reviews')
    .get(authentication.verify, reviewController.index)
    .post(authentication.verify, reviewController.new);

router.route('/reviews/:review_id')
    .get(authentication.verify, reviewController.view)
    .patch(authentication.verify, reviewController.update)
    .put(authentication.verify, reviewController.update)
    .delete(authentication.verify, reviewController.delete);

router.route('/users/register')
    .post(userController.register);

router.route('/users/login')
    .post(userController.login);

// Export API routes
module.exports = router;