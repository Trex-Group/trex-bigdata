# Create Image

```bash
$ ./build-image.sh base-dnsmasq
$ ./build-image.sh hadoop-base
$ ./build-image.sh hbase-base
```

# Start Container

```bash
$ ./start-hadoop-container.sh latest 3
# start master container...
# start slave1 container...
# start slave2 container...
```

# Serf agent

```bash
$ serf members

# master.test.com  172.17.0.2:7946  alive  
# slave1.test.com  172.17.0.3:7946  alive
# slave2.test.com  172.17.0.4:7946  alive

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
hadoop fs -mkdir /input
hadoop fs -ls /
hadoop fs -put /opt/hadoop/LICENSE.txt /input
hadoop fs -put /opt/hadoop/NOTICE.txt /input
hadoop fs -put /opt/hadoop/README.txt /input
hadoop fs -ls /input
```

### run jar
```bash
hadoop jar /opt/hadoop/share/hadoop/mapreduce/hadoop-mapreduce-examples-2.7.3.jar wordcount /input /output
```


