#!/bin/bash

moduleName="spbstu"
projectName="schedapp"
dockerRegistry="docker.kidev.ru"

exportDirectory="target/docker/$moduleName"
imageName="$dockerRegistry/$projectName/$moduleName"

exportDirectory="target/docker/$moduleName"
buildBranch=$(git rev-parse --abbrev-ref HEAD | tr -d '-'| tr -d '/')
buildNumber=$(git rev-list HEAD | wc -l | tr -d ' ')
buildVersion="$buildBranch.$buildNumber"

mvn -Dmaven.test.skip=true -e clean package

echo Y | docker image prune
docker build -t $imageName:latest \
             -t $imageName:$buildVersion .
mkdir -p ./$exportDirectory
echo Y | docker image prune

docker save -o ./$exportDirectory/image.tar $dockerRegistry/$projectName/$moduleName
cp ./docker-compose.yml ./$exportDirectory/docker-compose.yml