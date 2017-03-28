echo $HADOOP_MASTER > /opt/hadoop/etc/hadoop/master

rm -rf /opt/hadoop/etc/hadoop/slaves
[[ ! -z $HADOOP_SLAVE ]] && for slave in $HADOOP_SLAVE; do echo $slave >> /opt/hadoop/etc/hadoop/slaves; done

function init_members(){
    for member in $HADOOP_SLAVE
    do
        scp /opt/hadoop/etc/hadoop/slaves $member:$HADOOP_CONF_DIR/slaves #hadoop
    done
}

init_members

