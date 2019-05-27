'use strict';

let _id = 2;
let _items = [{id: 1, name: "abc"}, {id: 2, name: "def"}];

const createResponse = (statusCode, body) => ({
    statusCode: statusCode || 501,
    headers: {'Content-Type': 'text/json'},
    body: body ? JSON.stringify(body) : undefined
});

const executeWhen = (method, path, event, callback, target) => {
    if (method !== event.httpMethod) return;
    if (path !== event.resource) return;
    target(event, callback);
};

module.exports.router = (event, context, callback) => {
    console.log(JSON.stringify(event));

    executeWhen("GET", "/items", event, callback, getAllItems);
    executeWhen("GET", "/items/{id}", event, callback, getSingleItem);
    executeWhen("POST", "/items", event, callback, createItem);
    executeWhen("PUT", "/items/{id}", event, callback, updateItem);
    executeWhen("DELETE", "/items/{id}", event, callback, deleteItem);

    console.log("items", _items);
};

const getAllItems = (event, callback) => {
    const items = _items.sort((a, b) => a.id - b.id);
    callback(null, createResponse(200, items));
};

const getSingleItem = (event, callback) => {
    const id = (event.pathParameters || {}).id;
    const item = _items.find(x => x.id === id);
    if (!item) {
        callback(null, createResponse(404));
        return;
    }
    callback(null, createResponse(200, item));
};

const createItem = (event, callback) => {
    const id = ++_id;
    const body = JSON.parse(event.body);
    const item = Object.assign({}, body, {id});
    _items.push(item);
    callback(null, createResponse(201, {id}));
};

const updateItem = (event, callback) => {
    const id = event.pathParameters.id;
    const index = _items.findIndex(x => x.id === id);
    if (index < 0) {
        callback(null, createResponse(404));
        return;
    }
    const body = JSON.parse(event.body);
    _items[index] = Object.assign({}, body, {id});
    callback(null, createResponse(200));
};

const deleteItem = (event, callback) => {
    const id = event.pathParameters.id;
    const index = _items.findIndex(x => x.id === id);
    if (index < 0) {
        callback(null, createResponse(404));
        return;
    }
    _items.splice(index, 1);
    callback(null, createResponse(201));
};
