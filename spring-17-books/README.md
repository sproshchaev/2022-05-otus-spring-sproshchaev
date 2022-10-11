[![Java](https://img.shields.io/badge/Java-E43222??style=for-the-badge&logo=java&logoColor=FFFFFF)](https://java.com/)
[![Spring Boot Actuator](https://img.shields.io/badge/Spring_Boot_Actuator-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/guides/gs/actuator-service/)
[![Spring Data REST](https://img.shields.io/badge/Spring_Data_REST-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-data-rest)
[![Spring Security](https://img.shields.io/badge/Spring_Security-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-security/)
[![Spring Web](https://img.shields.io/badge/Spring_Web-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/guides/gs/serving-web-content/)
[![Thymeleaf](https://img.shields.io/badge/Thymeleaf-FFFFFF??style=for-the-badge&logo=Thymeleaf&logoColor=025B10)](https://www.thymeleaf.org/)
[![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-data-jpa)
[![Flyway](https://img.shields.io/badge/Flyway-FFFFFF??style=for-the-badge&logo=Flyway&logoColor=CC0100)](https://flywaydb.org/)
[![MS SQL](https://img.shields.io/badge/SQL_Server-2B65B2??style=for-the-badge&logo=Microsoft&logoColor=FFFFFF)](https://www.microsoft.com/en-us/sql-server)
[![Docker](https://img.shields.io/badge/Docker-0E2B62??style=for-the-badge&logo=Docker&logoColor=FFFFFF)](https://www.docker.com/)

# 2022-05-otus-spring-sproshchaev
Development on the Spring Framework
-----------------------------------
### Домашнее задание №17 (Занятие 34 "Docker, оркестрация, облака, облачные хостинги")
Обернуть приложение в docker-контейнер

### Цель: 
Цель: деплоить приложение в современном DevOps-стеке
Результат: обёртка приложения в Docker

### Описание/Пошаговая инструкция выполнения домашнего задания:
Внимание! Задание выполняется на основе любого сделанного Web-приложения

1. Обернуть приложение в docker-контейнер. Dockerfile принято располагать в корне репозитория. В image должна попадать 
JAR-приложения. Сборка в контейнере рекомендуется, но не обязательна.
2. БД в собственный контейнер оборачивать не нужно (если только Вы не используете кастомные плагины)
3. Настроить связь между контейнерами, с помощью docker-compose
4. Опционально: сделать это в локальном кубе.
5. Приложение желательно реализовать с помощью всех Best Practices Docker (логгирование в stdout и т.д.)
Данное задание НЕ засчитывает предыдущие!

Комментарий к ДЗ из вебинара:
- можно собрать приложение с использованием Dockerfile (по программе курса), а можно с использованием Jib (более 
современный вариант) - выбрать на своё усмотрение

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
4. Spring Boot: 2.7.4
5. Project Metadata
  - Group: ru.otus
  - Artifact: spring-17-books
  - Name: spring-17-books
  - Description: Demo project for Spring Boot
  - Package name: ru.otus.spring-17-books
6. Packaging: Jar
7. Java: 11
8. Dependencies: Spring Boot Actuator, Spring Security, Spring Web, Thymeleaf, Spring Data JPA, MS SQL Server Driver, 
Flyway Migration
9. Сохранить spring-17-books.zip в Java\2022-05-otus-spring-sproshchaev
10. Разархивировать архив Java\2022-05-otus-spring-sproshchaev\spring-17-books.zip (файл .zip удалить)
11. Открыть проект в IDE
12. Актуализировать файл .gitignore
13. Актуализировать файл README.md
14. Проверить в pom.xml наличие зависимостей: 
  - spring-boot-starter-actuator
  - spring-boot-starter-data-jpa 
  - spring-boot-starter-thymeleaf 
  - thymeleaf-extras-springsecurity5  
  - spring-boot-starter-security 
  - spring-boot-starter-web
  - flyway-core  
  - flyway-sqlserver  
  - mssql-jdbc 
  - micrometer-registry-prometheus
  - spring-data-rest-hal-explorer
  - spring-boot-starter-data-rest
  - spring-boot-starter-aop
  - тесты: spring-boot-starter-test, spring-security-test
15. Запустить Docker
  - Проверить в Docker в разделе "Images" наличие "mcr.microsoft.com/mssql/server", при отсутствии ввести в терминале 
  команду "docker pull mcr.microsoft.com/mssql/server"
  - Запустить в Docker в "Image" : "Images" - "Run", указать Optional settings: 
     - ACCEPT_EULA=Y
     - SA_PASSWORD=Sa123456
     - MSSQL_PID=Developer
  - Проверить в Docker в "Containers": Image "mcr.microsoft...", Status "Running", Port(s) "1433"
16. Проверить соединение с БД в IntelliJ IDEA
  - "Database" - "Data Source" - "Microsoft SQL Server": 
     - Port=1433 
     - User=sa 
     - Password=Sa123456
     - Url=jdbc:sqlserver://localhost:1433 
  - "Test Connection"
17. Добавить файл application.yaml:
    spring.datasource.url: jdbc:sqlserver://localhost:1433;database=library;encrypt=true;trustServerCertificate=true;
    spring.datasource.username: sa
    spring.datasource.password: Sa123456
    spring.datasource.driverClassName: com.microsoft.sqlserver.jdbc.SQLServerDriver
    spring.jpa.show-sql: true
    spring.jpa.hibernate.ddl-auto: none
    spring.flyway.enabled: true
    spring.jmx.enabled: true # включение JMX
    spring.data.rest.base-path: /datarest # пути для Spring Data REST в url
    management.endpoints.web.exposure.include: '*' # включение всех опций spring-boot-starter-actuator
    management.endpoint.health.show-details: always # отображение всех деталей
    management.health.defaults.enabled: true # включение health
    logging.file.name: spring-17-book.log
    logging.level.root: info # Logging Level
18. Создать БД
    Система миграций flyway не поддерживает транзакции по созданию базы данных, поэтому ее необходимо создать вручную, 
    введя в "Query console" команду: create database library
    Далее все ChengeLog-и будут создаваться в базе данных, определенной в файле application.yaml
19. Добавить в resources\db.migration ChengeLog-и:
      - 1.0 
      - 2.0
      - 3.0
      - data - sql-запросы для добавления данных в СУБД
    Нэйминг ChengeLog-ов: PN__<Description>.sql
      - P - префикс "V" - версионные миграции, "U" - undo-миграции (только в Enterprise версии), "R" - повторяемые;
      - N - версия. Разделяется точками или единичными подчеркиваниями, может быть достаточно длинной;
      - <Description> - описание версии
    
### Команды Docker
1) Список образов:                                                  docker image ls
2) Список запущенных контейнеров:                                   docker ps
3) Список имеющихся контейнеров (остаются после завершения работы): docker ps -a
4) Запуск образа с удалением контейнера после завершения работы:    docker run --rm <имя_образа> 
5) Запуск с возвращением консоли (-d) и заданием порта (-p) и имени 
контейнера (--name):                                                docker run -d -p1433:1433 --name=ms_sql                                                
   ( 56:19)
### Тестирование

[INFO] Tests run: 20, Failures: 0, Errors: 0, Skipped: 0

### Статьи по теме
1. Сравнение современных построителей образов контейнеров: Jib, Buildpacks и Docker https://habr.com/ru/post/552494/
