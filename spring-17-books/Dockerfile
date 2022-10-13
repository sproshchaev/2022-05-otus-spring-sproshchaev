FROM bellsoft/liberica-openjdk-alpine:11
COPY /target/spring-17-books-0.0.1-SNAPSHOT.jar /app/spring-17-books.jar
EXPOSE 8080
EXPOSE 5432
ENTRYPOINT ["java", "-jar", "/app/spring-17-books.jar"]