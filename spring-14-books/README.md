[![Java](https://img.shields.io/badge/Java-E43222??style=for-the-badge&logo=java&logoColor=FFFFFF)](https://java.com/)
[![Spring Shell](https://img.shields.io/badge/Spring_Shell-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-shell/)
[![Spring Batch](https://img.shields.io/badge/Spring_Batch-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-batch/)
[![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-data-jpa)
[![MS SQL](https://img.shields.io/badge/SQL_Server-2B65B2??style=for-the-badge&logo=Microsoft&logoColor=FFFFFF)](https://www.microsoft.com/en-us/sql-server)
[![Spring Data MongoDB](https://img.shields.io/badge/Spring_Data_MongoDB-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-data-mongodb/)
[![MongoDB](https://img.shields.io/badge/MongoDB-FFFFFF??style=for-the-badge&logo=MongoDB&logoColor=#4CA257)](https://www.mongodb.com/)
[![Mongock](https://img.shields.io/badge/Mongock-FFFFFF??style=for-the-badge&logo=Mongock&logoColor=#4CA257)](https://mongock.io/)

# 2022-05-otus-spring-sproshchaev
Development on the Spring Framework
-----------------------------------
### Домашнее задание №14 (Занятие 28 "Spring Batch")
На основе Spring Batch разработать процедуру миграции данных из реляционного хранилища в NoSQL или наоборот

### Цель:
Цель: мигрировать данные с помощью Spring Batch
Результат: приложение для пакетных обработок данных на Spring Batch

### Описание/Пошаговая инструкция выполнения домашнего задания:
Описание/Пошаговая инструкция выполнения домашнего задания:
1. Задание может быть выполнено в отдельном репозитории, с сущностями из ДЗ JPA и MongoDB.
2. Вы можете выбрать другую доменную модель
3. Необязательно добавлять процесс миграции в веб-приложение. Приложение может быть оформлено в виде отдельной утилиты.
4. Используя Spring Batch, следите, чтобы связи сущностей сохранились.
5. Опционально: Сделать restart задачи с помощью Spring Shell.
Данное задание НЕ засчитывает предыдущие!

Дополнение к ДЗ из лекции: 
6. Тесты не делаем

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
  - Artifact: spring-14-books
  - Name: spring-14-books
  - Description: 
  - Package name: ru.otus.spring-14-books
6. Packaging: Jar
7. Java: 11
8. Dependencies: Spring Batch, Spring Shell, Spring Data JPA, MS SQL Server Driver, Spring Data MongoDB
9. Сохранить spring-14-books.zip в Java\2022-05-otus-spring-sproshchaev
10. Разархивировать архив Java\2022-05-otus-spring-sproshchaev\spring-14-books.zip (файл .zip удалить)
11. Открыть проект в IDE
12. Актуализировать файл .gitignore
13. Актуализировать файл README.md
14. Проверить в pom.xml наличие зависимостей: 
  - spring-boot-starter-batch
  - spring-boot-starter-data-jpa
  - spring-boot-starter-data-mongodb
  - spring-shell-starter
  - mssql-jdbc
15. Запустить Docker
1) Проверить в Docker в разделе "Images" наличие "mcr.microsoft.com/mssql/server", при отсутствии ввести в терминале 
  команду "docker pull mcr.microsoft.com/mssql/server"
  - Запустить в Docker в "Image" : "Images" - "Run", указать Optional settings: 
     - ACCEPT_EULA=Y
     - SA_PASSWORD=Sa123456
     - MSSQL_PID=Developer
  - Проверить в Docker в "Containers": Image "mcr.microsoft...", Status "Running", Port(s) "1433".
2) Проверить в Docker в разделе "Images" наличие "mongo", при отсутствии ввести в терминале команду "docker pull mongo"
   - Запустить в Docker в "Image" : "Images" - "Run", указать Optional settings: 
   - Ports=27017
   - Проверить в Docker в "Containers": Image "mongo:latest", Status "Running".
16. Проверить соединение с БД в IntelliJ IDEA
1) Microsoft SQL Server  
    - "Database" - "Data Source" - "Microsoft SQL Server": 
    - Port=1433 
    - User=sa 
    - Password=Sa123456
    - Url=jdbc:sqlserver://localhost:1433 
  - "Test Connection"
2) MongoDB
    - "Database" - "Data Source" - "MongoDB" 
    - Port=27017 
    - "Test Connection"
    - В консоли ввести команду создания новой БД: use library2
17. Добавить файл application.yaml:
    spring.datasource.url: jdbc:sqlserver://localhost:1433;database=library;encrypt=true;trustServerCertificate=true;
    spring.datasource.username: sa
    spring.datasource.password: Sa123456
    spring.datasource.driverClassName: com.microsoft.sqlserver.jdbc.SQLServerDriver
    spring.jpa.show-sql: true
    spring.jpa.hibernate: ddl-auto: none
    spring.data.mongodb.database: library2
    spring.data.mongodb.port: 27017
    spring.data.mongodb.host: localhost
18. Добавить application.yml настройки для Mongock:
    mongock:
    runner-type: "InitializingBean" - если в приложении Mongock вместе с Spring Shell см. https://otus.ru/nest/post/1557/
    "ApplicationRunner" - если в приложении нет SpringShell
    change-logs-scan-package: ru.otus.spring08books.mongock.changelog
19. Создать пакет и классы для Mongock
    mongock/changelog/Schema.java, Data.java
21. Добавить аннотацию @EnableMongock в Main.class
### Примечания

### Тестирование
Тестов нет 

### Статьи по теме