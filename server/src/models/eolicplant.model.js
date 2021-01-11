module.exports = (sequelize, Sequelize) => {
    const Eolicplant = sequelize.define("eolicplant", {
        id: {
            type: Sequelize.INTEGER,
            autoIncrement: true,
            primaryKey: true
        },
        city: {
            type: Sequelize.STRING,
            allowNull: false
        },
        progress: Sequelize.INTEGER,
        completed: Sequelize.BOOLEAN,
        planning: Sequelize.STRING,
    });
    return Eolicplant;
};