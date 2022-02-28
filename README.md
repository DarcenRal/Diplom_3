# Дипломный проект по профессии «Тестировщик»

Ссылка на дипломное задание <a href= https://github.com/netology-code/qa-diploma>задание</a>.

## Тестовая документация

## Запуск приложения
### Подготовительный этап
1. Установить и запустить IntelliJ IDEA;
1. Установать и запустить Docker Desktop;
1. Скопировать репозиторий с Github [по ссылке](https://https://github.com/DarcenRal/Diplom_3.git).
1. Открыть проект в IntelliJ IDEA.

### Запуск тестового приложения

1. Запустить MySQL, PostgreSQL, NodeJS через терминал командой:
   ```
   docker-compose up
   ```
1. В новой вкладке терминала запустить тестируемое приложение:
   * Для MySQL: 
   ```
   java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar artifacts/aqa-shop.jar
   ```
   * Для PostgreSQL: 
   ```
   java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar artifacts/aqa-shop.jar
   ```
   .
1. Убедиться в готовности системы. Приложение должно быть доступно по      адресу:
   ```
   http://localhost:8080/
   ```
### Запуск тестов


В новой вкладке терминала запустить тесты:
1. Для MySQL: 
   ```
   gradlew clean test -Ddb.url=jdbc:mysql://localhost:3306/app
   ```
1. Для PostgreSQL: 
   ```
   gradlew clean test -Ddb.url=jdbc:postgresql://localhost:5432/app
   ```

## Формирование отчёта о тестировании

Для запуска и просмотра отчета "Allure" по результатам тестирования выполнить команду: ./gradlew allureReport, затем ./gradlew allureServe