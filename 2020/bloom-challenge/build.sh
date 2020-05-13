#!/bin/bash

declare -a components=("config-server" "eureka-server" "maths-api" "maths-service")

for component in "${components[@]}"
do
  pushd ${component}
  docker build --tag ${component}:latest .
  popd
done



