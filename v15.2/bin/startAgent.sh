#!/usr/bin/env bash

source /home/ubuntu/stockprediction/v15.2.0/bin/setExampleEnv.sh


nohup /home/ubuntu/gigaspaces-insightedge-enterprise-15.2.0/bin/gs.sh host run-agent --manager --gsc=4 --spark-master --spark-worker --zeppelin --webui > /tmp/agent-console.log 2>&1 &

## nohup $GS_HOME/bin/gs.sh demo > /tmp/agent-console.log 2>&1 &


