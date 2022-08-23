[![Java](https://img.shields.io/badge/Java-E43222??style=for-the-badge&logo=java&logoColor=FFFFFF)](https://java.com/)
[![Spring Web](https://img.shields.io/badge/Spring_Web-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/guides/gs/serving-web-content/)
[![Thymeleaf](https://img.shields.io/badge/Thymeleaf-FFFFFF??style=for-the-badge&logo=Thymeleaf&logoColor=025B10)](https://www.thymeleaf.org/)
[![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-data-jpa)
[![Flyway](https://img.shields.io/badge/Flyway-FFFFFF??style=for-the-badge&logo=Flyway&logoColor=CC0100)](https://flywaydb.org/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-3E6389??style=for-the-badge&logo=PostgreSQL&logoColor=FFFFFF)](https://www.postgresql.org/)

# 2022-05-otus-spring-sproshchaev
Development on the Spring Framework
-----------------------------------
### Домашнее задание №9 (Занятие 18 "Spring MVC View")
CRUD приложение с Web UI и хранением данных в БД

### Цель:
Цель: разрабатывать полноценные классические Web-приложения
Результат: Web-приложение полностью на стеке Spring

### Описание/Пошаговая инструкция выполнения домашнего задания:
Необходимо:

1. Создать приложение с хранением сущностей в БД (можно взять библиотеку и DAO/репозитории из прошлых занятий)
2. Использовать классический View на Thymeleaf, classic Controllers.
3. Для книг (главной сущности) на UI должны быть доступные все CRUD операции. CRUD остальных сущностей - по желанию/необходимости.
4. Локализацию делать НЕ нужно - она строго опциональна.
Данное задание НЕ засчитывает предыдущие!
Это домашнее задание частично будет использоваться в дальнейшем

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
4. Spring Boot: 2.7.3
5. Project Metadata
  - Group: ru.otus
  - Artifact: spring-09-books
  - Name: spring-09-books
  - Description: Demo project for Spring Boot, Spring Web, Thymeleaf, Spring Data JPA, PostgreSQL Driver, Flyway Migration
  - Package name: ru.otus.spring-09-books
6. Packaging: Jar
7. Java: 11
8. Dependencies: Spring Data JPA, PostgreSQL Driver, Flyway Migration, Spring Web, Thymeleaf
9. Сохранить spring-09-books.zip в Java\2022-05-otus-spring-sproshchaev
10. Разархивировать архив Java\2022-05-otus-spring-sproshchaev\spring-09-books.zip (файл .zip удалить)
11. Открыть проект в IDE
12. Актуализировать файл .gitignore
13. Актуализировать файл README.md
14. Проверить в pom.xml наличие зависимостей: 
  - spring-boot-starter-data-jpa 
  - spring-boot-starter-thymeleaf 
  - spring-boot-starter-web
  - flyway-core
  - postgresql
15. Запустить Docker
  - Проверить в Docker в разделе "Images" наличие "postgres", при отсутствии ввести в терминале команду "docker pull postgres"
  - Запустить в Docker в "Image" : "Images" - "Run", указать Optional settings: 
     - Ports=5432
     - POSTGRES_DB=books
     - POSTGRES_USER=postgres
     - POSTGRES_PASSWORD=12345
  - Проверить в Docker в "Containers": Image "postgres:latest", Status "Running", Port(s) "5432"
16. Проверить соединение с БД в IntelliJ IDEA
  - "Database" - "Data Source" - "PostgreSQL": 
     - Port=5432 
     - User=postgres 
     - Password=12345
     - Url=jdbc:postgresql://localhost:5432/books 
  - "Test Connection"
17. Добавить файл application.yaml:
  - spring:
      - datasource.url: jdbc:postgresql://localhost:5432/books
      - datasource.username: postgres
      - datasource.password: 12345
      - datasource.driver-class-name: org.postgresql.Driver
      - jpa.database: postgresql
      - jpa.database-platform: org.hibernate.dialect.PostgreSQL10Dialect
      - flyway.enabled: true
18. Добавить в resources\db.migration ChengeLog-и:
      - 1.0 
      - 2.0
      - data - sql-запросы для добавления данных в СУБД
    Нэйминг ChengeLog-ов: PN__<Description>.sql
      - P - префикс "V" - версионные миграции, "U" - undo-миграции (только в Enterprise версии), "R" - повторяемые;
      - N - версия. Разделяется точками или единичными подчеркиваниями, может быть достаточно длинной;
      - <Description> - описание версии.
### Примечания

### Вызов методов (@ShellMethod)
"a" - Information about the library <br>
#### CRUD for Genres
"cg --name 'New genre'" - Create a new genre of books in the library (Crud) <br>
"gibg --name 'Genre name'" - Getting a genre id (cRud) <br>
"ggbi --id <id genre>" - Getting information about the author from the library by id (cRud) <br>
"gag" - Getting a list of all genres from the library (cRud) <br>
"ug  --id <id genre> --name 'New genre name'" - Updating information about the genre (crUd) <br>
"dgbi --id <id genre>" - Deleting genre data from the library (cruD) <br>
#### CRUD for Authors
"ca --fullName 'New author'" - Create a new Author of books in the library (Crud) <br>
"giba --fullName 'Author name'" - Getting an id by author (cRud) <br>
"gabi --id <id author>" - Getting information about the author from the library by id (cRud) <br>
"gaa" - Getting a list of all authors from the library (cRud) <br>
"ua --id <id author> --fullName 'New author name'" - Updating information about the author (crUd) <br>
"dabi --id <id author>" - Deleting author by id from the library (cruD) <br>
#### CRUD for Books
"cb --title 'Title book' --author 'Author name' --genre 'Genre name'" - Add information about a new book, author, genre to the library (Crud) <br>
"gibb --title 'Title book' --fullName 'Author name' --name 'Genre name'" - Getting an id by book (cRud) <br>
"gbbi --id <id book>" - Get book data by its id (cRud) <br>
"gab" - Get a list of all library books (cRud) <br>
"ub --id 1 --title 'New title' --author 'New Author' --genre 'New Genre'" - Update book data by id (crUd) <br>
"dbbi --id <id book>" - Deleting the selected book by id (cruD) <br>
#### CRUD for Comment
"cc --idBook <id book> --comment 'Text comment'" - Create a new book comment (Crud) <br>
"gcbi --id <id comment>" - Get comment by its id (cRud) <br>
"gacbbi --id <id book>" - Get all comments on the book by id (cRud) <br>
"ucbi --id <id comment> --comment 'New comment'" - Update comment by id (crUd) <br>
"dcbi --id <id comment>" - Deleting the selected comment by id (cruD) <br>
"dacb --idBook <id book>" - Delete all comments to the book <br>

### Тестирование

### Статьи по теме
1. PostgreSQL Типы данных https://postgrespro.ru/docs/postgresql/9.4/datatype
2. Внешние ключи FOREIGN KEY: ON DELETE CASCADE https://bit.ly/3Kd0Ipg
3. Учебник: Использование Thymeleaf https://habr.com/ru/post/350862/
