const {persistKeyPair} = require("./helpers");
const AWS = require('aws-sdk');

AWS.config.update({region: 'eu-west-1'});

const ec2 = new AWS.EC2();
const groupName = 'hamster_sg';
const keyName = 'hamster_key';

createSecurityGroup(groupName)
  .then(() => {
    return createKeyPair(keyName)
  })
  .then(persistKeyPair)
  .then(() => {
    return createInstance(groupName, keyName)
  })
  .then(data => {
    console.log('Created instance with:', data);
  })
  .catch(err => {
    console.error('Failed to create instance with:', err)
  });

function createSecurityGroup(groupName) {
  const params = {
    Description: groupName,
    GroupName: groupName,
  };
  return new Promise((res, rej) => {
    ec2.createSecurityGroup(params, (err, data) => {
      if (err) rej(err);
      else {
        const params = {
          GroupId: data.GroupId,
          IpPermissions: [
            {
              IpProtocol: 'tcp',
              FromPort: 22,
              ToPort: 22,
              IpRanges: [
                {CidrIp: '0.0.0.0/0'}
              ]
            },
            {
              IpProtocol: 'tcp',
              FromPort: 3000,
              ToPort: 3000,
              IpRanges: [
                {CidrIp: '0.0.0.0/0'}
              ]
            }
          ]
        };
        ec2.authorizeSecurityGroupIngress(params, err => {
          if (err) rej(err);
          else {
            res();
          }
        })
      }
    })
  });
}

function createKeyPair(keyName) {
  const params = {
    KeyName: keyName
  };
  return new Promise((res, rej) => {
    ec2.createKeyPair(params, (err, data) => {
      if (err) rej(err);
      else res(data);
    })
  })
}

function createInstance(groupName, keyName) {
  const params = {
    ImageId: 'ami-00035f41c82244dab',
    InstanceType: 't2.nano',
    KeyName: keyName,
    MaxCount: 1,
    MinCount: 1,
    SecurityGroups: [
      groupName
    ],
    UserData: getUserData(),
  };
  return new Promise((res, rej) => {
    ec2.runInstances(params, (err, data) => {
      if (err) rej(err);
      else res(data);
    })
  })
}

function getUserData() {
  return Buffer.from(
    `#!/bin/bash
    sudo apt install -y nodejs
    sudo apt install -y npm
    sudo apt install -y git
    git clone https://github.com/ryanmurakami/hbfl.git
    cd hbfl
    npm i
    npm run start
    `).toString('base64');
}
