call mvn clean package
docker build --tag spbstu .
docker-compose up
docker image prune -a