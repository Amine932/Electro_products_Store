#!/bin/bash

echo "----------------- Build Gateway -----------------"
cd Gateway && mvn clean package -DskipTests && cd -

echo "----------------- Build Client-Service -----------------"
cd Client-Service && mvn clean package -DskipTests && cd -

echo "----------------- Build Config-Service -----------------"
cd Config-Service && mvn clean package -DskipTests && cd -

echo "----------------- Build Discovery-Service -----------------"
cd Discovery-Service && mvn clean package -DskipTests && cd -

echo "----------------- Build Product-Service -----------------"
cd Product-Service && mvn clean package -DskipTests && cd -

echo "----------------- Build Sale-Service -----------------"
cd Sale-Service && mvn clean package -DskipTests && cd -

echo "----------------- Build notification-service -----------------"
cd notification-service && mvn clean package -DskipTests && cd -

echo "----------------- Build Finished -----------------"
