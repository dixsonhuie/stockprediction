#!/usr/bin/env bash

source /home/ubuntu/stockprediction/v15.2/bin/setExampleEnv.sh


nohup $GS_HOME/bin/gs.sh host run-agent --manager --gsc=4 --spark-master --spark-worker --zeppelin --webui > /tmp/agent-console.log 2>&1 &

## nohup $GS_HOME/bin/gs.sh demo > /tmp/agent-console.log 2>&1 &


