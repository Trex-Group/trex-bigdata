
# Flushes and Compactions

1 [HBase shell]
```
create 'mytable', NAME =>'cf', VERSIONS => 3
```

2 [Terminal]
没有数据添加，所以没有 storefile
```
$ hdfs dfs -ls /hbase
$ hdfs dfs -ls -R /hbase/data/default/mytable
$ hdfs dfs -ls /hbase/data/default/mytable/*/cf
```

3 [HBase shell]
```
put 'mytable', 'row1','cf:col', 'foo'
```

4 [Terminal]
确认数据在 memstore 中存储，没有保存到文件
```
$ hdfs dfs -ls /hbase/data/default/mytable/*/cf
```

5 [HBase shell]
```
flush 'mytable'
```

6 [Terminal]
确认前面的 flush 操作，把数据从 memstore 中写到 store file 中
```
$ hdfs dfs -ls /hbase/data/default/mytable/*/cf
```


7 [HBase shell]
```
put 'mytable', 'row2','cf:col', 'bar'
flush 'mytable'
```

8 [Terminal]
第二次执行 flush 将数据从 memstore 写入到一个新 storefile 中
```
$ hdfs dfs -ls /hbase/data/default/mytable/*/cf
```

读取列表中的文件，可以查看文件中所有的KV存储
```
$ hbase hfile --printkv --file hdfs://localhost:8020/<fullpath from8>
```

9 [HBase shell]
```
major_compact 'mytable'
```

10 [Terminal]
Major Compact 把小的 Store files 合并到一个大的 store file 中，可以在这个 store file 中看到所有的KV存储
```
$ hdfs dfs -ls /hbase/data/default/mytable/*/cf
$ hbase hfile --printkv --file hdfs://localhost:8020/<path>
```

11 [Terminal]
显示 RegionServer 的 WAL
```
$ hdfs dfs -ls /hbase/WALs/*/
```

12 [Terminal]
查看 hbase hlog
```
hbase hlog hdfs://localhost:8020/<walpath>
```

# Compactions and Data

1 在 hbase shell 中添加一条新记录
```
Table name: 'mytable'
Row key: 'row3'
Column Family: 'cf'
Column Descriptor: 'col' and value 'row 3 value'
```

2 执行 flush 和 major compaction
```
flush 'mytable'
major_compact 'mytable'
```

3 显示 column family 中的文件
```
hdfs dfs -ls /hbase/data/default/mytable/*/cf
```

4 使用 hfile -printkv 确认 row3 数据是否存在
```
hbase hfile --printkv --file hdfs://localhost:8020/<path>
```

5 删除刚才添加的记录
```
delete 'mytable','row3'
```

6 执行 flush 使刚才删除操作生效
```
flush 'mytable'
```

7 使用 hfile --printkv 确认 row3 是否仍存在
```
hbase hfile --printkv --file hdfs://localhost:8020/<path>
```

8 执行 major compaction
```
major_compact 'mytable'
```

9 显示 column family 中的文件
```
hdfs dfs -ls /hbase/data/default/mytable/*/cf
```

10 使用 hfile -printkv 确认 row3 数据是否存在
```
hbase hfile --printkv --file hdfs://localhost:8020/<path
```

11 重复添加4条新记录
```
Table name: 'mytable'
Row key: 'row4'
Column Family: 'cf'

Column Descriptor: 'col' and value 'first time'
Column Descriptor: 'col' and value 'second time'
Column Descriptor: 'col' and value 'third time'
Column Descriptor: 'col' and value 'fourth time'
```

12 执行 flush 操作
```
flush 'mytable'
```

13 执行 major compaction
```
major_compact 'mytable'
```

14 显示 column family 中的文件
```
hdfs dfs -ls /hbase/data/default/mytable/*/cf
```

15 使用 hfile --printkv 验证 row4 是否只有3个值
```
hbase hfile --printkv --file hdfs://localhost:8020/<path
```
