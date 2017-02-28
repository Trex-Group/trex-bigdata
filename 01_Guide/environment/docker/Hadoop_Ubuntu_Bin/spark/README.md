# Spark
```
# build image
docker --tls build  -t test/spark-base .

# Run Demo
docker run --rm -it -p 4040:4040 test/spark-base bin/run-example SparkPi 10
```

