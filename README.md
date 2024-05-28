# Filters application

## Description
Module for filters and their criteria.

### Dependencies
* Java 17
* Spring Boot 3
* Gradle
* Docker
* PostgreSQL
* Liquibase
* Mybatis

## Compiling / Running

Create JAR:
```bash
$ ./gradlew clean build
```
Start the application containers:
```bash
$ docker compose up -d
```

For unit tests run
```bash
$ ./gradlew clean test
```

Server is running at
`http://localhost:9900`

