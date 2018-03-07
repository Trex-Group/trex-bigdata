FROM trex/base-dnsmasq:latest
MAINTAINER xenron <xenron@hotmail.com>

# install openssh-server
RUN apt-get update && \
  apt-get install -y curl openssh-server nano software-properties-common python-software-properties && \
  apt-get clean -y && apt-get autoclean -y && apt-get autoremove -y && \
  rm -rf /var/lib/{apt,dpkg,cache,log}/

# Java Version
#ENV JAVA_VERSION_MAJOR 8
#ENV JAVA_VERSION_MINOR 111
#ENV JAVA_VERSION_BUILD 14
#ENV JAVA_PACKAGE       jdk

# Download and unarchive Java
#RUN mkdir -p /opt &&\
#    curl -jksSLH "Cookie: oraclelicense=accept-securebackup-cookie"\
#    http://download.oracle.com/otn-pub/java/jdk/${JAVA_VERSION_MAJOR}u${JAVA_VERSION_MINOR}-b${JAVA_VERSION_BUILD}/${JAVA_PACKAGE}-${JAVA_VERSION_MAJOR}u${JAVA_VERSION_MINOR}-linux-x64.tar.gz | gunzip -c - | tar -xf - -C /opt &&\
#    ln -s /opt/jdk1.${JAVA_VERSION_MAJOR}.0_${JAVA_VERSION_MINOR} /opt/jdk &&\
#    rm -rf /opt/jdk/*src.zip \
#         /opt/jdk/lib/missioncontrol \
#         /opt/jdk/lib/visualvm \
#         /opt/jdk/lib/*javafx* \
#         /opt/jdk/jre/lib/plugin.jar \
#         /opt/jdk/jre/lib/ext/jfxrt.jar \
#         /opt/jdk/jre/bin/javaws \
#         /opt/jdk/jre/lib/javaws.jar \
#         /opt/jdk/jre/lib/desktop \
#         /opt/jdk/jre/plugin \
#         /opt/jdk/jre/lib/deploy* \
#         /opt/jdk/jre/lib/*javafx* \
#         /opt/jdk/jre/lib/*jfx* \
#         /opt/jdk/jre/lib/amd64/libdecora_sse.so \
#         /opt/jdk/jre/lib/amd64/libprism_*.so \
#         /opt/jdk/jre/lib/amd64/libfxplugins.so \
#         /opt/jdk/jre/lib/amd64/libglass.so \
#         /opt/jdk/jre/lib/amd64/libgstreamer-lite.so \
#         /opt/jdk/jre/lib/amd64/libjavafx*.so \
#         /opt/jdk/jre/lib/amd64/libjfx*.so 

RUN add-apt-repository ppa:webupd8team/java
RUN apt-get update
RUN echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | sudo /usr/bin/debconf-set-selections
RUN apt-get install -y oracle-java8-installer
RUN apt-get install -y oracle-java8-set-default
RUN echo "JAVA_HOME=/usr/lib/jvm/java-8-oracle" >> /etc/environment
RUN echo "JRE_HOME=/usr/lib/jvm/java-8-oracle/jre" >> /etc/environment

# Set environment
# ENV JAVA_HOME /usr/lib/jvm/java-8-oracle
# ENV PATH ${PATH}:${JAVA_HOME}/bin

# move all configuration files into container
ADD files/* /usr/local/

# set jave environment variable
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle
ENV PATH $PATH:$JAVA_HOME/bin

#configure ssh free key access
RUN mkdir /var/run/sshd && \
ssh-keygen -t rsa -f ~/.ssh/id_rsa -P '' && \
cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys && \
mv /usr/local/ssh_config ~/.ssh/config && \
sed 's@session\s*required\s*pam_loginuid.so@session optional pam_loginuid.so@g' -i /etc/pam.d/sshd

# install hadoop 2.7.5
RUN wget -q -o out.log -P /tmp https://archive.apache.org/dist/hadoop/common/hadoop-2.7.5/hadoop-2.7.5.tar.gz && \
  tar xzf /tmp/hadoop-2.7.5.tar.gz -C /opt && \
  rm /tmp/hadoop-2.7.5.tar.gz && \
  mv /opt/hadoop-2.7.5 /opt/hadoop && \
  mv /usr/local/bashrc ~/.bashrc && \
  mv /usr/local/hadoop-env.sh /opt/hadoop/etc/hadoop/hadoop-env.sh

# install hive 2.1.1
RUN wget -q -o out.log -P /tmp https://archive.apache.org/dist/hive/hive-2.1.1/apache-hive-2.1.1-bin.tar.gz && \
  tar xzf /tmp/apache-hive-2.1.1-bin.tar.gz -C /opt && \
  rm /tmp/apache-hive-2.1.1-bin.tar.gz && \
  mv /opt/apache-hive-2.1.1-bin /opt/hive


