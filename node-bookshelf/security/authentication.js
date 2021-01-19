var jwt = require('jsonwebtoken'); // used to create, sign, and verify tokens
var config = require('../config/config'); // get our config file

exports.verify = function (req, res, next) {

    // check header parameters for token
    var token = req.headers.authorization;
    if (!token) {       
        return res.status(403).send({ auth: false, message: 'Access denied.' });
    }

    // verifies secret and checks exp
    jwt.verify(token.slice(7, token.length), config.secret, function (err, decoded) {
        if (err) {
            return res.status(500).send({ auth: false, message: 'Expired or invalid JWT token' });
        }
        // succesfull authentication
        next();
    });
}