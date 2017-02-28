#!/bin/bash

tag="latest"

# N is the node number of the cluster
N=$1

if [ $# = 0 ]
then
	echo "Please use the node number of the cluster as the argument!"
	exit 1
fi

cd hadoop-master

# change the slaves file
echo "master.test.com" > files/slaves
i=1
while [ $i -lt $N ]
do
	echo "slave$i.test.com" >> files/slaves
	((i++))
done 

# delete master container
docker rm -f master 

# delete hadoop-master image
docker rmi test/hadoop-master:$tag 

# rebuild hadoop-docker image
pwd
docker build -t test/hadoop-master:$tag .

