#!/bin/bash

sleepTimeInSec=10

runDocker() {
  echo "----------------- `date +%T` Run $1 -----------------"
  docker-compose up -d $1
  echo "`date +%T` Wait a moment!"

  sleep $sleepTimeInSec
}

# docker-compose down

# runDocker discovery-service
# runDocker config-service
# runDocker product-service
# runDocker client-service
runDocker sale-service
# runDocker gateway-service
runDocker notification-service
# runDocker store-front-end

echo "Run finished"
