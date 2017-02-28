
docker build -t test/spark-source-maven -f Dockerfile.Ubuntu.14.04 .

docker run -d -t --name spark-source-maven test/spark-source-maven &> /dev/null

docker exec -it spark-source-maven bash


