#!/usr/bin/env sh

mvn clean install -Dtestfx.robot=glass -Dglass.platform=Monocle -Dmonocle.platform=Headless -Dprism.order=sw
mvn sonar:sonar -Dsonar.login=$SONAR_TOKEN
