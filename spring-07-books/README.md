[![Java](https://img.shields.io/badge/Java-E43222??style=for-the-badge&logo=java&logoColor=FFFFFF)](https://java.com/)
[![Spring](https://img.shields.io/badge/Spring-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/)

# 2022-05-otus-spring-sproshchaev
Development on the Spring Framework
-----------------------------------
### Домашнее задание №7 (Занятие 13 "Белая магия" Spring Data: Spring Data JPA")
Библиотеку на Spring Data JPA

### Цель:
Цель: максимально просто писать слой репозиториев с применением современных подходов
Результат: приложение со слоем репозиториев на Spring Data JPA

### Описание/Пошаговая инструкция выполнения домашнего задания:
Домашнее задание выполняется переписыванием предыдущего на JPA.
Требования:

1. Переписать все репозитории по работе с книгами на Spring Data JPA репозитории.
2. Используйте spring-boot-starter-data-jpa.
3. Кастомные методы репозиториев (или с хитрым @Query) покрыть тестами, используя H2.
4. @Transactional рекомендуется ставить на методы сервисов, а не репозиториев.
Это домашнее задание будет использоваться в качестве основы для других ДЗ
Данная работа не засчитывает предыдущую!
Дополнение из Вебинара 11 по ДЗ:

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
Если всё работает, но стилю не соответствует (публичные поля, классы капсом) = 1 + 4 + 0 = 5 - не принимается

### Параметры создания проекта Spring Initializr
1. https://start.spring.io/
2. Project: Maven Project
3. Language: Java
4. Spring Boot: 2.7.2
5. Project Metadata
  - Group: ru.otus
  - Artifact: spring-07-books
  - Name: spring-07-books
  - Description: Demo project for Spring Boot, Spring Shell, Spring Data JPA
  - Package name: ru.otus.spring-07-books
6. Packaging: Jar
7. Java: 11
8. Dependencies: Spring Shell I/O, Spring Data JPA
   Примечание: в Dependencies есть возможность дополнительно добавить зависимости:
   - H2 Database, в данной сборке эта зависимость не включена (планируется ручное добавление базы в проекте);
   - Liquibase Migration, в данной сборке эта зависимость не включена (groupId: org.liquibase, artifactId: liquibase-core
, version: 4.14.0 + рекомендуется groupId: org.yaml, artifactId: snakeyaml, version: 1.30);
   - Flyway Migration (в данной сборке эта зависимость не включена).
9. Сохранить spring-07-books.zip в Java\2022-05-otus-spring-sproshchaev
10. Разархивировать архив Java\2022-05-otus-spring-sproshchaev\spring-07-books.zip (файл .zip удалить)
11. Открыть проект в IDE
12. Актуализировать файл .gitignore
13. Актуализировать файл README.md
14. Проверить в pom.xml наличие зависимостей: spring-shell-starter, spring-boot-starter-data-jpa
15. Добавить в pom.xml зависимость H2: groupId: com.h2database, artifactId: h2, version: 2.1.212 
16. Добавить в pom.xml зависимость Liquibase:
    - groupId: org.liquibase, artifactId: liquibase-core, version: 4.14.0
    - groupId: org.yaml, artifactId: snakeyaml, version: 1.30 (рекомендуется эта зависимость для корректной работы 
liquibase с разными версиями)
17. Создать файл и добавить в файл application.yml: 
      url=jdbc:h2:mem:books, 
      username=sa, password, 
      driver-class-name=org.h2.Driver, 
      gpa.generate-ddl=false, 
      gpa.hibernate.ddl-auto=none, 
      gpa.show-sql=true,
      liquibase.enabled.true #sql.init.mode=always,
                             #sql.init.data-locations=data.sql,
                             #sql.init.schema-locations=schema.sql,
      h2.console.path=/h2-console,
      h2.console.settings.web-allow-others=true
19. Настройка liquibase:
    - Создать структуру каталогов resources/db/changelog
    - Создать resources/db/changelog/db.changelog-master.yaml:
      - версии БД (schema.sql): databaseChangeLog.- includeAll.path: db/changelog/1.0/ 
      - данные для БД (data.sql): databaseChangeLog.- includeAll.path: db/changelog/data/
    - Создать каталог для changelog-ов (схемы БД): resources/db/changelog/1.0
    - Создать changelog-и YYYY-MM-DD--Create-table_name.yml (.xml,.yml,.json,.sql): 2022-08-04--Create-author.yaml
    - Создать changelog в каталоге с данными (db/changelog/data/1.0 и db/changelog/data/1.0/csv)   

//----------//

20. Создать классы сущностей и разметить их аннотациями 
      @Entity, 
      @Id, 
      @GeneratedValue(strategy = GenerationType.IDENTITY) - если id формируется на уровне БД (через 
create table t (id bigint auto_increment primary key,...), то эта опция все-равно необходима!
      @ManyToOne
20. Над сервисами (не в репозитории!) разместить аннотации:
- @Transactional - если метод изменяет данные (чтение)
- @Transactional(readOnly = true) - если метод не изменяет данные (чтение) 
21. В репозитории предпочтительнее использовать TypedQuery

### Примечания

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
#### CRUD for Comment
"cc" - Create a new book comment (Crud)
"gcbi" - Get comment by its id (cRud)
"uc" - Update comment by id (crUd)
"dc" - Deleting the selected comment by id (cruD)

### Тестирование
1. Отключить Spring Shell в application.yml: spring.shell.interactive.enabled=false
2. Добавить для тестирования Spring Shell в application.yml: spring.main.allow-circular-references=true
3. В ресурсы тестов необходимо скопировать только: application.yml и data.sql. (файл schema.sql в ресурсы тестов 
переносить нельзя иначе тесты будут тестировать не то, что находится в базе!)   

### Статьи по теме
1. Liquibase changelog (структура БД schema.sql) Formats https://docs.liquibase.com/concepts/changelogs/changelog-formats.html
2. Liquibase load-data (загрузка данных data.sql) https://docs.liquibase.com/change-types/load-data.html