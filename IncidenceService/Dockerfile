FROM openjdk:17.0.1-jdk-slim
ADD build/libs/IncidenceService-1.0-SNAPSHOT.jar incidenceService.jar
ENTRYPOINT [ "java", "-jar",  "incidenceService.jar"]