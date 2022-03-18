#Docker File
FROM openjdk:8-jdk-alpine
ADD /var/lib/jenkins/workspace/Qoentum-Dev/target/qoentum.war qoentum.war
EXPOSE 80
