FROM openjdk:17

COPY target/translator-0.0.1-SNAPSHOT.jar /usr/app/

COPY src/main/resources/db/changelog /usr/app/src/main/resources/db/changelog

WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "translator-0.0.1-SNAPSHOT.jar"]