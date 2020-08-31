#!/usr/bin/env bash

PROJ_DIR="/home/dixson/work/proj28/stockprediction"


export GS_LOOKUP_LOCATORS="ec2-35-182-43-17.ca-central-1.compute.amazonaws.com:4174"
export GS_LOOKUP_GROUPS="xap-15.2.0"

java -jar $PROJ_DIR/restserver/target/restserver-1.0-SNAPSHOT-jar-with-dependencies.jar
