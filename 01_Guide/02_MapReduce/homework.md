
# 导入 Map-Reduce VirtualBox Image
1) 复制第二课的 Hadoop VirtualBox Image（Trex_02_Hadoop_MapReduce中的两个节点） 到自己的电脑上<br>
2) 在 VirtualBox 环境中，导入 Hadoop VirtualBox Image（Hadoop-Develop 和 Hadoop-Server）<br>
3) 根据自己的硬件环境，调整虚拟机节点的内存，网卡名称，ip地址<br>
4) 开启2个虚拟机节点（Hadoop-Develop，Hadoop-Server）<br>
5) 通过宿主机 ping 通2个虚拟机节点，ssh 连接到2个虚拟机节点<br>
6) 在 Hadoop-Develop 虚拟机中，打开 IDEA IDE，运行 WordCount<br>
   分别运行 MapReduce on LocalHost 和 MapReduce on Remote<br>

# 自己搭建 Map-Reduce 开发环境
1) 安装 Ubuntu Desktop 14.04 操作系统<br>
https://github.com/trex-group/Big-Data/blob/master/01_Guide/environment/Manual/OS_Ubuntu_Desktop_14.04.pdf<br>
2) 配置 Hadoop 开发环境<br>
JDK，Maven，IDEA（根据自己的能力追加 Eclipse，Gradle）<br>
https://github.com/trex-group/Big-Data/blob/master/01_Guide/environment/Manual/Hadoop_Develop.pdf<br>
https://github.com/trex-group/Big-Data/blob/master/01_Guide/environment/Manual/Maven.pdf<br>
https://github.com/trex-group/Big-Data/blob/master/01_Guide/environment/Manual/Gradle.pdf<br>
3) 运行 WordCount，确认运行结果<br>
4) 确保在上面环境正常运行的前提下<br>
利用第一课提供的 Hadoop VirtualBox Image 搭建 Hadoop 集群（如果硬件资源不够，可将3个节点合并为1个节点）<br>
参照第二课提供的 VirtualBox Image ，尝试将 Map-Reduce 任务提交到远程 Hadoop 集群<br>

# KPI 统计
数据素材 access_log.txt 中包含某网站一天的日志文件<br>
使用Map-Reduce编程（或结合其它方法）计算<br>
1) 根据上述日志文件，计算该天的独立ip数，pv数（注意要筛选日志，并非每条记录都要统计），被传输页面的总字节数<br>
2) 统计来源网站，列出域名及带来的独立ip数<br>
3) 统计用户使用的浏览器种类，计算出各种浏览器占的百分比<br>

# KPI 统计
数据素材 oracle_scott.txt 中包含Oracle数据库初始提供的两个表数据<br>
用适当的方式导入hadoop（来自Oracle数据库的样板表，可考虑分成2个文件存放，注意空值的处理）<br>
开发Map-Reduce程序，求出以下结果<br>
1) 求各个部门的总工资<br>
2) 求各个部门的人数和平均工资<br>
3) 求每个部门最早进入公司的员工姓名<br>
4) 求各个城市的员工的总工资<br>
5) 列出工资比上司高的员工姓名及其工资<br>
6) 列出工资比公司平均工资要高的员工姓名及其工资<br>
7) 列出名字以J开头的员工姓名及其所属部门名称<br>
8) 列出工资最高的头三名员工姓名及其工资<br>

# LBS 预测
数据素材 gps1.txt 中包含某人的基站数据的汇总信息（通过某运营商收集）<br>
1) 通过排除短时间内的地点移动，将基站数据转换为某时间段内的最长停留信息（例如：08-18，19-22，23-07）<br>
2) 根据时间段最长停留信息，推断离开A地点，最有可能的目的地<br>

# Pig
尝试部署Pig，并完成WordCount<br>
参考资料<br>
https://github.com/trex-group/Big-Data/blob/master/01_Guide/environment/Manual/Pig_Ubuntu_14.04.pdf<br>
https://cwiki.apache.org/confluence/display/PIG/PiggyBank<br>
https://datafu.incubator.apache.org/<br>
http://linkedin.github.io/datafu/<br>

