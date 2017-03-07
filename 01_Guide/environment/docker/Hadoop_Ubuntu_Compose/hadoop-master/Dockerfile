FROM trex/hadoop-base:latest
MAINTAINER xenron <xenron@hotmail.com>

# move all confugration files into container
ADD files/hadoop/* /tmp/
ADD files/init/* /tmp/

ENV HADOOP_INSTALL /opt/hadoop

RUN mkdir -p ~/hdfs/namenode && \
mkdir -p ~/zookeeper && \ 
mkdir -p ~/hdfs/datanode

RUN mv /tmp/hdfs-site.xml $HADOOP_INSTALL/etc/hadoop/hdfs-site.xml && \ 
mv /tmp/core-site.xml $HADOOP_INSTALL/etc/hadoop/core-site.xml && \
mv /tmp/mapred-site.xml $HADOOP_INSTALL/etc/hadoop/mapred-site.xml && \
mv /tmp/yarn-site.xml $HADOOP_INSTALL/etc/hadoop/yarn-site.xml && \
mv /tmp/stop-hadoop.sh ~/stop-hadoop.sh && \
mv /tmp/start-hadoop.sh ~/start-hadoop.sh && \
mv /tmp/run-wordcount.sh ~/run-wordcount.sh && \ 
# mv /tmp/start-sshd.sh ~/start-sshd.sh  && \ 
mv /tmp/configure-members.sh ~/configure-members.sh 

RUN chmod +x ~/start-hadoop.sh && \
chmod +x ~/stop-hadoop.sh && \
chmod +x ~/run-wordcount.sh && \
# chmod +x ~/start-sshd.sh && \
chmod +x ~/configure-members.sh  && \
chmod 1777 tmp

# format namenode
RUN /opt/hadoop/bin/hdfs namenode -format

EXPOSE 22 7373 7946 9000 50010 50020 50070 50075 50090 50475 8030 8031 8032 8033 8040 8042 8060 8088 50060

# CMD '/root/start-sshd.sh'; 'bash'
# CMD 'bash'
# CMD ["tail", "-f", "/dev/null"]
# ENTRYPOINT ["sh", "/opt/start-sshd.sh"]
# CMD [ "sh", "-c", "service sshd start; bash"]
ENTRYPOINT ["sh", "/etc/init.d/ssh", "start", "-D"]


