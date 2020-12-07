FROM adoptopenjdk/openjdk11:latest
EXPOSE 8080
ADD /target/ecommapp-0.1.jar ecommapp.jar 
ENTRYPOINT ["java","-jar","ecommapp.jar"]
