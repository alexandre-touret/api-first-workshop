#!/bin/sh

echo "Pulling & Running OASDIFF"
docker run --rm -t tufin/oasdiff $@
