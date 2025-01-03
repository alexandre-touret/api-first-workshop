#!/bin/bash

echo "Pulling & Running OASDIFF"
docker run -ti --rm -v ${PWD}:/data:ro tufin/oasdiff $@
