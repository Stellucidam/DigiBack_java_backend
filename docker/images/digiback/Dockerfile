# FROM openjdk:8-jdk-alpine # Erreur de version !
FROM openjdk:11-jre
COPY wait/wait-for-it.sh wait-for-it.sh
COPY artifact/app.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]
