FROM openjdk:21
COPY target/moex2-1.0-SNAPSHOT.jar /usr/local/service/
ENTRYPOINT ["java", "-jar", "-Dliquibase.hub.mode=off", "/usr/local/service/moex2-1.0-SNAPSHOT.jar"]
#dev2