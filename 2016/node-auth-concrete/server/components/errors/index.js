/**
 * Error responses
 */

'use strict';

module.exports.endpointNotFound = (req, res) => {
  res.status(404).json({
    mensagem: "Endpoint não encontrado."
  });
};

module.exports.invalidCredentials = (req, res) => {
  res.status(401).json({
    mensagem: "Usuário e/ou senha inválidos."
  });
};

module.exports.invalidToken = (req, res) => {
  res.status(401).json({
    mensagem: "Não autorizado.",
    detalhes: "Token inválido."
  });
};

module.exports.unathorized = (req, res) => {
  res.status(401).json({
    mensagem: "Não autorizado.",
    detalhes: "Você não tem permissão para acessar esse recurso."
  });
};

module.exports.expiredToken = (req, res) => {
  res.status(400).json({
    mensagem: "Sessão expirada."
  });
};

module.exports.invalidInput = (req, res, val) => {
  res.status(422).json({
    mensagem: "Falha de validação.",
    detalhes: Object.keys(val.errors).map(key => {
      return {
        propriedade: key,
        motivo: val.errors[key].message
      };
    })
  });
};

module.exports.invalidJson = (req, res, err) => {
  res.status(400).json({
    mensagem: "JSON inválido.",
    detalhes: err
  });
};

module.exports.generic = (req, res, err) => {
  res.status(500).json({
    mensagem: "Erro não tratado.",
    detalhes: err
  });
};
