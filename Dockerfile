FROM openjdk:18.0.1.1-jdk-oraclelinux7
MAINTAINER Ivan Poltorakov <ivan@poltorakov.ru>
ADD ./target/spbstu.jar spbstu.jar
ENTRYPOINT java -jar spbstu.jar spbstu
EXPOSE 8071
