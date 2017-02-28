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

```bash
$ serf members
# master.test.com  172.17.0.2:7946  alive  
# slave1.test.com  172.17.0.3:7946  alive
# slave2.test.com  172.17.0.4:7946  alive
$ cd ~
$ ./configure-members.sh
$ ./start-hadoop.sh
$ jps
$ hdfs dfsadmin -report
```

# WordCount

```bash
hadoop fs -mkdir /input
hadoop fs -ls /
hadoop fs -put /usr/local/hadoop/LICENSE.txt /input
hadoop fs -put /usr/local/hadoop/NOTICE.txt /input
hadoop fs -put /usr/local/hadoop/README.txt /input
hadoop fs -ls /input 

hadoop jar /usr/local/hadoop/share/hadoop/mapreduce/hadoop-mapreduce-examples-2.7.3.jar wordcount /input /output
```


