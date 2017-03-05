#!/bin/bash

echo $HADOOP_MASTER > /opt/hadoop/etc/hadoop/slaves
[[ ! -z $HADOOP_SLAVE ]] && for slave in $HADOOP_SLAVE; do echo $slave > /opt/hadoop/etc/hadoop/slaves; done

$HADOOP_INSTALL/sbin/start-dfs.sh

echo -e "\n"
$HADOOP_INSTALL/sbin/start-yarn.sh
