FROM openjdk:11-jre-slim
MAINTAINER NotUpToYou
ARG app
ENV appjar $app
COPY $appjar.jar /app.jar
CMD ["java", "-jar", "app.jar"]
