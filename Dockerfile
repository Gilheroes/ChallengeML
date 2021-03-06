# Java 11, try this
FROM adoptopenjdk/openjdk11:alpine-jre

# Refer to Maven build -> finalName
ARG JAR_FILE=target/snap-0.0.1-SNAPSHOT.jar
ARG DB_FILE=challengeML.db

# cd /opt/app
WORKDIR /opt/app

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar
COPY ${DB_FILE} challengeML.db

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]