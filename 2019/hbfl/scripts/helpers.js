const fs = require('fs');
const path = require('path');
const os = require('os');

function persistKeyPair(keyData) {
  return new Promise((resolve, reject) => {
    console.log(keyData);
    const keyPath = path.join(os.homedir(), 'Documents', 'ssh', keyData.KeyName);
    const options = {
      encoding: 'utf8',
      mode: 0o600,
      flag: 'w',
    };
    fs.writeFile(keyPath, keyData.KeyMaterial, options, (err) => {
      if (err) reject(err);
      else {
        console.log('Key written to', keyPath);
        resolve(keyData.KeyName)
      }
    })
  })
}

module.exports = {
  persistKeyPair
};
