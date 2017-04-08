# Spark VirtualBox Image - Standalone

难度等级：☆<br>
预计时间：30 minus<br>

1) 复制 Spark VirtualBox Image（Trex_06_Spark 目录下的三个节点） 到自己的电脑上<br>
2) 在 VirtualBox 环境中，导入 Spark VirtualBox Image<br>
3) 根据自己的硬件环境，调整虚拟机节点的内存，物理网卡名称（可能需要刷新 MAC ADDRESS ）<br>
4) 开启3个虚拟机节点（Master，Worker-1，Worker-2）<br>
5) 通过主机 ping 通3个虚拟机节点，ssh 连接到3个虚拟机节点<br>
6) 运行 example <br>

# Spark Source Compile

难度等级：☆<br>
预计时间：60 minus<br>

1) 在 Docker 环境下，使用 Spark_Ubuntu_Source_Maven_Compile 提供的脚本<br>
搭建Spar 的 Maven 编译环境，并按照 README.md 完成代码编译过程<br>
https://github.com/trex-group/Big-Data/tree/master/01_Guide/environment/docker/Spark_Ubuntu_Source_Maven_Compile<br>
2) 在 Docker 环境下，使用 Spark_Ubuntu_Source_Sbt_Compile 提供的脚本<br>
搭建Spar 的 Sbt 编译环境，并按照 README.md 完成代码编译过程<br>
https://github.com/trex-group/Big-Data/tree/master/01_Guide/environment/docker/Spark_Ubuntu_Source_Sbt_Compile<br>

# Spark On Yarn

难度等级：★★<br>
预计时间：2 day at least<br>

# Spark On Mesos

难度等级：★★<br>
预计时间：2 day at least<br>


