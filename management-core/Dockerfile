FROM adoptopenjdk/openjdk11:alpine-jre
MAINTAINER jorge.espinosa
COPY target/management-core-0.0.1-SNAPSHOT.jar management-core-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/management-core-0.0.1-SNAPSHOT.jar"]