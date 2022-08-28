[![Java](https://img.shields.io/badge/Java-E43222??style=for-the-badge&logo=java&logoColor=FFFFFF)](https://java.com/)
[![Spring Web](https://img.shields.io/badge/Spring_Web-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/guides/gs/serving-web-content/)
[![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-data-jpa)
[![MySQL](https://img.shields.io/badge/MySQL-42759B??style=for-the-badge&logo=MySQL&logoColor=FFFFFF)](https://www.mysql.com/)
[![Liquibase](https://img.shields.io/badge/Liquibase-FFFFFF??style=for-the-badge&logo=Liquibase&logoColor=3861F6)](https://www.liquibase.com/)

# 2022-05-otus-spring-sproshchaev
Development on the Spring Framework
-----------------------------------
### Домашнее задание №10 (Занятие 19 "Современные приложения на Spring MVC")
Переписать приложение с использованием AJAX и REST-контроллеров

### Цель:
Цель: использовать Spring MVC для разработки современных AJAX/SPA приложений c помощью Spring MVC
Результат: современное приложение на стеке Spring

### Описание/Пошаговая инструкция выполнения домашнего задания:
Домашнее задание выполняется на основе предыдущего.

1. Переписать приложение с классических View на AJAX архитектуру и REST-контроллеры.
2. Минимум: получение одной сущности и отображение её на странице с помощью XmlHttpRequest, fetch api или jQuery
3. Опционально максимум: полноценное SPA приложение на React/Vue/Angular, только REST-контроллеры.
В случае разработки SPA - рекомендуется вынести репозиторий с front-end. Используйте proxy при разработке (настройки CORS не должно быть).
Данное задание, выполненное в виде SPA засчитывает ДЗ №9 (Занятие №15).
Если Вы хотите засчитать, то обязательно пришлите ссылку в чат соответствующего предыдущего занятия.
Данное ДЗ будет использоваться в дальнейшем

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
  - Artifact: spring-10-books
  - Name: spring-10-books
  - Description: Demo project for Spring Boot, Spring Web, Spring Data JPA, MySQL Driver, Liquibase Migration
  - Package name: ru.otus.spring-10-books
6. Packaging: Jar
7. Java: 11
8. Dependencies: Spring Web, Spring Data JPA, MySQL Driver, Liquibase Migration
9. Сохранить spring-10-books.zip в Java\2022-05-otus-spring-sproshchaev
10. Разархивировать архив Java\2022-05-otus-spring-sproshchaev\spring-10-books.zip (файл .zip удалить)
11. Открыть проект в IDE
12. Актуализировать файл .gitignore
13. Актуализировать файл README.md
14. Проверить в pom.xml наличие зависимостей: 
  - spring-boot-starter-data-jpa
  - spring-boot-starter-web
  - liquibase-core
  - mysql-connector-java
15. Запустить Docker
  - Проверить в Docker в разделе "Images" наличие "postgres", при отсутствии ввести в терминале команду "docker pull mysql"
  - Запустить в Docker в "Image" : "Images" - "Run", указать Optional settings: 
     - Ports=3306
     - MYSQL_DATABASE=books
     - MYSQL_USER=library
     - MYSQL_PASSWORD=12345
     - MYSQL_ROOT_PASSWORD=root
     - MYSQL_ALLOW_EMPTY_PASSWORD=yes
     - MYSQL_RANDOM_ROOT_PASSWORD=yes
  - Проверить в Docker в "Containers": Image "mysql:latest", Status "Running", Port(s) "3306"
16. Проверить соединение с БД в IntelliJ IDEA
  - "Database" - "Data Source" - "MySQL": 
     - Port=3306
     - User=library 
     - Password=12345
     - Url=jdbc:mysql://localhost:3306/books 
  - "Test Connection"
17. Добавить файл application.yaml:
  - spring:
      - datasource.url: jdbc:mysql://localhost:3306/books
      - datasource.username: library
      - datasource.password: 12345
      - datasource.driver-class-name: com.mysql.cj.jdbc.Driver
      - jpa.generate-ddl: false        
      - jpa.hibernate.ddl-auto: none  
      - jpa.show-sql: true            
      - liquibase.enabled: true
18. Добавить в resources\db.changelog ChengeLog-и:
      - \1.0 
      - \2.0
      - \data - sql-запросы для добавления данных в СУБД
      - db.changelog-master.yaml
          - databaseChangeLog:
              - includeAll:
                  path: db/changelog/1.0/ 
              - includeAll:
                  path: db/changelog/data/1.0/
              - includeAll:
                  path: db/changelog/2.0/
              - includeAll:
                  path: db/changelog/data/2.0/
### Примечания

### Тестирование

### Статьи по теме
1. Docker MySQL https://hub.docker.com/_/mysql
2. Accessing data with MySQL https://spring.io/guides/gs/accessing-data-mysql/
3. MySQL + Spring JPA + Docker. Basic https://bit.ly/3RfG9e9
