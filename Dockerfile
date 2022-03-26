FROM openjdk:8-jdk-alpine
ENV MYSQL_HOST=mysql
EXPOSE 8083
ADD /var/lib/jenkins/jobs/Qoentum/workspace/target/qoentum.war qoentum.war
ENTRYPOINT ["java","-war","/qoentum.war"]