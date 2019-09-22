const Sequelize = require('sequelize');
const db = require('app/models/_db');

const modelName = 'user';
const modelFields = {
  id: {
    type: Sequelize.INTEGER,
    primaryKey: true,
    autoIncrement: true
  },
  name: {
    type: Sequelize.STRING(256),
    allowNull: false,
    unique: false
  },
  username: {
    type: Sequelize.STRING(256),
    allowNull: false,
    unique: true
  },
  hashedPassword: {
    type: Sequelize.STRING,
    allowNull: false,
  }
};
const modelMetadata = {
  underscored: true,
  freezeTableName: true,
  tableName: 'tbl_user'
};

const User = db.define(modelName, modelFields, modelMetadata);
User.sync({ force: true });
module.exports = User;