#!/bin/bash

DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
LOG="logs/web.log"

log=${1:-$LOG}
cd $DIR

echo "Building OAI"
cd ../oai-pmh-osper/
mvn -Dmaven.test.skip=true clean install

echo "Building metadata service"
cd ..
mvn -Dmaven.test.skip=true clean install

echo "Starting metadata service"
nohup java -jar web/target/web-0.1.0.jar  > $log &