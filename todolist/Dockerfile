FROM openjdk:11-oracle
ENV SERVER_PORT 80
EXPOSE $SERVER_PORT
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENV TZ=Europe/Moscow
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ENTRYPOINT java -jar /app.jar   --server.port=$SERVER_PORT