#!/bin/bash

node -p "'Node'.padEnd(8, '*')"
node --v8-options | grep "in progress"
node --v8-options | grep gc
node -p "console.log(global)"
node -p "process"

