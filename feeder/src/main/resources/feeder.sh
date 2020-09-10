#!/usr/bin/env bash

PROJ_DIR="/home/ubuntu/stockprediction"

java -jar $PROJ_DIR/feeder/target/feeder-1.0-SNAPSHOT-jar-with-dependencies.jar my-topic $PROJ_DIR/data.csv

