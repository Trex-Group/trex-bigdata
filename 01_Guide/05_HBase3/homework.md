
# MIN_VERSIONS & Time-To-Live

1 ����hbase shell
```
hbase>hbase shell
```
2 ����һ�ű�table name: tbl_1, column family: cf1
```
hbase>create 'tbl_1','cf1'
```
3 ȷ���Ƿ񴴽��ɹ�
```
hbase>list
```
4 ��ʾ�մ���������ԣ�ע��TTL=FOREVER
```
hbase>describe'tbl_1',
```
5 ��TTLֵ��Ϊ60��
```
hbase>alter 'tbl_1',NAME=>'cf1',TTL=>60
```
6 ��֤TTL�����޸��Ƿ�ɹ�
```
hbase>describe 'tbl_1'
```
7 ����һ������
```
hbase>put 'tbl_1','rowkey1','cf1:cq1','Row1'
```
8 ��ʾ������
```
hbase>scan 'tbl_1'
```
9 �ȴ�60�룬��ִ��scan����
```
hbase>scan 'tbl_1'
```
10 ����һ���±�TTL=10,MIN_VERSIONS=1
```
hbase>create 'tbl_2',NAME=>'cf2',TTL=>10,MIN_VERSIONS=>1
```
11 ��֤TTL��MIN_VERSIONS��ֵ
```
hbase>discribe 'tbl_2'
```
12 ִ��scan,�鿴�����Ƿ�������
```
hbase>scan 'tbl_2'
```
13 ����һ������
```
put 'tbl_2','rowkey2','cf2:cq2','cf2 cq2 row 1'
```
14 ��ʾ����
```
hbase>scan 'tbl_2'
```
15 �ȳ���10�룬ִ��scan���������Ƿ�ɾ��
```
hbase>scan 'tbl_2'
```
16 ��MIN_VERSIONSֵ�ĳ�0
```
hbase> alter 'tbl_2', NAME => 'cf2', MIN_VERSIONS => 0
```
17 �ٴ�ִ��scan���������Ƿ񻹴���
```
hbase>scan 'tbl_2'
```
18 ������Ա�
```
hbase> disable 'tbl_1'
hbase> drop 'tbl_1'
hbase> disable 'tbl_2'
hbase> drop 'tbl_2'
```

# Java API

�Ѷȵȼ������<br>
Ԥ��ʱ�䣺4 hours<br>

����ͨ�� Java API ���� HBase<br>
http://hbase.apache.org/book.html#jdo<br>

# Row Key �ļ�ʵ��

�Ѷȵȼ������<br>
Ԥ��ʱ�䣺4 hours<br>

ʵ�� Row Key ��һ�ַ�Ĭ�Ϸ�ʽ��Hash ��ʽ ��������


