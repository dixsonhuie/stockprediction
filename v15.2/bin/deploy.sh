#!/usr/bin/env bash


source /home/ubuntu/stockprediction/v15.2.0/bin/setExampleEnv.sh

$GS_HOME/bin/gs.sh pu deploy demo /home/ubuntu/stockprediction/space/target/stockprediction-space.jar

