// reviewController.js
// Import review model
Review = require('../model/reviewModel');
Book = require('../model/bookModel');


// Handle index actions
exports.index = function (req, res) {
    Review.get(function (err, reviews) {
        if (err) {
            res.json({
                status: "500",
                message: "Unexpected Error Occurred!",
            });
        } else {
            res.json({
                status: "success",
                message: "Reviews retrieved successfully",
                data: reviews
            });
        }
    });
};

// Handle create review actions
exports.new = function (req, res) {
    Book.findById(req.body.bookId, function (err, book) {

        if (err) {
            res.json({
                status: "404",
                message: "Book not found!",
            });
        } else {

            var review = new Review();
            review.bookId = req.body.book_id;
            review.comment = req.body.comment;
            review.rating = req.body.rating;
            review.user = req.body.user;

            // save the review and check for errors
            review.save(function (err) {
                if (err) {
                    res.json({
                        status: "500",
                        message: "Unexpected Error Occurred!",
                    });
                } else {
                    res.json({
                        message: 'New review created!',
                        data: review
                    });
                }
            });
        }
    });
};

// Handle view review info
exports.view = function (req, res) {
    Review.findById(req.params.review_id, function (err, review) {
        if (err) {
            res.json({
                status: "404",
                message: "Review not found!",
            });
        } else {
            res.json({
                message: 'Review details loading..',
                data: review
            });
        }
    });
};

// Handle update review info
exports.update = function (req, res) {
    Review.findById(req.params.review_id, function (err, review) {
        if (err) {
            res.json({
                status: "404",
                message: "Review not found!",
            });
        } else {
            Book.findById(req.body.bookId, function (err, book) {
                if (err) {
                    res.json({
                        status: "404",
                        message: "Book not found!",
                    });
                } else {
                    review.bookId = req.body.bookId;
                    review.comment = req.body.comment;
                    review.rating = req.body.rating;
                    review.user = req.body.user;
                    // save the review and check for errors
                    review.save(function (err) {
                        if (err)
                            res.json({
                                status: "500",
                                message: "Unexpected Error Occurred!",
                            });
                        res.json({
                            message: 'Review Info updated',
                            data: review
                        });
                    });
                }
            });

        }
    });
};

// Handle delete review
exports.delete = function (req, res) {
    Review.remove({
        _id: req.params.review_id
    }, function (err, review) {
        if (err) {
            res.json({
                status: "404",
                message: "Review not found!",
            });
        } else {
            res.json({
                status: "success",
                message: 'Review deleted'
            });
        }
    });
};