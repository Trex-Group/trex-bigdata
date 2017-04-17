
# Create Image
# Spark 1.6.3 & Hadoop 2.6.5
docker build -t test/spark-source-maven -f Dockerfile.Ubuntu.14.04.Spark.1.6 .
# Spark 2.1.0 & Hadoop 2.7.3
docker build -t test/spark-source-maven -f Dockerfile.Ubuntu.14.04.Spark.2.1 .

# Start Container
docker run -d -t --name spark-source-maven test/spark-source-maven &> /dev/null

# Run a command in a running container
docker exec -it spark-source-maven bash


