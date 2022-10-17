[![Java](https://img.shields.io/badge/Java-E43222??style=for-the-badge&logo=java&logoColor=FFFFFF)](https://java.com/)
[![Spring Boot Actuator](https://img.shields.io/badge/Spring_Boot_Actuator-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/guides/gs/actuator-service/)
[![Spring Data REST](https://img.shields.io/badge/Spring_Data_REST-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-data-rest)
[![Spring Security](https://img.shields.io/badge/Spring_Security-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-security/)
[![Spring Web](https://img.shields.io/badge/Spring_Web-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/guides/gs/serving-web-content/)
[![Thymeleaf](https://img.shields.io/badge/Thymeleaf-FFFFFF??style=for-the-badge&logo=Thymeleaf&logoColor=025B10)](https://www.thymeleaf.org/)
[![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-data-jpa)
[![Flyway](https://img.shields.io/badge/Flyway-FFFFFF??style=for-the-badge&logo=Flyway&logoColor=CC0100)](https://flywaydb.org/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-3E6389??style=for-the-badge&logo=PostgreSQL&logoColor=FFFFFF)](https://www.postgresql.org/)
[![Spring Cloud Netflix](https://img.shields.io/badge/Spring_Cloud_Netflix-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-cloud-netflix)

# 2022-05-otus-spring-sproshchaev
Development on the Spring Framework
-----------------------------------
### Домашнее задание №18 (Занятие 37 "Zuul, Hystrix Circuit Breaker, Sleuth, Zipkin, Hystrix Dashboard, Secure Configuration Properties")
Обернуть внешние вызовы в Hystrix

### Цель: 
Цель: сделать внешние вызовы приложения устойчивыми к ошибкам
Результат: приложение с изолированными с помощью Hystrix внешними вызовами

### Описание/Пошаговая инструкция выполнения домашнего задания:
1. Обернуть все внешние вызовы в Hystrix, Hystrix Javanica.
2. Возможно использование Resilent4j
3. Возможно использование Feign Client
Опционально: Поднять Turbine Dashboard для мониторинга.
Данное задание НЕ засчитывает предыдущие!

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
5 - экстра балл за особо красивый кусочек кода/решение целиком (ставится только после отличной работы, отдельно не 
ставится)
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
  - Artifact: spring-18-books
  - Name: spring-18-books
  - Description: Demo project for Spring Boot, Spring Boot Actuator, Spring Security, Spring Web, Thymeleaf, 
Spring Data JPA, PostgreSQL Driver, Flyway Migration
  - Package name: ru.otus.spring-17-books
6. Packaging: Jar
7. Java: 11
8. Dependencies: Spring Boot Actuator, Spring Security, Spring Web, Thymeleaf, Spring Data JPA, PostgreSQL Driver, 
Flyway Migration
9. Сохранить spring-18-books.zip в Java\2022-05-otus-spring-sproshchaev
10. Разархивировать архив Java\2022-05-otus-spring-sproshchaev\spring-18-books.zip (файл .zip удалить)
11. Открыть проект в IDE
13. Актуализировать файл .gitignore
14. Актуализировать файл README.md
15. Проверить в pom.xml наличие зависимостей: 
  - spring-boot-starter-actuator
  - spring-boot-starter-data-jpa 
  - spring-boot-starter-thymeleaf 
  - thymeleaf-extras-springsecurity5  
  - spring-boot-starter-security 
  - spring-boot-starter-web
  - flyway-core  
  - postgresql 
  - micrometer-registry-prometheus
  - spring-data-rest-hal-explorer
  - spring-boot-starter-data-rest
  - spring-boot-starter-aop
  - тесты: spring-boot-starter-test, spring-security-test

16. Запустить Docker

  Проверить в Docker в разделе "Images" наличие "postgres", при отсутствии ввести в терминале команду "docker pull postgres:14.5"
  - Запустить в Docker в "Image" : "Images" - "Run", указать Optional settings:
    - Ports=5432 (установить в разделе "Ports")
    Environment variables:
    - POSTGRES_DB=library
    - POSTGRES_USER=postgres
    - POSTGRES_PASSWORD=12345
  - Проверить в Docker в "Containers": Image "postgresql:14.5", Status "Running", Port(s) "5432"

17. Проверить соединение с БД в IntelliJ IDEA
  - "Database" - "Data Source" - "PostgreSQL":
    - Port=5432
    - User=postgres
    - Password=12345
    - Url=jdbc:postgresql://localhost:5432/library
  - "Test Connection"

18. Добавить файл application.yaml:
    spring.datasource.url: jdbc:postgresql://localhost:5432/library
    spring.datasource.username: postgres
    spring.datasource.password: 12345
    spring.datasource.driver-class-name: org.postgresql.Driver
    spring.jpa.database: postgresql
    spring.jpa.database-platform: org.hibernate.dialect.PostgreSQL10Dialect
    spring.jpa.generate-ddl: false       # не создавать объекты в БД
    spring.jpa.hibernate.ddl-auto: none  # не создавать объекты в БД
    spring.jpa.show-sql: true
    spring.jpa.hibernate.ddl-auto: none
    spring.flyway.enabled: true
    spring.jmx.enabled: true # включение JMX
    spring.data.rest.base-path: /datarest # пути для Spring Data REST в url
    management.endpoints.web.exposure.include: '*' # включение всех опций spring-boot-starter-actuator
    management.endpoint.health.show-details: always # отображение всех деталей
    management.health.defaults.enabled: true # включение health
    logging.file.name: spring-18-book.log
    logging.level.root: info # Logging Level

19. Добавить в resources\db.migration ChengeLog-и:
      - 1.0 
      - 2.0
      - 3.0
      - data - sql-запросы для добавления данных в СУБД
    Нэйминг ChengeLog-ов: PN__<Description>.sql
      - P - префикс "V" - версионные миграции, "U" - undo-миграции (только в Enterprise версии), "R" - повторяемые;
      - N - версия. Разделяется точками или единичными подчеркиваниями, может быть достаточно длинной;
      - <Description> - описание версии.

### Hystrix
1) pom.xml
  - dependencyManagement: spring-cloud-dependencies, version 2021.0.4
  - spring-cloud-starter-config, version Greenwich.SR1
  - spring-cloud-starter-netflix-hystrix
  - spring-cloud-config-server
2) bootstrap.yaml
  - spring.application.name: spring-18-books
  - server.port: 8080
3) Main
  - @EnableCircuitBreaker
4) LibraryServiceImpl
  - @HystrixCommand

### Логирование 
  resources\logback.xml

### Тестирование

[INFO] Tests run: 20, Failures: 0, Errors: 0, Skipped: 0

### Статьи по теме
1. Spring Cloud Netflix: Hystrix по-русски + Feign Client https://bit.ly/3TkkhiL
2. Руководство по Spring Cloud Netflix – Hystrix https://javascopes.com/spring-cloud-netflix-hystrix-a32b3b9b/
3. A Guide to Spring Cloud Netflix – Hystrix https://www.baeldung.com/spring-cloud-netflix-hystrix
4. bootstrap.yml загружается до application.yml https://bit.ly/3TnV07C
5. Различия между bootstrap.properties и application.properties https://www.baeldung.com/spring-cloud-bootstrap-properties 
6. "Решение в конце" (от Александра) https://stackoverflow.com/questions/67507452/no-spring-config-import-property-has-been-defined