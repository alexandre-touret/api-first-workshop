#!/bin/bash

echo "Pulling & Running OASDIFF"
docker run --rm -t -v ${PWD}:/data:ro tufin/oasdiff $@
