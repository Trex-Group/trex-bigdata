
# Build images
```bash
docker-compose -f docker-compose.image.yaml build
```

# Startup Container
```bash
# build & run

docker-compose -f docker-compose.hadoop-container.yaml up -d

docker-compose up -d
docker-compose run -d
```

# Clean up
```bash
docker-compose stop
docker-compose rm

docker-compost -f down
```

# master container

```bash
docker exec -it master.trex.com bash

cd ~
/root/configure-members.sh
/root/start-hadoop.sh

# test
jps
$HADOOP_HOME/bin/hdfs dfsadmin -report
```

# WordCount

### upload files

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
$HADOOP_HOME/bin/hadoop jar $HADOOP_HOME/share/hadoop/mapreduce/hadoop-mapreduce-examples-2.7.3.jar wordcount /input /output
```

### check result

```bash
$HADOOP_HOME/bin/hadoop fs -ls /output
$HADOOP_HOME/bin/hadoop fs -cat /output/part-r-00000
```


