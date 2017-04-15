
# MIN_VERSIONS & Time-To-Live

1 启动hbase shell
```
hbase>hbase shell
```
2 创建一张表，table name: tbl_1, column family: cf1
```
hbase>create 'tbl_1','cf1'
```
3 确认是否创建成功
```
hbase>list
```
4 显示刚创建表的属性，注意TTL=FOREVER
```
hbase>describe'tbl_1',
```
5 把TTL值改为60秒
```
hbase>alter 'tbl_1',NAME=>'cf1',TTL=>60
```
6 验证TTL属性修改是否成功
```
hbase>describe 'tbl_1'
```
7 插入一行数据
```
hbase>put 'tbl_1','rowkey1','cf1:cq1','Row1'
```
8 显示表数据
```
hbase>scan 'tbl_1'
```
9 等待60秒，再执行scan命令
```
hbase>scan 'tbl_1'
```
10 创建一个新表，TTL=10,MIN_VERSIONS=1
```
hbase>create 'tbl_2',NAME=>'cf2',TTL=>10,MIN_VERSIONS=>1
```
11 验证TTL和MIN_VERSIONS的值
```
hbase>discribe 'tbl_2'
```
12 执行scan,查看表中是否有数据
```
hbase>scan 'tbl_2'
```
13 插入一条数据
```
put 'tbl_2','rowkey2','cf2:cq2','cf2 cq2 row 1'
```
14 显示数据
```
hbase>scan 'tbl_2'
```
15 等超过10秒，执行scan，看数据是否被删除
```
hbase>scan 'tbl_2'
```
16 把MIN_VERSIONS值改成0
```
hbase> alter 'tbl_2', NAME => 'cf2', MIN_VERSIONS => 0
```
17 再次执行scan，看数据是否还存在
```
hbase>scan 'tbl_2'
```
18 清除测试表
```
hbase> disable 'tbl_1'
hbase> drop 'tbl_1'
hbase> disable 'tbl_2'
hbase> drop 'tbl_2'
```

# Java API

难度等级：★★<br>
预计时间：4 hours<br>

尝试通过 Java API 连接 HBase<br>
http://hbase.apache.org/book.html#jdo<br>

# Row Key 的简单实现

难度等级：★★<br>
预计时间：4 hours<br>

实现 Row Key 的一种非默认方式（Hash 方式 或其他）


