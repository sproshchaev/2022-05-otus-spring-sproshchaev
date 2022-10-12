FROM bellsoft/liberica-openjdk-alpine:11
COPY /target/spring-17-books-0.0.1-SNAPSHOT.jar /app/books.jar
EXPOSE 8080
EXPOSE 1433
ENTRYPOINT ["java", "-jar", "/app/books.jar"]