FROM dockerproxy-iva.si.francetelecom.fr/openjdk:8-slim

ARG JAR_FILE

ENV ACTIVE_PROFILE ""

EXPOSE 9090

ADD ${JAR_FILE} /opt/hubme/auto-irrigate-service.jar

WORKDIR /opt

ENTRYPOINT ["sh","-c","java -Dspring.profiles.active=${ACTIVE_PROFILE} \
                            -jar auto-irrigate-service.jar"]
