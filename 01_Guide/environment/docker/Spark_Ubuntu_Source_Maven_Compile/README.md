
# Create Image
docker build -t test/spark-source-maven -f Dockerfile.Ubuntu.14.04 .

# Start Container
docker run -d -t --name spark-source-maven test/spark-source-maven &> /dev/null

# Run a command in a running container
docker exec -it spark-source-maven bash


