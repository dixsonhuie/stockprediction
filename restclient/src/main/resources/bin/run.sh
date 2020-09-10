#!/usr/bin/env bash

PROJ_DIR="/home/ubuntu/stockprediction"


java -jar $PROJ_DIR/restclient/target/restclient-1.0-SNAPSHOT-jar-with-dependencies.jar http://localhost:8091 restserver/rest/mlinstance