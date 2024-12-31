#!/bin/sh

echo "Pulling & Running ASYNCAPI"

docker run --rm -it --user=root -v ${PWD}/src:/app/example -v ${PWD}/target:/app/output asyncapi/cli generate models java /app/example/main/resources/asyncapi/supplychain-asyncapi.yaml --packageName=info.touret.guitarheaven.infrastructure.kafka.generated -o /app/output/generated-sources/asyncapi/info/touret/guitarheaven/infrastructure/kafka/generated  --javaJackson --javaConstraints
