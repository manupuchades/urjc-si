const db = require("../connection/db.connection");
const Eolicplant = db.eolicplants;
const Op = db.Sequelize.Op;

// Create and Save a new EolicPlant
exports.create = (req, res) => {
    // Validate request
    if (!req.body.city) {
        res.status(400).send({
            message: "City cannot be empty!"
        });
        return;
    }

    // Create a Eolicplant
    const eolicplant = {
        city: req.body.city,
        progress: '0',
        completed: false
    };

    // Save Eolicplant in the database
    Eolicplant.create(eolicplant)
        .then(data => {
            res.send(data);
        })
        .catch(err => {
            res.status(500).send({
                message:
                    err.message || "Some error occurred while creating the Eolicplant."
            });
        });
};

// Retrieve all Eolicplants from the database.
exports.findAll = (req, res) => {
    Eolicplant.findAll()
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving the data."
      });
    });

};

// Find a single EolicPlant with an id
exports.findOne = (req, res) => {
    const id = req.params.id;

    Eolicplant.findByPk(id)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message: "Error retrieving Eolicplant with id=" + id
      });
    });

};

// Delete a EolicPlant with the specified id in the request
exports.delete = (req, res) => {
    const id = req.params.id;

    Eolicplant.destroy({
    where: { id: id }
  })
    .then(num => {
      if (num == 1) {
        res.send({
          message: "Eolicplant was deleted successfully!"
        });
      } else {
        res.send({
          message: "Eolicplant not found with id=" + id
        });
      }
    })
    .catch(err => {
      res.status(500).send({
        message: "Could not delete Cannot with id=" + id
      });
    });
};