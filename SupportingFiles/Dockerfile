FROM adoptopenjdk/openjdk16:x86_64-alpine-jre16u-nightly
MAINTAINER Ivan Poltorakov <ivan@poltorakov.ru>
ADD ../target/spbstu.jar spbstu.jar
ENTRYPOINT java -jar spbstu.jar spbstu
EXPOSE 8080