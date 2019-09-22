const {ApolloServer, gql} = require('apollo-server');
const {Account, Driver, User, UserType, Vehicle} = require('./database');

const typeDefs = gql`
  type Query {
    accounts(id: ID, offset: Int, limit: Int): [Account]
  }
  type Account {
    accountId: Int
    description: String
    drivers(offset: Int, limit: Int): [Driver]
    vehicles(offset: Int, limit: Int): [Vehicle]
    users(offset: Int, limit: Int): [User]
  }
  type Driver {
    driverId: Int
    firstName: String
    lastName: String
    assignedVehicles: [Vehicle]
  }
  type User {
    userId: Int
    userType: String
  }
  type Vehicle {
    vehicleId: Int
    driver: Driver
    description: String
    vin: String
  }
`;

const resolvers = {
  Query: {
    accounts: (parent, {id, offset, limit}) => {
      return Account.findAll({
        where: id ? {account_id: id} : null,
        offset: offset ? offset : null,
        limit: limit ? limit : null,
        order: ['description']
      });
    }
  },
  Account: {
    accountId: _ => _.account_id,
    description: _ => _.description,
    users: (parent, {offset, limit}) => {
      return User.findAll({
        where: {account_id: parent.account_id},
        offset: offset ? offset : null,
        limit: limit ? limit : null,
        order: ['username']
      })
    },
    vehicles: (parent, {offset, limit}) => {
      return Vehicle.findAll({
        where: {account_id: parent.account_id},
        offset: offset ? offset : null,
        limit: limit ? limit : null,
        order: ['description']
      })
    },
    drivers: (parent, {offset, limit}) => {
      return Driver.findAll({
        where: {account_id: parent.account_id},
        offset: offset ? offset : null,
        limit: limit ? limit : null,
        order: ['first_name']
      })
    }
  },
  Driver: {
    driverId: _ => _.driver_id,
    firstName: _ => _.first_name,
    lastName: _ => _.last_name,
    assignedVehicles: (parent, {offset, limit}) => {
      return Vehicle.findAll({
        where: {driver_id: parent.driver_id},
        offset: offset ? offset : null,
        limit: limit ? limit : null,
        order: ['description']
      })
    }
  },
  Vehicle: {
    vehicleId: _ => _.vehicle_id,
    driver: _ => Driver.findById(_.driver_id).then(x => x.first_name + ' ' + x.last_name),
    description: _ => _.description,
    vin: _ => _.vin
  },
  User: {
    userId: _ => _.user_id,
    userType: _ => UserType.findById(_.type_id).then(x => x.description)
  }
};

const server = new ApolloServer({
  typeDefs,
  resolvers
});

server.listen({port:8000}).then(() => {
  console.log("App running at port 8000");
});