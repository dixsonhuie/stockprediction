#!/usr/bin/env bash

source /home/ubuntu/stockprediction/v15.2.0/bin/setExampleEnv.sh

nohup $GS_HOME/bin/gs.sh host run-agent --webui > /tmp/webui.log 2>&1 &


