#!/bin/sh

echo "Pulling & Running ASYNCAPI"

docker run --rm -it --user=root -v ${PWD}/src:/app/example -v ${PWD}/target:/app/output asyncapi/cli validate /app/example/main/resources/asyncapi/supplychain-asyncapi.yaml
