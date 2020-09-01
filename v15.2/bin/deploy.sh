#!/usr/bin/env bash


source /home/ubuntu/stockprediction/v15.2/bin/setExampleEnv.sh

$GS_HOME/bin/gs.sh pu deploy demo $PROJ_DIR/space/target/stockprediction-space.jar

