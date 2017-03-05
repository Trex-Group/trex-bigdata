# Virtual Box Env
安装Virtual Box虚拟机环境<br>

# Hadoop VirtualBox Image
复制 Hadoop VirtualBox Image 到自己的电脑上<br>
在 VirtualBox 环境中，导入 Hadoop VirtualBox Image<br>
根据自己的硬件环境，调整虚拟机节点的内存，网卡名称，ip地址<br>
开启3个虚拟机节点（NameNode，DataNode-1，DataNode-2）<br>
通过主机 ping 通3个虚拟机节点，ssh 连接到3个虚拟机节点<br>
运行 WordCount<br>

# Hadoop VirtualBox Image Customize
在三个虚拟机节点，新建 hadoop 用户<br>
在三个虚拟机节点之间，配置 hadoop 用户的无密码登陆<br>
修改环境变量 ~/.bashrc<br>
修改 /opt 中 hadoop 相关资源的拥有者为 hadoop<br>
修改 core-site.xml，hdfs-site.xml，调整数据保留为 ~/hadoop-data/ 目录<br>
格式化 HDFS 系统<br>
启动 HDFS，Yarn<br>
确认 Web UI 的运行状态<br>
运行 WordCount<br>

# CentOS
新建3个虚拟机节点，安装 CentOS 操作系统<br>
按照 NameNode，DataNode-1，DataNode-2 方式进行 Hadoop 部署<br>

# Docker Env
安装 Docker 环境<br>

# Hadoop Docker Container
```bash
git clone https://github.com/trex-group/Big-Data.git
cd Big-Data/01_Guide/environment/docker/Hadoop_Ubuntu_Bin/
```
按照下面页面进行测试<br>
https://github.com/trex-group/Big-Data/tree/master/01_Guide/environment/docker/Hadoop_Ubuntu_Bin<br>

# Cloudera
部署 QuickStart VirtualBox Image<br>
https://www.cloudera.com/downloads/quickstart_vms/5-8.html<br>
https://downloads.cloudera.com/demo_vm/virtualbox/cloudera-quickstart-vm-5.8.0-0-virtualbox.zip<br>

