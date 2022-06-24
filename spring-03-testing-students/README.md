[![Java](https://img.shields.io/badge/Java-E43222??style=for-the-badge&logo=java&logoColor=FFFFFF)](https://java.com/)
[![Spring](https://img.shields.io/badge/Spring-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/)

# 2022-05-otus-spring-sproshchaev
Development on the Spring Framework
-----------------------------------
### Домашнее задание №3
Приложение по проведению тестирования студентов (с самим тестированием)

### Цель:
Использовать возможности Spring Boot, чтобы разрабатывать современные приложения, так, как их сейчас и разрабатывают.
Результат: Production-ready приложение на Spring Boot

### Описание/Пошаговая инструкция выполнения домашнего задания:
Это домашнее задание выполняется на основе предыдущего.

1. Создать проект, используя Spring Boot Initializr (https://start.spring.io)
2. Перенести приложение проведения опросов из прошлого домашнего задания.
3. Перенести все свойства в application.yml
4. Локализовать выводимые сообщения и вопросы (в CSV-файле). MesageSource должен быть из автоконфигурации Spring Boot.
5. Сделать собственный баннер для приложения.
6. Перенести тесты и использовать spring-boot-test-starter для тестирования
*Опционально:
- использовать ANSI-цвета для баннера.
- если Ваш язык отличается от русского и английского - локализовать в нём. Коммитить wrapper или нет в репозиторий - решать Вам. 
Задание сдаётся в виде ссылки на pull-request в чат с преподавателем. Вопросы можно задавать в чате, но для оперативности 
рекомендуем Slack. Написанное приложение будет использоваться в ДЗ №4 (к занятию №5). 
Данное задание засчитывает ДЗ №1 (к занятию №1) и ДЗ №2 (к занятию №2). Если Вы хотите засчитать, то обязательно пришлите 
ссылку в чат соответствующего предыдущего занятия.

### Критерии оценки:
Факт сдачи:
0 - задание не сдано
1 - задание сдано Степень выполнения (количество работающего функционала, что примет заказчик, что будет проверять 
тестировщик):
0 - ничего не работает или отсутствует основной функционал
1 - не работает или отсутствует большая часть критического функционала
2 - основной функционал есть, возможны небольшие косяки
3 - основной функционал есть, всё хорошо работает
4 - основной функционал есть, всё хорошо работает, тесты и/или задание перевыполнено Способ выполнения (качество 
выполнения, стиль кода, как ревью перед мержем):
0 - нужно править, мержить нельзя (нарушение соглашений, публичные поля)
1 - лучше исправить в рамках этого ДЗ для повышения оценки
2 - можно мержить, но в следующих ДЗ нужно поправить.
3 - можно мержить, мелкие недочёты
4 - отличная работа!
5 - экстра балл за особо красивый кусочек кода/решение целиком (ставится только после отличной работы, отдельно 
не ставится) Статус "Принято" ставится от 6 и выше баллов. Ниже 6, задание не принимается. Идеальное, но не работающее, 
решение = 1 + 0 + 4 (+4 а не 5) = 5 - не принимается. Если всё работает, но стилю не соответствует (публичные поля, 
классы капсом) = 1 + 4 + 0 = 5 - не принимается

### Параметры создания проекта Spring Initializr
1. https://start.spring.io/
2. Project: Maven Project
3. Language: Java
4. Spring Boot: 2.7.0
5. Project Metadata
  - Group: ru.otus
  - Artifact: spring-03-testing-students
  - Name: spring-03-testing-students
  - Description: Project for Spring Boot
  - Package name: ru.otus.spring-03-testing-students
6. Packaging: Jar
7. Java: 11
8. Dependencies: No dependency selected

### Изменения в проекте после переноса логики 
1. Изменения в "точке входа" (в spring-03-testing-students по сравнению с spring-02-testing-students):

a) Изменяется порядок вызова контекста приложения

   В spring-02-testing-students поднятие контекста:
   public static void main(String[] args) {
       AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
       TestsForStudents testsForStudents = context.getBean(TestsForStudents.class);
       testsForStudents.runTest();
   }

   В spring-03-testing-students поднятие контекста::
   public static void main(String[] args) {
       ApplicationContext context = SpringApplication.run(Main.class, args);
       TestsForStudents testsForStudents = context.getBean(TestsForStudents.class);
       testsForStudents.runTest();
   }

b) Отсутствие необходимости аннотации @PropertySource("classpath:application.properties") для application.properties (файла)
Spring сам находит файл application.properties и читает из него настройки автоматически

c) Отсутствие необходимости аннотации @ComponentScan(basePackages = "ru.otus")

### Статьи по теме
1. Spring: @Value aннотация https://bit.ly/3tT6ZzL
2. Внешние данные конфигурации в Spring: Файлы форматов YAML и .properties https://bit.ly/3bp3Lh2
3. Локализация и интернационализация https://bit.ly/3Ngy5HS
4. Создание веб-приложения с несколькими языками с помощью Spring Boot https://bit.ly/3OhwqD6
5. Русские имена фамилии по-английски. Перевод русских букв в английские https://bit.ly/3HL1Vmq
6. ISO 639-2 Language Code List https://bit.ly/3OC8BWj
