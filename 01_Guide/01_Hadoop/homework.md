# Virtual Box Env
安装Virtual Box虚拟机环境

# Hadoop VirtualBox Image
复制 Hadoop VirtualBox Image 到自己的电脑上
在 VirtualBox 环境中，导入 Hadoop VirtualBox Image
根据自己的硬件环境，调整虚拟机节点的内存
开启3个虚拟机节点（NameNode，DataNode-1，DataNode-2）
运行 WordCount

# Hadoop VirtualBox Image Customize
在三个虚拟机节点，新建 hadoop 用户
在三个虚拟机节点之间，配置 hadoop 用户的无密码登陆
修改环境变量 ~/.bashrc
修改 /opt 中 hadoop 相关资源的拥有着为 hadoop
修改 core-site.xml，hdfs-site.xml，调整数据保留为 ~/hadoop-data/ 目录
格式化 HDFS 系统
启动 hdfs，yarn
确认 webui 的运行状态
运行 workcount

# CentOS
新建3个虚拟机节点，安装 CentOS 操作系统
按照 NameNode，DataNode-1，DataNode-2 方式进行 Hadoop 部署

# Docker Env
安装 Docker 环境

# Hadoop Docker Container
git clone https://github.com/trex-group/Big-Data.git
cd Big-Data/01_Guide/environment/docker/Hadoop_Ubuntu_Bin/

按照下面页面进行测试
https://github.com/trex-group/Big-Data/tree/master/01_Guide/environment/docker/Hadoop_Ubuntu_Bin



