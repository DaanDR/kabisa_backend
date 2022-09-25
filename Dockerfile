FROM openjdk:17.0

COPY target/kabisa_backend-0.0.1-SNAPSHOT.jar kabisa_backend.jar

ENTRYPOINT ["java","-jar","/kabisa_backend.jar"]