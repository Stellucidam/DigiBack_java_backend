#!/usr/bin/env bash

./update_docker.sh

# shellcheck disable=SC2164
cd docker/topologies/

docker-compose pull

docker-compose up --build

