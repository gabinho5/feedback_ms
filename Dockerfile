FROM openjdk:8-jre-alpine

USER root

ADD ./target/swaggerpetstorefeedbackmicroservice-0.1-SNAPSHOT.jar /opt/swaggerpetstorefeedbackmicroservice-0.1-SNAPSHOT.jar
CMD ["java", "-Xmx200m", "-jar", "/opt/swaggerpetstorefeedbackmicroservice-0.1-SNAPSHOT.jar"]

EXPOSE 8080

