'use strict';

import bcrypt from 'bcrypt';
import mongoose from 'mongoose';
mongoose.Promise = require('bluebird');

// Schemas

const PhoneSchema = new mongoose.Schema({
  ddd: {
    type: String,
    required: [true, 'DDD do telefone obrigatório.']
  },
  numero: {
    type: String,
    required: [true, 'Número do telefone obrigatório.']
  }
});

const UserSchema = new mongoose.Schema({
  nome: {
    type: String,
    required: [true, 'Nome obrigatório.']
  },
  email: {
    type: String,
    required: [true, 'E-mail obrigatório.'],
    unique: [true, 'E-mail já está em uso.']
  },
  senha: {
    type: String,
    required: [true, 'Senha obrigatória.']
  },
  telefones: [PhoneSchema],
  dataCriacao: Date,
  dataAtualizacao: Date,
  ultimoLogin: Date,
  token: String
});

// Transforms

if (!PhoneSchema.options.toObject) {
  PhoneSchema.options.toObject = {};
}

PhoneSchema.options.toObject.transform = function(doc, obj, options) {
  delete obj._id;
  return obj;
};

if (!UserSchema.options.toObject) {
  UserSchema.options.toObject = {};
}

UserSchema.options.toObject.transform = function(doc, obj, options) {
  obj.id = obj._id.toHexString();
  delete obj._id;
  delete obj.__v;
  delete obj.senha;
  return obj;
};

// Custom validators

UserSchema.path('email').validate(function(value) {
  return /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(value);
}, 'E-mail inválido.');

UserSchema.path('email').validate(function(value, respond) {
  return this.constructor.findOne({email: value}).exec()
    .then(user => {
      if (!user)
        return respond(true);
      if (this.id === user.id)
        return respond(true);
      return respond(false);
    })
    .catch(err => {
      throw err;
    });
}, 'E-mail já está em uso.');

// Hooks

UserSchema.pre('save', function(next) {
  if (this.isNew) {
    this.dataCriacao = new Date();
  }
  this.dataAtualizacao = new Date();
  next();
});

// Public operations

UserSchema.methods = {

  hashPassword: function() {
    let hash = bcrypt.hashSync(this.senha, 10);
    this.senha = hash;
    return hash;
  },

  checkPassword: function(senha) {
    return bcrypt.compareSync(senha, this.senha);
  }

};

export default mongoose.model('User', UserSchema);
