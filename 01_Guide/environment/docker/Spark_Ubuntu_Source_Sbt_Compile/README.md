
docker build -t test/spark-source-sbt -f Dockerfile.Ubuntu.14.04 .

docker run -d -t --name spark-source-sbt test/spark-source-sbt &> /dev/null

docker exec -it spark-source-sbt bash

