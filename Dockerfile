FROM amazoncorretto:24.0.2-alpine
WORKDIR /krainet_auth_service
COPY target/krainet_auth_service-0.0.1-SNAPSHOT.jar krainet_auth_service.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","krainet_auth_service.jar"]