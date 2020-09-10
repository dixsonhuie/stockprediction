#!/usr/bin/env bash

#PROJ_DIR="/home/ubuntu/stockprediction"
PROJ_DIR="../.."


#java -jar $PROJ_DIR/restclient/target/restclient-1.0-SNAPSHOT-jar-with-dependencies.jar http://localhost:8091 restserver/rest/mlinstance
#java -jar $PROJ_DIR/restclient-1.0-SNAPSHOT-jar-with-dependencies.jar http://dummy.restapiexample.com api/v1/employees

java -jar $PROJ_DIR/restclient-1.0-SNAPSHOT-jar-with-dependencies.jar http://35.183.9.24:8091 restserver/rest/mlinstance