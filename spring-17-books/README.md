[![Java](https://img.shields.io/badge/Java-E43222??style=for-the-badge&logo=java&logoColor=FFFFFF)](https://java.com/)
[![Spring Boot Actuator](https://img.shields.io/badge/Spring_Boot_Actuator-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/guides/gs/actuator-service/)
[![Spring Data REST](https://img.shields.io/badge/Spring_Data_REST-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-data-rest)
[![Spring Security](https://img.shields.io/badge/Spring_Security-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-security/)
[![Spring Web](https://img.shields.io/badge/Spring_Web-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/guides/gs/serving-web-content/)
[![Thymeleaf](https://img.shields.io/badge/Thymeleaf-FFFFFF??style=for-the-badge&logo=Thymeleaf&logoColor=025B10)](https://www.thymeleaf.org/)
[![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-data-jpa)
[![Flyway](https://img.shields.io/badge/Flyway-FFFFFF??style=for-the-badge&logo=Flyway&logoColor=CC0100)](https://flywaydb.org/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-3E6389??style=for-the-badge&logo=PostgreSQL&logoColor=FFFFFF)](https://www.postgresql.org/)
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
2. БД в собственный контейнер оборачивать не нужно (если только Вы не используете кастомные плагины). Т.е. ее самому 
используем делать не надо, а используем готовый образ БД с Docker-hab.
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
8. Dependencies: Spring Boot Actuator, Spring Security, Spring Web, Thymeleaf, Spring Data JPA, PostgreSQL Driver, 
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
  - postgresql 
  - micrometer-registry-prometheus
  - spring-data-rest-hal-explorer
  - spring-boot-starter-data-rest
  - spring-boot-starter-aop
  - тесты: spring-boot-starter-test, spring-security-test
  - плагины: jib-maven-plugin (замена Dockerfile)
15. Запустить Docker

1) Проверить в Docker в разделе "Images" наличие "postgres", при отсутствии ввести в терминале команду "docker pull postgres:14.5"
  - Запустить в Docker в "Image" : "Images" - "Run", указать Optional settings:
    - Ports=5432 (установить в разделе "Ports")
    Environment variables:
    - POSTGRES_DB=library
    - POSTGRES_USER=postgres
    - POSTGRES_PASSWORD=12345
  - Проверить в Docker в "Containers": Image "postgresql:14.5", Status "Running", Port(s) "5432"

  2) Проверить в Docker в разделе "Images" наличие openjdk
  - вариант 1 "adoptopenjdk": https://hub.docker.com/r/adoptopenjdk/openjdk11 
               TAG "11.0.10-jre-slim" https://hub.docker.com/_/openjdk/tags?page=1&name=11&ordering=-name 
  - вариант 2 "liberica": https://hub.docker.com/r/bellsoft/liberica-openjdk-alpine
               TAG "11": https://hub.docker.com/r/bellsoft/liberica-openjdk-alpine/tags?page=2
16. Проверить соединение с БД в IntelliJ IDEA
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
      - <Description> - описание версии.
    
### Команды Docker
1) Список образов:                                                  docker image ls
2) Список запущенных контейнеров:                                   docker ps
3) Список имеющихся контейнеров (остаются после завершения работы): docker ps -a
4) Запуск образа с удалением контейнера после завершения работы:    docker run --rm <имя_образа> 
5) Запуск с возвращением управления или консоли (-d) и определением порта (-p<внешний>:<внутренний>, т.е. все запросы 
из внешнего порта идут на внутренний) и имени контейнера (--name):  docker run -d -p1433:1433 --name=ms_sql                                                
6) Просмотр логов контейнера:                                       docker logs <names/ID>
7) Остановка работающего контейнера (контейнер не удаляется):       docker kill <names/ID>
8) Удаление контейнера:                                             docker rm <names/ID>

### Запуск в Terminal
1) Сборка приложения и генерация jar-файла в target: ./mvnw package (необходимо запускать при запущенной БД!)
2) Запуск docker-compose из docker-compose.yaml    : docker-compose up
3) curl http://localhost:8080/

### Тестирование

[INFO] Tests run: 20, Failures: 0, Errors: 0, Skipped: 0

### Статьи по теме
1. Справочник по Dockerfile https://dker.ru/docs/docker-engine/engine-reference/dockerfile-reference/
2. Сравнение современных построителей образов контейнеров: Jib, Buildpacks и Docker https://habr.com/ru/post/552494/
3. How to Expose Ports in Docker https://www.mend.io/free-developer-tools/blog/docker-expose-port/
4. Docker: привязываем порты https://nuancesprog.ru/p/7481/
5. Creating and filling a Postgres DB with Docker compose https://levelup.gitconnected.com/creating-and-filling-a-postgres-db-with-docker-compose-e1607f6f882f