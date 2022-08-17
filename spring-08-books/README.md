[![Java](https://img.shields.io/badge/Java-E43222??style=for-the-badge&logo=java&logoColor=FFFFFF)](https://java.com/)
[![Spring Data MongoDB](https://img.shields.io/badge/Spring_Data_MongoDB-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-data-mongodb/)
[![MongoDB](https://img.shields.io/badge/MongoDB-FFFFFF??style=for-the-badge&logo=MongoDB&logoColor=#4CA257)](https://www.mongodb.com/)
[![Mongock](https://img.shields.io/badge/Mongock-FFFFFF??style=for-the-badge&logo=Mongock&logoColor=#4CA257)](https://mongock.io/)

# 2022-05-otus-spring-sproshchaev
Development on the Spring Framework
-----------------------------------
### Домашнее задание №8 (Занятие 15 "Spring Data для подключения к нереляционным БД")
Использовать MongoDB и spring-data для хранения информации о книгах

### Цель:
Цель: После выполнения ДЗ вы сможете использовать Spring Data MongoDB и саму MongoDB для разработки приложений с хранением данных в нереляционной БД.
Результат: Приложение с использованием MongoDB

### Описание/Пошаговая инструкция выполнения домашнего задания:
Задание может выполняться на основе предыдущего, а может быть выполнено самостоятельно
Требования:

1. Использовать Spring Data MongoDB репозитории, а если не хватает функциональности, то и *Operations
2. Тесты можно реализовать с помощью Flapdoodle Embedded MongoDB
3. Hibernate, равно, как и JPA, и spring-boot-starter-data-jpa не должно остаться в зависимостях, если ДЗ выполняется 
на основе предыдущего.
4. Как хранить книги, авторов, жанры и комментарии решать Вам. Но перенесённая с реляционной базы структура не всегда 
будет подходить для MongoDB.
Данное задание НЕ засчитывает предыдущие!
Это задание может использоваться в дальнейшем, а может не использоваться - на Ваше дальнейшее усмотрение

### Критерии оценки:
Факт сдачи:

0 - задание не сдано
1 - задание сдано
Степень выполнения (количество работающего функционала, что примет заказчик, что будет проверять тестировщик):
0 - ничего не работает или отсутствует основной функционал
1 - не работает или отсутствует большая часть критического функционала
2 - основной функционал есть, возможны небольшие косяки
3 - основной функционал есть, всё хорошо работает
4 - основной функционал есть, всё хорошо работает, тесты и/или задание перевыполнено
Способ выполнения (качество выполнения, стиль кода, как ревью перед мержем):
0 - нужно править, мержить нельзя (нарушение соглашений, публичные поля)
1 - лучше исправить в рамках этого ДЗ для повышения оценки
2 - можно мержить, но в следующих ДЗ нужно поправить.
3 - можно мержить, мелкие недочёты
4 - отличная работа!
5 - экстра балл за особо красивый кусочек кода/решение целиком (ставится только после отличной работы, отдельно не ставится)
Статус "Принято" ставится от 6 и выше баллов.
Ниже 6, задание не принимается.
Идеальное, но не работающее, решение = 1 + 0 + 4 (+4 а не 5) = 5 - не принимается.
Если всё работает, но стилю не соответсвует (публичные поля, классы каспом) = 1 + 4 + 0 = 5 - не принимается

### Параметры создания проекта Spring Initializr
1. https://start.spring.io/
2. Project: Maven Project
3. Language: Java
4. Spring Boot: 2.7.2
5. Project Metadata
  - Group: ru.otus
  - Artifact: spring-08-books
  - Name: spring-08-books
  - Description: Demo project for Spring Boot, Spring Shell, Spring Data MongoDB, Embedded MongoDB Database
  - Package name: ru.otus.spring-08-books
6. Packaging: Jar
7. Java: 11
8. Dependencies: Spring Shell I/O, Spring Data MongoDB, Embedded MongoDB Database
9. Сохранить spring-08-books.zip в Java\2022-05-otus-spring-sproshchaev
10. Разархивировать архив Java\2022-05-otus-spring-sproshchaev\spring-08-books.zip (файл .zip удалить)
11. Открыть проект в IDE
12. Актуализировать файл .gitignore
13. Актуализировать файл README.md
14. Проверить в pom.xml наличие зависимостей: 
  - spring-shell-starter, 
  - spring-boot-starter-data-mongodb, 
  - de.flapdoodle.embed.mongo
15. Добавить в pom.xml зависимость Mongock: 
  - groupId: com.github.cloudyrock.mongock, artifactId: mongock-spring-v5, version: 4.3.8
  - groupId: com.github.cloudyrock.mongock, artifactId: mongodb-springdata-v3-driver, version: 4.3.8
16. Запустить Docker
  - Проверить в Docker в разделе "Images" наличие "mongo", при отсутствии ввести в терминале команду "docker pull mongo"
  - Запустить в Docker в "Image" : "Images" - "Run", указать Optional settings: Ports=27017
  - Проверить в Docker в "Containers": Image "mongo:latest", Status "Running"
17. Проверить соединение с БД в IntelliJ IDEA
  - "Database" - "Data Source" - "MongoDB" - Port=27017 - "Test Connection"
  - "Query Consoles" - "console"
  - Ввести команду: show dbs (показать все БД)
  - Проверить вывод результата в консоль: "Services" - "console" - "console" 
  - Основные команды в MongoDB:
    - show dbs - показать все базы данных;
    - use databaseName - выбор базы данных для работы;
    - show collections - список коллекций текущей базы;
    - show users - список юзеров текущей базы;
    - db.createCollection("collectionName") - создание коллекции;
    - db.<collectionName>.insert({field1: "value", field2: "value"}) - вставить один или несколько документов в коллекцию;
    - db.<collectionName>.find() - показать все записи коллекции;
    - db.<collectionName>.count() - число записей в коллекции.
18. Добавить application.yml настройки для MongoDB:
  - spring.data.mongodb:
      - authentication-database: admin
      - username: root
      - password: root
      - database: library # задает имя БД, в которую затем mongock создает коллекции
      - port: 27017
      - host: localhost
19. Добавить application.yml настройки для Mongock:
  - mongock:
      - runner-type: "InitializingBean" - если в приложении Mongock вместе с Spring Shell см. https://otus.ru/nest/post/1557/  
                     "ApplicationRunner" - если в приложении нет SpringShell
      - change-logs-scan-package: ru.otus.spring08books.mongock.changelog
20. Создать пакет и классы для Mongock 
  - mongock/changelog/Schema.java, Data.java 
21. Добавить аннотацию @EnableMongock в Main.class

### Примечания


### Вызов методов (@ShellMethod)
"a" - Information about the library <br>
#### CRUD for Genres
"cg" - Create a new genre of books in the library (Crud) <br>
"gibg" - Getting a genre id (cRud) <br>
"ggbi" - Getting information about the author from the library by id (cRud) <br>
"gag" - Getting a list of all genres from the library (cRud) <br>
"ug" - Updating information about the genre (crUd) <br>
"dgbi" - Deleting genre data from the library (cruD) <br>
#### CRUD for Authors
"ca" - Create a new Author of books in the library (Crud) <br>
"giba" - Getting an id by author (cRud) <br>
"gabi" - Getting information about the author from the library by id (cRud) <br>
"gaa" - Getting a list of all authors from the library (cRud) <br>
"ua" - Updating information about the author (crUd) <br>
"dabi" - Deleting author by id from the library (cruD) <br>
#### CRUD for Books
"cb" - Add information about a new book, author, genre to the library (Crud) <br>
"gibb" - Getting an id by book (cRud) <br>
"gbbi" - Get book data by its id (cRud) <br>
"gab" - Get a list of all library books (cRud) <br>
"ub" - Update book data by id (crUd) <br>
"dbbi" - Deleting the selected book by id (cruD) <br>
#### CRUD for Comment
"cc" - Create a new book comment (Crud) <br>
"gcbi" - Get comment by its id (cRud) <br>
"gacbbi" - Get all comments on the book by id (cRud) <br>
"ucbi" - Update comment by id (crUd) <br>
"dcbi" - Deleting the selected comment by id (cruD) <br>

### Тестирование
1. Создать директорию test\resources
2. Создать test\resources\application.yaml
3. Создать TestAppConfiguration.java и подключить через аннотацию в MainTests.java

### Статьи по теме
1. DOCKER OFFICIAL IMAGE mongo https://hub.docker.com/_/mongo
2. Mongock GET STARTED https://docs.mongock.io/v5/get-started/#1--add-mongock-bom-to-your-pom-file
3. MONGODB SPRING DATA DRIVER https://docs.mongock.io/v5/driver/mongodb-springdata/index.html
4. Spring Data: Easy MongoDB Migration Using Mongock https://dzone.com/articles/spring-data-easy-mongodb-migration-using-mongock
5. Работа со Spring Shell и Mongock из Spring Boot - в проекте Spring Boot, одновременно интерфейс командной строки 
Spring Shell и библиотеку миграции Mongock, разработчики сталкиваются с тем, что запуск миграций не происходит https://otus.ru/nest/post/1557/
6. CHANGELOGS https://docs.mongock.io/v4/changelogs/index.html
7. Spring Boot Integration Testing with Embedded MongoDB https://www.baeldung.com/spring-boot-embedded-mongodb
8. Тестирование приложений Spring Data MongoDB с NoSQLUnit https://bit.ly/3dAHfmr