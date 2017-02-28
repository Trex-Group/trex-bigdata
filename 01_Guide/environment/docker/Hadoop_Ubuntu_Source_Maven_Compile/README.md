
# Create Image
docker build -t test/hadoop-source-maven -f Dockerfile.Ubuntu.14.04 .

# Start Container
docker run -d -t --name hadoop-source-maven test/hadoop-source-maven &> /dev/null

# Run a command in a running container
docker exec -it hadoop-source-maven bash

