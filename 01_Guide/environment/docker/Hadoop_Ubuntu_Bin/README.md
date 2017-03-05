# Create Image

```bash
$ ./build-image.sh base-dnsmasq
$ ./build-image.sh hadoop-base
$ ./build-image.sh hbase-base
```

# Start Container

```bash
$ cd ~
$ ./start-hadoop-container.sh latest 3
# start master container...
# start slave1 container...
# start slave2 container...
```

# Serf agent

```bash
$ serf members

# master.trex.com  172.17.0.2:7946  alive  
# slave1.trex.com  172.17.0.3:7946  alive
# slave2.trex.com  172.17.0.4:7946  alive

$ cd ~
$ ./configure-members.sh
```

# Start Hadoop

```bash
$ ./start-hadoop.sh

$ jps
$ hdfs dfsadmin -report
```

# WordCount

### upload file

```bash
$HADOOP_HOME/bin/hadoop fs -mkdir /input
$HADOOP_HOME/bin/hadoop fs -ls /
$HADOOP_HOME/bin/hadoop fs -put $HADOOP_HOME/LICENSE.txt /input
$HADOOP_HOME/bin/hadoop fs -put $HADOOP_HOME/NOTICE.txt /input
$HADOOP_HOME/bin/hadoop fs -put $HADOOP_HOME/README.txt /input
$HADOOP_HOME/bin/hadoop fs -ls /input
```

### run jar
```bash
hadoop jar $HADOOP_HOME/share/hadoop/mapreduce/hadoop-mapreduce-examples-2.7.3.jar wordcount /input /output
```


