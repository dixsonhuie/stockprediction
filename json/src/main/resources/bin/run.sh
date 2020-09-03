#!/usr/bin/env bash

PROJ_DIR="/home/ubuntu/stockprediction"


export GS_LOOKUP_LOCATORS="172.31.0.64:4174"
export GS_LOOKUP_GROUPS="xap-15.2.0"

java -jar $PROJ_DIR/json/target/json-1.0-SNAPSHOT-jar-with-dependencies.jar
