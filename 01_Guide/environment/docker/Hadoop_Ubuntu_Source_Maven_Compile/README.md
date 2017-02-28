
docker build -t test/hadoop-source-maven -f Dockerfile.Ubuntu.14.04 .

docker run -d -t --name hadoop-source-maven test/hadoop-source-maven &> /dev/null

docker exec -it hadoop-source-maven bash

