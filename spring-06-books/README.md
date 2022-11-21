[![Java](https://img.shields.io/badge/Java-E43222??style=for-the-badge&logo=java&logoColor=FFFFFF)](https://java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-boot/)
[![Spring Shell](https://img.shields.io/badge/Spring_Shell-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-shell/)
[![Hibernate](https://img.shields.io/badge/Hibernate-5B666B??style=for-the-badge&logo=Hibernate)](http://hibernate.org/)
[![H2](https://img.shields.io/badge/H2-0618D5??style=for-the-badge&logo=H2&logoColor=FFFFFF)](https://www.h2database.com/)

# 2022-05-otus-spring-sproshchaev
Development on the Spring Framework
-----------------------------------
### Домашнее задание №6 (Занятие 11 "JPQL, Spring ORM, DAO на основе Spring ORM + JPA")
Переписать приложение для хранения книг на ORM

### Цель:
Цель: полноценно работать с JPA + Hibernate для подключения к реляционным БД посредством ORM-фреймворка
Результат: Высокоуровневое приложение с JPA-маппингом сущностей

### Описание/Пошаговая инструкция выполнения домашнего задания:
Домашнее задание выполняется переписыванием предыдущего на JPA.
Требования:

1. Использовать JPA, Hibernate только в качестве JPA-провайдера.
2. Для решения проблемы N+1 можно использовать специфические для Hibernate аннотации @Fetch и @BatchSize.
3. Добавить сущность "комментария к книге", реализовать CRUD для новой сущности.
4. Покрыть репозитории тестами, используя H2 базу данных и соответствующий H2 Hibernate-диалект для тестов.
5. Не забудьте отключить DDL через Hibernate
6. @Transactional рекомендуется ставить только на методы сервиса. 
Это домашнее задание будет использоваться в качестве основы для других ДЗ Данная работа не засчитывает предыдущую!

Дополнение из Вебинара 11 по ДЗ:
1) Библиотеку перевести на ORM framework
2) Вместо JDBC и JDBCTemplate использовать Jpa и EntytyManager
3) Для исключения проблемы N+1 использовать @Fetch и @BathSize
4) Добавить сущность "Комментарий к книге" (у одной книги много комментариев), реализовать CRUD
5) Тесты реализовать через DataJpaTest, покрыть репозитории
6) Отключить DDL через Hibernate, использовать data.sql, schema.sql
7) @Transactional ставим на методы сервиса. На репозитории не ставим

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
4. Spring Boot: 2.7.2
5. Project Metadata
  - Group: ru.otus
  - Artifact: spring-06-books
  - Name: spring-06-books
  - Description: Demo project for Spring Boot, Spring Shell, Spring Data JPA
  - Package name: ru.otus.spring-06-books
6. Packaging: Jar
7. Java: 11
8. Dependencies: Spring Shell I/O, Spring Data JPA
9. Сохранить spring-06-books.zip в Java\2022-05-otus-spring-sproshchaev
10. Разархивировать архив Java\2022-05-otus-spring-sproshchaev\spring-06-books.zip (файл .zip удалить)
11. Открыть проект в IDE
12. Актуализировать файл .gitignore
13. Актуализировать файл README.md
14. Проверить в pom.xml наличие зависимостей: 
  - spring-shell-starter, 
  - spring-boot-starter-data-jpa
15. Добавить в pom.xml зависимость H2: 
  - artifactId h2 (version>2.1.212) 
16. Добавить файлы application.yaml, data.sql, schema.sql
17. При создании таблицы comment необходимо указать для этого поля каскадное удаление "references book(id) on delete cascade",
которое будет удалять все комментарии в БД при удалении книги
18. Добавить в файл application.yaml: 
      url=jdbc:h2:mem:books, 
      username=sa, password, 
      driver-class-name=org.h2.Driver, 
      gpa.generate-ddl=false, 
      gpa.hibernate.ddl-auto=none, 
      gpa.show-sql=true, 
      sql.init.mode=always,
      sql.init.data-locations=data.sql,
      sql.init.schema-locations=schema.sql,
      h2.console.path=/h2-console,
      h2.console.settings.web-allow-others=true
19. Создать классы сущностей и разметить их аннотациями 
      @Entity, 
      @Id, 
      @GeneratedValue(strategy = GenerationType.IDENTITY) - если id формируется на уровне БД (через 
create table t (id bigint auto_increment primary key,...), то эта опция все-равно необходима!
      @ManyToOne
20. Над сервисами (не в репозитории!) разместить аннотации:
- @Transactional - если метод изменяет данные (чтение)
- @Transactional(readOnly = true) - если метод не изменяет данные (чтение) 
21. В репозитории предпочтительнее использовать TypedQuery

### Вызов методов (@ShellMethod)
"a" - Information about the library, example: a
"c" - Start console H2, example: c
#### CRUD for Genres
"cg" - Create a new genre of books in the library (Crud), example: cg --name Novel
"gibg" - Getting a genre id (cRud), example: gibg --name 'History'
"ggbi" - Getting information about the author from the library by id (cRud), example: ggbi --id 2
"gag" - Getting a list of all genres from the library (cRud), example: getallgenres
"ug" - Updating information about the genre (crUd), example: ug --id 1 --name 'Politics'
"dg" - Deleting genre data from the library (cruD), example: dg --id 5 --name 'Fiction'
#### CRUD for Authors
"ca" - Create a new Author of books in the library (Crud), example: ca --fullName 'Stephen Edwin King'
"giba" - Getting an id by author (cRud), example: giba --fullName 'Daniel Defoe'
"gabi" - Getting information about the author from the library by id (cRud), example: gabi --id 2
"gaa" - Getting a list of all authors from the library (cRud), example: getallauthors
"ua" - Updating information about the author (crUd), example: ua --id 1 --fullName 'Gianni Rodari'
"da" - Deleting author data from the library (cruD), example: da --id 3 --fullName 'Gianni Rodari'
#### CRUD for Books
"cb" - Add information about a new book, author, genre to the library (Crud), example: cb --title 'A Life in Letters' --author 'Arthur Conan Doyle' --genre Autobiography
"gbbi" - Get book data by its id (cRud), example: gbbi --id 1
"gab" - Get a list of all library books (cRud), example: gab
"gibb" - Getting an id by book (cRud), example: gibb --title 'The Pilgrim’s Progress' --fullName 'John Bunyan' --name 'History'
"ub" - Update book data by id (crUd), example: ub --id 1 --title 'New title' --author 'New Author' --genre 'New Genre'
"db" - Deleting the selected book by id (cruD), example: db --id 1
#### CRUD for Comment
"cc" - Create a new book comment (Crud), example: cc --idBook 1 --comment 'I read the book with pleasure :)'
"gcbi" - Get comment by its id (cRud), example: gcbi --id 1
"gibс" - Getting an id by comment, example: gibс --comment 'The Pilgrims Progress — is a very interesting book!'
"gacbbi" - Get all comments on the book by id
"uc" - Update comment by id (crUd), example: uc --id 1 --comment 'New comment'
"dc" - Deleting the selected comment by id (cruD), example: dc --id 1

### Тестирование
1. Отключить Spring Shell в application.yml: spring.shell.interactive.enabled=false
2. Добавить для тестирования Spring Shell в application.yml: spring.main.allow-circular-references=true
3. В ресурсы тестов необходимо скопировать только: application.yml и data.sql. (файл schema.sql в ресурсы тестов 
переносить нельзя иначе тесты будут тестировать не то, что находится в базе!)   

[INFO] Tests run: 29, Failures: 0, Errors: 0, Skipped: 0

### Статьи по теме
1. Проблема N+1 https://habr.com/ru/company/otus/blog...
2. Использование @Fetch(FetchMode.SUBSELECT) https://www.baeldung.com/hibernate-fetchmode
3. Hibernate для самых маленьких и не только https://habr.com/ru/post/132385/
