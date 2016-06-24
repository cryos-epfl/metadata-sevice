#!/bin/bash

METADATA_DIR="/home/slf/gsn/metadatafiles/groups"

metadata_dir=${1:-$METADATA_DIR}

echo "dir = $metadata_dir"

DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

cd $DIR
cd ../grouping/
mvn compile exec:java -Dexec.mainClass="ch.epfl.osper.metadata.grouping.OsperSetsUpdate" -Dexec.args="$metadata_dir"

