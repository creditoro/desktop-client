#!/usr/bin/env sh

Xvfb :1 &
mvn clean install
mvn sonar:sonar -Dsonar.login=$SONAR_TOKEN
sudo pkill -9 Xvfb
