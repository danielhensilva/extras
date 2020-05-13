#!/bin/bash

declare -a components=("eureka-server" "config-server" "maths-api" "maths-service")

for component in "${components[@]}"
do
  docker run --name ${component} -P -d ${component}
done



