FROM java:8
VOLUME /tmp
ADD dockerserver-0.0.1-SNAPSHOT-fatjar.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]