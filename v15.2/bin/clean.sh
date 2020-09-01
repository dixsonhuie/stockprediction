#!/usr/bin/env bash

source /home/ubuntu/stockprediction/v15.2/bin/setExampleEnv.sh


rm -r $GS_HOME/logs/*

rm -r $GS_HOME/work/*

CWD=`pwd`;

cd $GS_HOME/deploy


for i in `ls | grep -v templates`; do
  rm -r $i;
done

cd $CWD

