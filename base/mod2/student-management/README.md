# **Описание**

**Student Management Application** - это небольшое консольное приложение «Учёт студентов». Интерфейс консольного приложения реализуется с помощью стартера Spring Shell. Сущность «Студент» представляет из себя идентификатор (id), имя (firstName), фамилию (lastName) и возраст (age).
Оно позволяет добавлять, удалять, просматривать и очищать список студентов. Поддерживает докеризацию для удобного запуска в контейнере.

### Функциональность

Приложение предоставляет следюущие команды для работы со студентами:
- **Добавление студента** - добавляет нового студента с автоматически генерируемым идентификатором (ID)
- **Удаление студента** - добавляет нового студента с автоматически генерируемым идентификатором (ID)
- **Просмотр списка студентов** - выводит всех студентов в системе
- **Очистка списка студентов** - удаляет всех студентов из системы

> Есть обработка событий создания и удаления студентов с выводом сообщений в консоль.

# Установка и запуск

> Локальный запуск

1. Должны быть установлены Java и Maven
2. Нужно перейти в корневую директорию проекта и выполнить команду для сборки:
```shell
mvn clean package
```
3. Запустите приложение
```shell
mvn spring-boot:run
```
> После запуска вы должны попасть на консольный интерфейс приложения, где сможете вводить команды для управления студентами.

# Docker

> Приложение поддерживает запуск в контейнере Docker. Для этого выполните следующие шаги:

1. Соберите Docker-образ:
```shell
docker build -t student-management .
```
2. Запустите контейнер с приложением:
```shell
docker run -it student-management
```

> Приложение должно открыться в интерактивной консоли внутри контейнера, где будут доступны все команды.

# Использование программы / Доступные команды

### Добавление студента

Чтобы добавить нового студента, нужно ввести следующую команду:

Пример ввода: add-student Мамаев Сергей 20
```shell
add-student <firstName> <lastName> <age>
```

### Удаление студента

Чтобы удалить студента по идентификатору (ID), нужно ввесли следующую команду:

Пример ввода: remove-student 2
```shell
remove-student <id>
```

### Просмотр всех студентов

Чтобы посмотреть полный список студентов, нужно ввести следующую команду:

Пример ввода: list-students
```shell
list-students
```

### Очистка списка студентов

Чтобы очистить полный список студентов, нужно ввесли следующую команду:

Пример ввода: clear-students
```shell
clear-students
```

# События

> При добавлении и удалении студентов в приложении срабатывают события:

- При добавлении студента в консоль выводится информация о созданном студенте.
- При удалении студента выводится сообщение с ID удалённого студента.

# Инициализация студентов при старте

> Приложение может автоматически добавлять студентов при старте. Это можно настроить в файле application.properties

Пример настройки:
```properties
student.init.enabled=true;
```