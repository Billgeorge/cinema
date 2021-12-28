FROM adoptopenjdk/openjdk11:alpine-jre
MAINTAINER jorge.espinosa
COPY target/movie-service-0.0.1-SNAPSHOT.jar movie-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/movie-service-0.0.1-SNAPSHOT.jar"]