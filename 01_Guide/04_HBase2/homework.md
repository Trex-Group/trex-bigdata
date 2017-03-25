
# Flushes and Compactions

1 [HBase shell]
```
create 'mytable', NAME =>'cf', VERSIONS => 3
```

2 [Terminal]
û��������ӣ�����û�� storefile
```
$ hdfs dfs -ls /hbase
$ hdfs dfs -ls -R /hbase/data/default/mytable
$ hdfs dfs �Cls /hbase/data/default/mytable/*/cf
```

3 [HBase shell]
```
put 'mytable', 'row1','cf:col', 'foo'
```

4 [Terminal]
ȷ�������� memstore �д洢��û�б��浽�ļ�
```
$ hdfs dfs �Cls /hbase/data/default/mytable/*/cf
```

5 [HBase shell]
```
flush 'mytable'
```

6 [Terminal]
ȷ��ǰ��� flush �����������ݴ� memstore ��д�� store file ��
```
$ hdfs dfs �Cls /hbase/data/default/mytable/*/cf
```


7 [HBase shell]
```
put 'mytable', 'row2','cf:col', 'bar'
flush 'mytable'
```

8 [Terminal]
�ڶ���ִ�� flush �����ݴ� memstore д�뵽һ���� storefile ��
```
$ hdfs dfs �Cls /hbase/data/default/mytable/*/cf
```

��ȡ�б��е��ļ������Բ鿴�ļ������е�KV�洢
```
$ hbase hfile --printkv --file hdfs://localhost:8020/<fullpath from8>
```

9 [HBase shell]
```
major_compact 'mytable'
```

10 [Terminal]
Major Compact ��С�� Store files �ϲ���һ����� store file �У���������� store file �п������е�KV�洢
```
$ hdfs dfs -ls /hbase/data/default/mytable/*/cf
$ hbase hfile --printkv --file hdfs://localhost:8020/<path>
```

11 [Terminal]
��ʾ RegionServer �� WAL
```
$ hdfs dfs -ls /hbase/WALs/*/
```

12 [Terminal]
�鿴 hbase hlog
```
hbase hlog hdfs://localhost:8020/<walpath>
```

# Compactions and Data

1 ��hbase shell�����һ���¼�¼
```
Table name: 'mytable'
Row key: 'row3'
Column Family: 'cf'
Column Descriptor: 'col' and value 'row 3 value'
```

2 ִ��flush ��major compaction
```
flush 'mytable'
major_compact 'mytable'
```

3 ��ʾcolumn family�е��ļ�
```
hdfs dfs -ls /hbase/data/default/mytable/*/cf
```

4 ʹ��hfile �Cprintkvȷ��row3�����Ƿ����
```
hbase hfile --printkv --file hdfs://localhost:8020/<path>
```

5 ɾ���ղ���ӵļ�¼
```
delete 'mytable','row3'
```

6 ִ��flush,ʹ�ղ�ɾ��������Ч
```
flush 'mytable'
```

7 ʹ��hfile �Cprintkvȷ��row3�Ƿ��Դ���
```
hbase hfile --printkv --file hdfs://localhost:8020/<path>
```

8 ִ��major compaction
```
major_compact 'mytable'
```

9 ��ʾcolumn family�е��ļ�
```
hdfs dfs -ls /hbase/data/default/mytable/*/cf
```

10 ʹ��hfile �Cprintkvȷ��row3�����Ƿ����
```
hbase hfile --printkv --file hdfs://localhost:8020/<path
```

11 �ظ����4���¼�¼
```
Table name: 'mytable'
Row key: 'row4'
Column Family: 'cf'

Column Descriptor: 'col' and value 'first time'
Column Descriptor: 'col' and value 'second time'
Column Descriptor: 'col' and value 'third time'
Column Descriptor: 'col' and value 'fourth time'
```

12 ִ��flush����
```
flush 'mytable'
```

13 ִ��major compaction
```
major_compact 'mytable'
```

14 ��ʾcolumn family�е��ļ�
```
hdfs dfs -ls /hbase/data/default/mytable/*/cf
```

15 ʹ��hfile �Cprintkv����֤row4�Ƿ�ֻ��3��ֵ
```
hbase hfile --printkv --file hdfs://localhost:8020/<path
```
