FROM openjdk:8-jdk-alpine
ENV MYSQL_HOST=mysql
EXPOSE 8083
ADD target/qoentum.war qoentum.war
ENTRYPOINT ["java","-jar","/qoentum.war"]
