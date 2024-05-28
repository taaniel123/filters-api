FROM openjdk:17-jdk-slim

EXPOSE 9900

ADD build/libs/filters.jar /opt/
