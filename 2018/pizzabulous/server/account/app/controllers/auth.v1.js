const Router = require('express');
const routes = Router();

const userService = require('../services/auth_service');

routes.post('signIn', (req, res) => {
});

routes.post('signUp', (req, res) => {

});

module.exports = routes;