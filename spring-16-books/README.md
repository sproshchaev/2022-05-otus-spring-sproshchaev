[![Java](https://img.shields.io/badge/Java-E43222??style=for-the-badge&logo=java&logoColor=FFFFFF)](https://java.com/)
[![Spring Boot Actuator](https://img.shields.io/badge/Spring_Boot_Actuator-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/guides/gs/actuator-service/)
[![Spring Data REST](https://img.shields.io/badge/Spring_Data_REST-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-data-rest)
[![Spring Security](https://img.shields.io/badge/Spring_Security-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-security/)
[![Spring Web](https://img.shields.io/badge/Spring_Web-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/guides/gs/serving-web-content/)
[![Thymeleaf](https://img.shields.io/badge/Thymeleaf-FFFFFF??style=for-the-badge&logo=Thymeleaf&logoColor=025B10)](https://www.thymeleaf.org/)
[![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-data-jpa)
[![Flyway](https://img.shields.io/badge/Flyway-FFFFFF??style=for-the-badge&logo=Flyway&logoColor=CC0100)](https://flywaydb.org/)
[![MS SQL](https://img.shields.io/badge/SQL_Server-2B65B2??style=for-the-badge&logo=Microsoft&logoColor=FFFFFF)](https://www.microsoft.com/en-us/sql-server)

# 2022-05-otus-spring-sproshchaev
Development on the Spring Framework
-----------------------------------
### Домашнее задание №16 (Занятие 32 "Монолиты vs. Microservices (Round 2), Spring Boot Actuator - must have в микросервисах")
Использовать метрики, healthchecks и logfile

### Цель:
Цель: реализовать production-grade мониторинг и прозрачность в приложении
Результат: приложение с применением Spring Boot Actuator

### Описание/Пошаговая инструкция выполнения домашнего задания:
Данное задание выполняется на основе одного из реализованных Web-приложений

1. Подключить Spring Boot Actuator в приложение.
2. Включить метрики, healthchecks и logfile.
3. Реализовать свой собственный HealthCheck индикатор
4. UI для данных от Spring Boot Actuator реализовывать не нужно.
5. Опционально: переписать приложение на HATEOAS принципах с помощью Spring Data REST Repository
Данное задание НЕ засчитывает предыдущие!

Комментарий к ДЗ из вебинара: 
1) что значит использовать метрики - подключить Actuator и показать, что все работает, 
настроено, метрики включены;   
2) написать свой healthchecks какой-нибудь не сложный с какой-нибудь бизнес-логикой (не смешной, а нормальный);
3) logfile - в пропертях прописать для него какое-то красивое имя: имя микросервиса, дата и т.п.
4) добавляйте любую функциональность на выбор - можете Prometheus добавить
5) Опционально - добавить Spring Data REST 

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
  - Artifact: spring-16-books
  - Name: spring-16-books
  - Description: Demo project for Spring Boot
  - Package name: ru.otus.spring-16-books
6. Packaging: Jar
7. Java: 11
8. Dependencies: Spring Boot Actuator, Spring Security, Spring Web, Thymeleaf, Spring Data JPA, MS SQL Server Driver, 
Flyway Migration
9. Сохранить spring-16-books.zip в Java\2022-05-otus-spring-sproshchaev
10. Разархивировать архив Java\2022-05-otus-spring-sproshchaev\spring-16-books.zip (файл .zip удалить)
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
  - тесты: spring-boot-starter-test, spring-security-test 
  - micrometer-registry-prometheus
  - spring-data-rest-hal-explorer
  - spring-boot-starter-data-rest
  - spring-boot-starter-aop
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

### Spring Boot Actuator

1. Вызов Actuator http://localhost:8080/actuator

2. Метрики: http://localhost:8080/actuator/metrics

{"names":["application.ready.time","application.started.time","disk.free","disk.total","executor.active",
"executor.completed","executor.pool.core","executor.pool.max","executor.pool.size","executor.queue.remaining",
"executor.queued","hikaricp.connections","hikaricp.connections.acquire","hikaricp.connections.active",
"hikaricp.connections.creation","hikaricp.connections.idle","hikaricp.connections.max","hikaricp.connections.min",
"hikaricp.connections.pending","hikaricp.connections.timeout","hikaricp.connections.usage","http.server.requests",
"jdbc.connections.max","jdbc.connections.min","jvm.buffer.count","jvm.buffer.memory.used","jvm.buffer.total.capacity",
"jvm.classes.loaded","jvm.classes.unloaded","jvm.gc.live.data.size","jvm.gc.max.data.size","jvm.gc.memory.allocated",
"jvm.gc.memory.promoted","jvm.gc.overhead","jvm.gc.pause","jvm.memory.committed","jvm.memory.max",
"jvm.memory.usage.after.gc","jvm.memory.used","jvm.threads.daemon","jvm.threads.live","jvm.threads.peak",
"jvm.threads.states","logback.events","process.cpu.usage","process.start.time","process.uptime",
"spring.data.repository.invocations","system.cpu.count","system.cpu.usage","tomcat.sessions.active.current",
"tomcat.sessions.active.max","tomcat.sessions.alive.max","tomcat.sessions.created","tomcat.sessions.expired",
"tomcat.sessions.rejected"]}

3. Вызов HealthCheck http://localhost:8080/actuator/health

{"status":"UP","components":{"db":{"status":"UP","details":{"database":"Microsoft SQL Server",
"validationQuery":"isValid()"}},"diskSpace":{"status":"UP","details":{"total":510507945984,"free":239072727040,
"threshold":10485760,"exists":true}},"library":{"status":"UP","details":{"message":"App is working"}},
"ping":{"status":"UP"}}}

4. http://localhost:8080/actuator/health/library
   Пример индикации рабочего состояния приложения: "library":{"status":"UP","details":{"message":"App is working"}}
   Пример не рабочего состояния: "library":{"status":"DOWN","details":{"message":"App is not working"}}

5. http://localhost:8080/actuator/loggers

6. Вызов просмотра log-файла http://localhost:8080/actuator/logfile

7. Вызов Prometheus http://localhost:8080/actuator/prometheus

8. Вызовы Spring Data REST
   http://localhost:8080/datarest/author
   http://localhost:8080/datarest/author/search
   http://localhost:8080/datarest/genre
   http://localhost:8080/datarest/book   
   http://localhost:8080/datarest/comment

### Примечания

### Тестирование

[INFO] Tests run: 20, Failures: 0, Errors: 0, Skipped: 0

### Статьи по теме
1. Введение в Spring Boot Actuator https://habr.com/ru/company/otus/blog/452624/
2. Используйте Logging в Spring Boot https://betacode.net/11753/use-logging-in-spring-boot