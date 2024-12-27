#!/bin/sh

echo "Pulling & Running VACUUM"
docker run --rm -v $PWD:/work:ro dshanley/vacuum lint $@
