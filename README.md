DatabaseProject
Цей проєкт демонструє використання бази даних у веб-додатку, створеному на Java.
Легко керуйте даними вашого ресторану за допомогою цього веб-додатку.

Як запустити проєкт
Проєкт можна запустити двома способами: за допомогою Docker або вручну.

Запуск за допомогою Docker
Переконайтеся, що у вас встановлений Docker.
Якщо Docker ще не встановлений, завантажте його з офіційного сайту:
Завантажити Docker.

Клонуйте репозиторій.
Налаштуйте параметри середовища.

Відкрийте файли application.properties або docker-compose.yaml.
Замініть ${LOGIN} і ${PASSWORD} на свої дані для доступу до бази даних.
Запустіть проєкт.
Виконайте команду:

bash
Копировать код
docker-compose up
Відкрийте браузер.
Перейдіть за адресою:
http://localhost:8080.

Щоб зупинити контейнер.
Виконайте команду:

bash
Копировать код
docker-compose down
2. Запуск вручну
Встановіть необхідні інструменти.

PostgreSQL:
Завантажте PostgreSQL з офіційного сайту.
Maven:
Завантажте та встановіть Maven з офіційного сайту.
JDK 21:
Завантажте JDK 21 з офіційного сайту.
Клонуйте репозиторій.

bash
Копировать код
git clone https://github.com/your-username/your-repo.git
cd your-repo
Налаштуйте параметри середовища.

Відкрийте файли application.properties та docker-compose.yaml.
Замініть ${LOGIN} і ${PASSWORD} на свої дані для доступу до бази даних.
Запакуйте проєкт за допомогою Maven.
Виконайте команду:

bash
Копировать код
mvn package
Запустіть проєкт.

За допомогою вашої IDE (наприклад, IntelliJ IDEA).
Або напряму з папки target:
bash
Копировать код
java -jar target/your-project-name.jar
Відкрийте браузер.
Перейдіть за адресою:
http://localhost:8080.

Рекомендації
Керування параметрами:
Використовуйте змінні середовища для конфіденційних даних (логін, пароль).
Перевірка портів:
У разі конфліктів портів, змініть значення у файлах application.properties або docker-compose.yaml.
