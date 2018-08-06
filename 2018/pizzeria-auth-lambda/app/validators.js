const validator = require("validator");

module.exports = {

  createUser: (email, name, password) => {
    const errors = [];
    if (!validator.isEmail(email)) {
      errors.push('body.email must be a valid email');
    }
    if (!validator.isLength(name, {min: 3})) {
      errors.push("body.name must be at least 3 characters long");
    }
    if (!validator.isAlpha(name)) {
      errors.push("body.name must contain only letters");
    }
    if (!validator.isLength(password, {min: 8})) {
      errors.push("body.password must have at least 8 characters long");
    }
    if (!validator.matches(password, /[A-Z]/)) {
      errors.push("body.password must have at least one uppercase letter");
    }
    if (!validator.matches(password, /[a-z]/)) {
      errors.push("body.password must have at least one lowercase letter");
    }
    if (!validator.matches(password, /[0-9]/)) {
      errors.push("body.password must have at least one number");
    }
    return errors;
  }

};

