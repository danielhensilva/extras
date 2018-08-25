const Sequelize = require('sequelize');
const config = require('../../configs').database;

const db = new Sequelize(
  config.database,
  config.username,
  config.password, {
    host: config.host,
    dialect: config.dialect,
  }
);

db.authenticate()
  .then(() => {
    console.error('Connection has been established successfully.');
  })
  .catch(error => {
    console.error('Unable to connect to the database:', error);
  });

module.exports = db;