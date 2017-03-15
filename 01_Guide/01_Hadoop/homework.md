# Virtual Box Env

难度等级：☆<br>
预计时间：30 minus<br>

安装Virtual Box虚拟机环境<br>

# Hadoop VirtualBox Image

难度等级：☆<br>
预计时间：30 minus<br>

1) 复制 Hadoop VirtualBox Image（Trex_01_Hadoop目录下的三个节点） 到自己的电脑上<br>
2) 在 VirtualBox 环境中，导入 Hadoop VirtualBox Image<br>
3) 根据自己的硬件环境，调整虚拟机节点的内存，物理网卡名称（可能需要刷新 MAC ADDRESS ）<br>
4) 开启3个虚拟机节点（NameNode，DataNode-1，DataNode-2）<br>
5) 通过主机 ping 通3个虚拟机节点，ssh 连接到3个虚拟机节点<br>
6) 运行 WordCount<br>

# Hadoop VirtualBox Image Customize

难度等级：★★<br>
预计时间：2 hours<br>

1) 开启3个虚拟机节点（NameNode，DataNode-1，DataNode-2）<br>
2) 分别在三个虚拟机节点，新建 hadoop 用户，密码任意（可以和用户名相同）<br>
3) 在三个虚拟机节点之间，配置 hadoop 用户的无密码登陆<br>
4) 在每个虚拟机节点上确认 hadoop 用户的 ssh 免密码登陆<br>
ssh 本节点主机名，ssh 其他节点A主机名，ssh 其他节点B主机名<br>
5) 在用户自动加载脚本中 ~/.bashrc 中添加 hadoop 需要使用的系统环境变量<br>
可参照 /root/.bashrc<br>
6) 修改 /opt 中 hadoop 相关资源的拥有者为 hadoop<br>
7) 修改 core-site.xml，hdfs-site.xml，调整数据保留为 /home/xenron/hadoop-data/ 目录<br>
8) 格式化 HDFS 系统<br>
9) 启动 HDFS，启动 Yarn<br>
10) 确认各 Web UI 的运行状态<br>
11) 运行 WordCount<br>
12) 确认运行结果<br>

# CentOS Hadoop 环境配置

难度等级：★★<br>
预计时间：4 hours<br>

1) 在 VirtualBox 软件环境中，新建3个虚拟机节点<br>
2) 在新建立的3个虚拟机节点中，分别安装 CentOS 6.7 操作系统<br>
参考资料<br>
https://github.com/trex-group/Big-Data/blob/master/01_Guide/environment/Manual/OS_CentOS_6.7.pdf<br>
3) 按照 NameNode，DataNode-1，DataNode-2 方式进行 Hadoop 集群环境部署<br>
参考资料<br>
（下面资料为Ubuntu环境下Hadoop的搭建方法，CentOS的软件安装方法稍有区别，需要自己查询相关资料）<br>
https://github.com/trex-group/Big-Data/blob/master/01_Guide/environment/Manual/Hadoop_Ubuntu_14.04.pdf<br>

# Docker 环境部署

难度等级：★<br>
预计时间：30 minus<br>

1) 安装 Docker 环境<br>
参考资料<br>
https://github.com/trex-group/Big-Data/blob/master/01_Guide/01_Hadoop/docker.pdf<br>
2) 确认 Docker 环境正常运行<br>
参考资料<br>
输入 docker ，确认是否有帮助信息返回<br>

# Hadoop Docker Container

1) 按照下面命令，下载 docker 环境脚本<br>
```bash
git clone https://github.com/trex-group/Big-Data.git
cd Big-Data/01_Guide/environment/docker/Hadoop_Ubuntu_Bin/
```
2) 按照下面页面进行部署、确认<br>
https://github.com/trex-group/Big-Data/blob/master/01_Guide/environment/docker/Hadoop_Ubuntu_Bin/README.md<br>


# Cloudera QuickStart 环境部署

难度等级：★<br>
预计时间：30 minus<br>

部署 QuickStart VirtualBox Image<br>
https://www.cloudera.com/downloads/quickstart_vms/5-8.html<br>
https://downloads.cloudera.com/demo_vm/virtualbox/cloudera-quickstart-vm-5.8.0-0-virtualbox.zip<br>

