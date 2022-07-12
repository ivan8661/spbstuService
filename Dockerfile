FROM maven:3.8.6-openjdk-18 as build
WORKDIR /spbstuService
COPY * /spbstuService/

RUN mvn clean -X -Dmaven.test.skip

FROM openjdk:18.0.1.1-jdk-oraclelinux7
COPY --from=build /spbstuService/target /spbstuService
RUN java -jar /spbstuService/spbstu.jar
EXPOSE 8080
