#!/bin/bash

GSN_SENSORS="url=http://montblanc.slf.ch:22002/ws/api/sensors"
SERVER="http://www.osper.ch"

location=${1:-$GSN_SENSORS}
server=${2:-$SERVER}

echo "location = $location"

DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

cd $DIR
cd ../core/
mvn compile exec:java -Dexec.mainClass="ch.epfl.gsn.metadata.tools.gsn.GSNImportTool" -Dexec.args="$location $server"

