#!/usr/bin/env bash

if [ -z "$KAFKA_HOME" ]; then
    echo "Please set KAFKA_HOME environment variable."
    exit 1
fi


java -jar /home/ubuntu/feeder-1.0-SNAPSHOT-jar-with-dependencies.jar my-topic /home/ubuntu/stockprediction/v15.2.0/data/data.csv
