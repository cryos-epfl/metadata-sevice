#!/bin/bash

METADATA_DIR="/home/slf/gsn/metadatafiles/dif"

metadata_dir=${1:-$METADATA_DIR}

echo "dir = $metadata_dir"

DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

cd $DIR
cd ../oai-pmh-osper/
mvn compile exec:java -Dexec.mainClass="ch.epfl.osper.oai.load.RecordLoader" -Dexec.args="$metadata_dir"

