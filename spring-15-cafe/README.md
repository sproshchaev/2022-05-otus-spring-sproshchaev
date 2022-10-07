[![Java](https://img.shields.io/badge/Java-E43222??style=for-the-badge&logo=java&logoColor=FFFFFF)](https://java.com/)
[![Spring Shell](https://img.shields.io/badge/Spring_Shell-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-shell/)
[![Spring Integration](https://img.shields.io/badge/Spring_Integration-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-integration)

# 2022-05-otus-spring-sproshchaev
Development on the Spring Framework
-----------------------------------
### Домашнее задание №15 (Занятие 31 "Spring Integration: Endpoints и Flow Components")
Реализовать обработку доменной сущности через каналы Spring Integration

### Цель:
Цель: Участники смогут осуществлять "интеграцию" частей приложения с помощью EIP
Результат: приложение c применением EIP на Spring Integration

### Описание/Пошаговая инструкция выполнения домашнего задания:
1. Выберите другую доменную область и сущности. Пример: превращение гусеницы в бабочку.
2. Опишите/сконфигурируйте процесс (IntegrationFlow) с помощью инструментария предоставляемого Spring Integration.
3. Желательно использование MessagingGateway и subfolw (при необходимости).
   Задание выполняется в другом репозитории/в другой папке.
   Данное задание НЕ засчитывает предыдущие!

Дополнение к ДЗ из лекции: 
4. Варианты доменных областей: 1) ресторан, бар, фильтрация по холодным 2) свой бизнес-процесс и т.п.
5. Желательно пророутить и вызвать какой-то обработчик

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
  - Artifact: spring-15-cafe
  - Name: spring-15-cafe
  - Description: Demo project for Spring Boot, Spring Integration, Spring Shell
  - Package name: ru.otus.spring-15-cafe
6. Packaging: Jar
7. Java: 11
8. Dependencies: Spring Integration, Spring Shell
9. Сохранить spring-15-cafe.zip в Java\2022-05-otus-spring-sproshchaev
10. Разархивировать архив Java\2022-05-otus-spring-sproshchaev\spring-15-cafe.zip (файл .zip удалить)
11. Открыть проект в IDE
12. Актуализировать файл .gitignore
13. Актуализировать файл README.md
14. Проверить в pom.xml наличие зависимостей: 
  - spring-boot-starter-integration
  - spring-shell-starter
  - добавить зависимость spring-messaging

### Примечания

### Тестирование

### Статьи по теме
1. Introduction to Spring Integration https://www.baeldung.com/spring-integration