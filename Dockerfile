FROM gradle:8-jdk17-focal AS BUILD
WORKDIR /opt/src/
COPY . .
RUN gradle clean build

FROM openjdk:17.0
WORKDIR /opt/src
COPY  --from=BUILD /opt/src/build/libs/yeshdmits-api-0.0.1-SNAPSHOT.jar /app/src/yeshdmits-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/src/yeshdmits-api.jar"]