echo $HADOOP_MASTER > /opt/hadoop/etc/hadoop/master

rm -rf /opt/hadoop/etc/hadoop/slaves
[[ ! -z $HADOOP_SLAVE ]] && for slave in $HADOOP_SLAVE; do echo $slave >> /opt/hadoop/etc/hadoop/slaves; done

members_line=$(paste -d, -s $HADOOP_SLAVE 2>&1)
memstr='members' #uniq string for replace
sed -i -e "s/$memstr/$members_line/g" /opt/hbase/conf/hbase-site.xml

function init_members(){
    for member in $HADOOP_SLAVE
    do
        scp /opt/hadoop/etc/hadoop/slaves $member:$HADOOP_CONF_DIR/slaves #hadoop
        scp /opt/hbase/conf/hbase-site.xml $member:/opt/hbase/conf/hbase-site.xml #hbase
    done
}

init_members

