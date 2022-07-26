[![Java](https://img.shields.io/badge/Java-E43222??style=for-the-badge&logo=java&logoColor=FFFFFF)](https://java.com/)
[![Spring](https://img.shields.io/badge/Spring-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/)

# 2022-05-otus-spring-sproshchaev
Development on the Spring Framework
-----------------------------------
### Домашнее задание №5 (Занятие 9 "DAO на Spring JDBC")
Создать приложение хранящее информацию о книгах в библиотеке

### Цель:
Цель: использовать возможности Spring JDBC и spring-boot-starter-jdbc для подключения к реляционным базам данных
Результат: приложение с хранением данных в реляционной БД, которое в дальнейшем будем развивать

### Описание/Пошаговая инструкция выполнения домашнего задания:
Это домашнее задание выполняется НЕ на основе предыдущего.

1. Использовать Spring JDBC и реляционную базу (H2 или настоящую реляционную БД). Настоятельно рекомендуем использовать 
NamedParametersJdbcTemplate
2. Предусмотреть таблицы авторов, книг и жанров.
3. Предполагается отношение многие-к-одному (у книги один автор и жанр). Опциональное усложнение - отношения 
многие-ко-многим (у книги может быть много авторов и/или жанров).
4. Интерфейс выполняется на Spring Shell (CRUD книги обязателен, операции с авторами и жанрами - как будет удобно).
5. Скрипт создания таблиц и скрипт заполнения данными должны автоматически запускаться с помощью spring-boot-starter-jdbc.
6. Написать тесты для всех методов DAO и сервиса работы с книгами. Рекомендации к выполнению работы:
7. НЕ делать AbstractDao.
8. НЕ делать наследования в тестах Это домашнее задание является основой для следующих.

### Критерии оценки:
Факт сдачи:

0 - задание не сдано
1 - задание сдано Степень выполнения (количество работающего функционала, что примет заказчик, что будет проверять тестировщик):
0 - ничего не работает или отсутствует основной функционал
1 - не работает или отсутствует большая часть критического функционала
2 - основной функционал есть, возможны небольшие косяки
3 - основной функционал есть, всё хорошо работает
4 - основной функционал есть, всё хорошо работает, тесты и/или задание перевыполнено Способ выполнения (качество выполнения, стиль кода, как ревью перед мержем):
0 - нужно править, мержить нельзя (нарушение соглашений, публичные поля)
1 - лучше исправить в рамках этого ДЗ для повышения оценки
2 - можно мержить, но в следующих ДЗ нужно поправить.
3 - можно мержить, мелкие недочёты
4 - отличная работа!
5 - экстра балл за особо красивый кусочек кода/решение целиком (ставится только после отличной работы, отдельно не ставится) Статус "Принято" ставится от 6 и выше баллов. Ниже 6, задание не принимается. Идеальное, но не работающее, решение = 1 + 0 + 4 (+4 а не 5) = 5 - не принимается. Если всё работает, но стилю не соответствует (публичные поля, классы капсом) = 1 + 4 + 0 = 5 - не принимается
### Параметры создания проекта Spring Initializr
1. https://start.spring.io/
2. Project: Maven Project
3. Language: Java
4. Spring Boot: 2.7.1
5. Project Metadata
  - Group: ru.otus
  - Artifact: spring-05-books
  - Name: spring-05-books
  - Description: Project for Spring Boot, Spring Shell, Spring Data JDBC
  - Package name: ru.otus.spring-05-books
6. Packaging: Jar
7. Java: 11
8. Dependencies: Spring Shell I/O, Spring Data JDBC
9. Сохранить spring-05-books.zip в Java\2022-05-otus-spring-sproshchaev
10. Разархивировать архив Java\2022-05-otus-spring-sproshchaev\spring-05-books.zip (файл .zip удалить)
11. Открыть проект в IDE
12. Актуализировать файл .gitignore
13. Актуализировать файл README.md
14. Проверить в pom.xml наличие зависимостей: spring-boot-starter, spring-shell-starter, spring-boot-starter-data-jdbc
15. Добавить в pom.xml зависимость H2: artifactId h2 
16. Добавить файлы application.yml, data.sql, schema.sql
17. Добавить в файл application.yml: url, username, password, driver-class-name

### Примечания
1. Если в полях БД H2 включить генерацию "ID BIGINT AUTO_INCREMENT", то использовать ручные вставки 
(insert into...(id) values (1)) в data.sql нельзя так как будет возникать ошибка уникальности ID когда БД будет 
генерировать новое значение 
2. Jdbc.queryForObject - это запрос за одним объектом; jdbc.query - запрос за несколькими объектами, поэтому 
при результате 0 и 2 в queryForObject будет исключение (ожидает 1 и не null), а в query пустой список (при 0) и список 
с числом записей. Использование jdbc.query - это лучший вариант. 

### Вызов методов (@ShellMethod)
"a" - Information about the library
"c" - Start console H2
#### CRUD for Genres
"cg" - Create a new genre of books in the library (Crud)
"gibg" - Getting a genre id (cRud)
"ggbi" - Getting information about the author from the library by id (cRud)
"ug" - Updating information about the genre (crUd)
"dg" - Deleting genre data from the library (cruD)
#### CRUD for Authors
"ca" - Create a new Author of books in the library (Crud)
"giba" - Getting an id by author (cRud)
"gabi" - Getting information about the author from the library by id (cRud)
"gaa" - Getting a list of all authors from the library (cRud)
"gag" - Getting a list of all genres from the library (cRud)
"ua" - Updating information about the author (crUd)
"da" - Deleting author data from the library (cruD)
#### CRUD for Books
"cb" - Add information about a new book, author, genre to the library (Crud)
"gbbi" - Get book data by its id (cRud)
"gab" - Get a list of all library books (cRud)
"gibb" - Getting an id by book (cRud)
"ub" - Update book data by id (crUd)
"db" - Deleting the selected book by id (cruD)

### Статьи по теме
1. Связи между таблицами базы данных https://habr.com/ru/post/488054/
2. Ключи и целостность базы данных https://bit.ly/3aXKuTL
3. EntityFramework: (анти)паттерн Repository https://bit.ly/3cs0gXE
4. Паттерн «Репозиторий» https://bit.ly/3PKMptP
5. Проблема с N+1 запросами в JPA и Hibernate https://bit.ly/3OfAdju
6. Типы данных H2 и соответствие Java https://bit.ly/3RYgDvk
7. Spring Boot With H2 Database https://www.baeldung.com/spring-boot-h2-database