FROM openjdk:19
COPY target/marktplaatsAuthentication-0.0.1-SNAPSHOT.jar marktplaatsAuthentication.jar
ENTRYPOINT ["java","-jar","/marktplaatsAuthentication.jar"]