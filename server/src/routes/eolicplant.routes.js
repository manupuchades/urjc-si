let router = require("express").Router();
let eoloplants = require("../controllers/eolicplant.controller.js");

// Create a new Eolicplant
router.post("/", eoloplants.create);

// Retrieve all Eolicplants
router.get("/", eoloplants.findAll);

// Retrieve a single Eolicplant with id
router.get("/:id", eoloplants.findOne);

// Delete a Eolicplant with id
router.delete("/:id", eoloplants.delete);

module.exports = router;