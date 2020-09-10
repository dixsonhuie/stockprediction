#!/usr/bin/env bash


source /home/ubuntu/stockprediction/v15.2/bin/setExampleEnv.sh

$GS_HOME/bin/gs.sh pu deploy --instances=1 --property=web.port=8091 restservice $PROJ_DIR/space/restserver/restserver.war

