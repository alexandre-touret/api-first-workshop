#!/bin/sh

echo "Pulling & Running VACUUM"
docker run -ti --rm -v $PWD:/work:ro dshanley/vacuum lint $@
