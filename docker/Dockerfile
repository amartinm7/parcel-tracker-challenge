FROM openjdk:8-jdk-alpine

MAINTAINER Antonio Martin

EXPOSE 8085

COPY build/libs/lib/*.jar build/libs/*.jar /usr/local/tracking/

WORKDIR /usr/local/tracking

CMD java -Xmx512m -cp "*" org.springframework.boot.loader.JarLauncher