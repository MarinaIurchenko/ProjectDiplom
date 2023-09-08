# Дипломный проект по профессии «Тестировщик ПО»
Дипломный проект — автоматизация тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.
## Запуск приложения
### Подготовка
1. Установить и запустить IntelliJ IDEA;
2. Установить и запустить Docker Desktop;
3. Установить и запустить DBeaver;
4. Сделать `git clone` репозитория с Github [по ссылке](https://github.com/MarinaIurchenko/ProjectDiplom);
5. Открыть проект `ProjectDiplom` в IntelliJ IDEA;
6. Создать соединение с MySQL в DBeaver;
7. Создать соединение с PostgreSQL в DBeaver;

## Запуск тестового приложения
- Запустить MySQL, PostgreSQL, NodeJS через терминал командой:    
    ```
   docker-compose up --build
   ```
- Запустить приложение для двух ДБ:
* MySQL:
    ```
   java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar artifacts/aqa-shop.jar
    ```
* PostgreSQL:
   ```
   java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar artifacts/aqa-shop.jar
   ```
- Проверить, что ссылка открывается и приложение доступно по адресу:
   ```
   http://localhost:8080/
   ```

## Запуск тестов

- Для MySQL:
   ```
   ./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"
   ```
- Для PostgreSQL:
   ```
   ./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"
   ```

## Формирование отчёта о тестировании
* Сформировать Allure-репорт можно командой:
   ```
   ./gradlew allureServe
   ```
Отчет откроется автоматически. 
* Сформировать Gradle-репорт можно командой:
   ```
   ./gradlew clean build
   ```
Для просмотра отчета Gradle: 
* Пройти путь: `C:\...\qa-diploma\build\reports\tests\test\index.html`
* Открыть файл `index.html` в используемом браузере. 
