
# Build images

```bash
docker-compose -f docker-compose.image.yaml build
```

# Startup Hadoop Container

```bash
# build & run

docker-compose -f docker-compose.hadoop-container.yaml up -d

# docker-compose up -d
# docker-compose run -d
```

## Clean up

```bash
docker-compose stop
docker-compose rm

docker-compose -f down
```

## master container

```bash
docker exec -it master.trex.com bash

cd ~
/root/configure-members.sh
/root/start-hadoop.sh

# test
jps
$HADOOP_HOME/bin/hdfs dfsadmin -report
```

## WordCount

### upload files

```bash
$HADOOP_HOME/bin/hadoop fs -mkdir /user
$HADOOP_HOME/bin/hadoop fs -mkdir /user/root
$HADOOP_HOME/bin/hadoop fs -mkdir /user/root/input
$HADOOP_HOME/bin/hadoop fs -ls /
$HADOOP_HOME/bin/hadoop fs -put $HADOOP_HOME/LICENSE.txt /user/root/input
$HADOOP_HOME/bin/hadoop fs -put $HADOOP_HOME/NOTICE.txt /user/root/input
$HADOOP_HOME/bin/hadoop fs -put $HADOOP_HOME/README.txt /user/root/input
$HADOOP_HOME/bin/hadoop fs -ls /user/root/input
```

### run jar

```bash
$HADOOP_HOME/bin/hadoop jar $HADOOP_HOME/share/hadoop/mapreduce/hadoop-mapreduce-examples-2.7.3.jar wordcount /user/root/input /user/root/output
```

### check result

```bash
$HADOOP_HOME/bin/hadoop fs -ls /user/root/output
$HADOOP_HOME/bin/hadoop fs -cat /user/root/output/part-r-00000
```

# Start HBase Container

```bash
# build & run

docker-compose -f docker-compose.hbase-container.yaml up -d

# docker-compose up -d
# docker-compose run -d
```

# Clean up
```bash
docker-compose stop
docker-compose rm

docker-compose -f down
```

## Start Hadoop & HBase in master container

```bash
docker exec -it master.trex.com bash

cd ~
/root/configure-members.sh
/root/start-hadoop.sh

jps
$HADOOP_HOME/bin/hdfs dfsadmin -report
```

## HBase Shell
```
cd ~
./start-hbase.sh
```

```
hbase(main):001:0> $ status
hbase(main):002:0> $ create 'album','label','image'
hbase(main):003:0> $ put 'album','label1','label:size','10'
hbase(main):004:0> $ put 'album','label1','label:color','255:255:255'
hbase(main):005:0> $ put 'album','label1','label:text','Family album'
hbase(main):006:0> $ put 'album','label1','image:name','holiday'
hbase(main):007:0> $ put 'album','label1','image:source','/tmp/pic1.jpg'
hbase(main):008:0> $ get 'album','label1'
```


