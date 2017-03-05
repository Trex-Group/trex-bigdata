
# Build images
```bash
docker-compose -f docker-compose.image.yaml build
```

# Startup Container
```bash
# build & run

docker-compose -f docker-compose.hadoop-container.yaml up -d

docker-compose up -d
docker-compose run -d
```

# Clean up
```bash
docker-compose stop
docker-compose rm

docker-compost -f down
```

# master container

```bash
# test
docker exec -it master.trex.com bash

cd ~
/root/start-sshd.sh
/root/configure-members.sh
/root/start-hadoop.sh
```

# slave1 container

```bash
docker exec -it slave1.trex.com bash
```

```bash
cd ~
/root/start-sshd.sh
```

# slave2 container
```bash
docker exec -it slave2.trex.com bash
```

```bash
cd ~
/root/start-sshd.sh

```

