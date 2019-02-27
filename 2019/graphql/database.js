const Sequelize = require('sequelize');
const sequelize = new Sequelize('mysql://root:p4ssword@localhost:3306/core', {
  dialect: 'mysql'
});

sequelize
  .authenticate()
  .then(() => {
    console.log('Connection has been established successfully');
  })
  .catch(err => {
    console.error('Unable to connect to the database', err);
  });

const tableSettings = {
  freezeTableName: true,
  underscored: true,
  timestamps: false
};

exports.Account = sequelize.define('account', {
  account_id: {
    type: Sequelize.INTEGER,
    primaryKey: true
  },
  description: Sequelize.STRING,
}, tableSettings);

exports.Driver = sequelize.define('driver', {
  driver_id: {
    type: Sequelize.INTEGER,
    primaryKey: true
  },
  account_id: Sequelize.INTEGER,
  first_name: Sequelize.STRING,
  last_name: Sequelize.STRING
}, tableSettings);

exports.User = sequelize.define('user', {
  user_id: {
    type: Sequelize.INTEGER,
    primaryKey: true
  },
  account_id: Sequelize.INTEGER,
  type_id: Sequelize.INTEGER,
  username: Sequelize.STRING
}, tableSettings);

exports.UserType = sequelize.define('user_type', {
  type_id: {
    type: Sequelize.INTEGER,
    primaryKey: true
  },
  description: Sequelize.STRING,
}, tableSettings);

exports.Vehicle = sequelize.define('vehicle', {
  vehicle_id: {
    type: Sequelize.INTEGER,
    primaryKey: true
  },
  account_id: Sequelize.INTEGER,
  driver_id: Sequelize.INTEGER,
  description: Sequelize.STRING,
  vin: Sequelize.STRING,
}, tableSettings);
