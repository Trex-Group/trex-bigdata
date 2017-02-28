
# Create Image
docker build -t test/spark-source-sbt -f Dockerfile.Ubuntu.14.04 .

# Start Container
docker run -d -t --name spark-source-sbt test/spark-source-sbt &> /dev/null

# Run a command in a running container
docker exec -it spark-source-sbt bash

