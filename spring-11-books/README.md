[![Java](https://img.shields.io/badge/Java-E43222??style=for-the-badge&logo=java&logoColor=FFFFFF)](https://java.com/)
[![Spring Reactive Web](https://img.shields.io/badge/Spring_Reactive_Web-FFFFFF??style=for-the-badge&logo=Spring)](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html/)
[![Thymeleaf](https://img.shields.io/badge/Thymeleaf-FFFFFF??style=for-the-badge&logo=Thymeleaf&logoColor=025B10)](https://www.thymeleaf.org/)
[![JavaScript](https://img.shields.io/badge/JavaScript-000000??style=for-the-badge&logo=JavaScript&logoColor=F3E050)](https://github.com/sproshchaev/javascript-jquery/)
[![AJAX](https://img.shields.io/badge/AJAX-FFFFFF??style=for-the-badge&logo=AJAX&logoColor=2E64A4)](https://developer.mozilla.org/ru/docs/Web/Guide/AJAX/)
[![Spring Data MongoDB](https://img.shields.io/badge/Spring_Data_MongoDB-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-data-mongodb/)
[![MongoDB](https://img.shields.io/badge/MongoDB-FFFFFF??style=for-the-badge&logo=MongoDB&logoColor=#4CA257)](https://www.mongodb.com/)
[![Mongock](https://img.shields.io/badge/Mongock-FFFFFF??style=for-the-badge&logo=Mongock&logoColor=#4CA257)](https://mongock.io/)

# 2022-05-otus-spring-sproshchaev
Development on the Spring Framework
-----------------------------------
### Домашнее задание №11 (Занятие 22 "Spring WebFlux")
Использовать WebFlux

### Цель:
Цель: разрабатывать Responsive и Resilent приложения на реактивном стеке Spring c помощью Spring Web Flux и Reactive Spring Data Repositories
Результат: приложение на реактивном стеке Spring

### Описание/Пошаговая инструкция выполнения домашнего задания:
1. За основу для выполнения работы можно взять ДЗ с Ajax + REST (классическое веб-приложение для этого лучше не использовать).
2. Но можно выбрать другую доменную модель (не библиотеку).
3. Необходимо использовать Reactive Spring Data Repositories.
4. В качестве БД лучше использовать MongoDB или Redis. Использовать PostgreSQL и экспериментальную R2DBC не рекомендуется.
5. RxJava vs Project Reactor - на Ваш вкус.
6. Вместо классического Spring MVC и embedded Web-сервера использовать WebFlux.
7. Опционально: реализовать на Functional Endpoints
Данное задание НЕ засчитывает предыдущие!
Рекомендации:
Старайтесь избавиться от лишних архитектурных слоёв. Самый простой вариант - весь flow в контроллере.
8. Дополнительный комментарий к ДЗ из вебинара:
- spring-boot-starter-web не должно быть в pom.xml, не должно быть класса Servlet
- никаких сервисов не должно быть - все реализуем в контроллере
- R2DBC успешно используется в банковских проектах, можно использовать и его

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
4. Spring Boot: 2.7.3
5. Project Metadata
  - Group: ru.otus
  - Artifact: spring-11-books
  - Name: spring-11-books
  - Description: Demo project for Spring Boot, Spring Reactive Web, Thymeleaf, Spring Data Reactive MongoDB, Embedded MongoDB Database
  - Package name: ru.otus.spring-11-books
6. Packaging: Jar
7. Java: 11
8. Dependencies: Spring Reactive Web, Thymeleaf, Spring Data Reactive MongoDB, Embedded MongoDB Database
9. Сохранить spring-11-books.zip в Java\2022-05-otus-spring-sproshchaev
10. Разархивировать архив Java\2022-05-otus-spring-sproshchaev\spring-11-books.zip (файл .zip удалить)
11. Открыть проект в IDE
12. Актуализировать файл .gitignore
13. Актуализировать файл README.md
14. Проверить в pom.xml наличие зависимостей: 
  - spring-boot-starter-webflux
  - spring-boot-starter-thymeleaf
  - spring-boot-starter-data-mongodb-reactive
  - de.flapdoodle.embed.mongo
15. Добавить в pom.xml зависимость Mongock:
    groupId: com.github.cloudyrock.mongock, artifactId: mongock-spring-v5, version: 4.3.8
    groupId: com.github.cloudyrock.mongock, artifactId: mongodb-springdata-v3-driver, version: 4.3.8
16. Запустить Docker
    Проверить в Docker в разделе "Images" наличие "mongo", при отсутствии ввести в терминале команду "docker pull mongo"
    Запустить в Docker в "Image" : "Images" - "Run", указать Optional settings: Ports=27017
    Проверить в Docker в "Containers": Image "mongo:latest", Status "Running"
17. Проверить соединение с БД в IntelliJ IDEA
    "Database" - "Data Source" - "MongoDB" - Port=27017 - "Test Connection"
    "Query Consoles" - "console"
    Ввести команду: show dbs (показать все БД)
    Проверить вывод результата в консоль: "Services" - "console" - "console"
    Основные команды в MongoDB:
    show dbs - показать все базы данных;
    use databaseName - выбор базы данных для работы;
    show collections - список коллекций текущей базы;
    show users - список юзеров текущей базы;
    db.createCollection("collectionName") - создание коллекции;
    db..insert({field1: "value", field2: "value"}) - вставить один или несколько документов в коллекцию;
    db..find() - показать все записи коллекции;
    db..count() - число записей в коллекции.
18. Добавить application.yml настройки для MongoDB:
    - spring.data.mongodb:
    - authentication-database: admin
    - username: root
    - password: root
    - database: library # задает имя БД, в которую затем mongock создает коллекции
    - port: 27017
    - host: localhost
19. Добавить application.yml настройки для Mongock, создать пакет и классы для Mongock, Добавить аннотацию @EnableMongock в Main.class
    - Для работы в реактивном стеке требуется mongodb-reactive-driver https://docs.mongock.io/v5/driver/mongodb-reactive/index.html

### Примечания

### Тестирование

### Статьи по теме
