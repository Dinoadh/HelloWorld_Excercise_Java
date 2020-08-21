FROM openjdk:8-jdk
ADD ./target/*.jar helloworld.jar
EXPOSE 8080
CMD ["java", "-jar", "helloworld.jar"]