#!/usr/bin/env bash

function get_hostname() {
  HOSTNAME=$(hostname)
  HOSTNAME=${HOSTNAME#ip-}
  HOSTNAME=${HOSTNAME//-/.}
  echo "$HOSTNAME";
}

export PROJ_DIR="/home/ubuntu/stockprediction"

export JAVA_HOME="/usr/lib/jvm/java-8-openjdk-amd64"

export GS_HOME="/home/ubuntu/gigaspaces-insightedge-enterprise-15.2.0"

export GS_LOOKUP_GROUPS="xap-15.2.0"

HOSTNAME=$(get_hostname)

echo "HOSTNAME is: $HOSTNAME"

export GS_NIC_ADDRESS="$HOSTNAME"


if [ -z "${GS_LOOKUP_LOCATORS+xxx}" ]; then
  export GS_MANAGER_SERVERS="172.31.0.64"
fi

export GS_LICENSE="Product=InsightEdge;Version=15.2;Type=ENTERPRISE;Customer=GigaSpaces_Technologies_-_Internal_Jay_Dalal_DEV;Expiration=2021-Apr-15;Hash=qRq4PNPOMQ0V1SkQyawG"

export GS_GSC_OPTIONS="-Xms1g -Xmx1g -XX:+UseG1GC -Dgs.gc.collectionTimeThresholdWarning=10000"

export GS_OPTIONS_EXT="-Dcom.gs.get-build=false"
