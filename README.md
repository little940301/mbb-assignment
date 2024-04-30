# MBB Assignment

[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

Minimal [Spring Boot](http://projects.spring.io/spring-boot/) sample app.

## Purpose

This MBB Assignment Service is a springboot JAVA Backend Interview Assignment

## Requirements

For building and running the application you need:

- [JDK 21](https://www.oracle.com/java/technologies/downloads/#java11)
- [Maven 3](https://maven.apache.org)

## Running the application locally

Utilising [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

Navigate to project folder
```shell
mvn clean install
```
Navigate to project's target folder
```shell
java -jar -Dspring.profiles.active=local mbb-assignment-service-X.X.X.jar
```

This will create:

* A Jar called "mbb-assignment-service.jar"
* Upload the jar to all the staging server
* Stop the existing running service
* Start the service with latest Jar

## Reference
* [Spring Framework](https://spring.io/projects/spring-framework)
* [Spring Data](https://spring.io/projects/spring-data)

## Copyright
Released under the Apache License 2.0. See the [LICENSE](https://github.com/codecentric/springboot-sample-app/blob/master/LICENSE) file.

