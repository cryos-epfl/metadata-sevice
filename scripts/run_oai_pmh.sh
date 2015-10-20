#!/bin/bash
DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

cd $DIR

LOG="../logs/oai.log"
log=${1:-$LOG}

cd ../oai-pmh-osper/
mvn -Dmaven.test.skip=true clean install
cd ../oai-rest/
mvn -Dmaven.test.skip=true clean install

nohup java -jar target/oai-pmh-rest-0.1.0.jar > $log &