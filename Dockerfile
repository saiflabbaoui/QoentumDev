#Docker File
FROM openjdk:8-jdk-alpine
ADD /var/lib/jenkins/workspace/Test/target/qoentum.war qoentum.war
EXPOSE 80
