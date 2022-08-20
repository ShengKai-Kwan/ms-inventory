FROM openjdk:8-jre-alpine
COPY ./ms-inventory-svc.jar /usr/app/
WORKDIR /usr/app/
ENTRYPOINT ["java", "-jar", "-Xmx1024m", "/usr/app/ms-inventory-svc.jar"]
EXPOSE 8088