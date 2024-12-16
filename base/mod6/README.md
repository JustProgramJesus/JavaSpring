# Два небольших приложения order-service и order-status-service

## Общее описание

> Два приложения - order-service и order-status-service создают систему для обработки заказов, взаимодействуя между собой через систему обмена сообщениями Kafka.

### Order-service:
- Принимает POST запросы для создания заказов;
- Отправляет данные о заказе в Kafka-топик order-topic;
- Слушает топик order-status-topic, где отображаются статусы заказов.

### Order-status-service

- Слушает топик order-topic для получения данных о заказах;
- Формирует статус заказа и отправляет его в топик order-status-topic.

## Требования к системе

> Инструменты разработки:
- Java JDK 21 (Oracle)
- Maven 3.4.0
- Intellij IDEA или другой любой IDE с поддержкой Spring Boot

> Инструменты для работы с Kafka

- Docker и Docker Compose для запуска Kafka и Zookeeper
  
> Библиотеки и зависимости

- Spring Boot
- Spring Kafka
- Jackson Databind (для сериализации и десериализации JSON)
- Jackson JSR310 (для работы с Java 8+ типами времени)

## Подробно о каждом проекте

> Проект Order-service

Он выполняет следующие задачи:
- Принимает POST-запросы с заказами через эндпоинт /api/orders;
- Отправляет данные заказа (OrderEvent) в Kafka-топик order-topic;
- Слушает топик order-status-topic для получения статусов заказов.

> **Структура классов**

 Класс | Описание |
| --------------------- | -------------------------- |
| OrderServiceApplication | Основной класс для запуска приложения. |
| OrderEvent | Модель данных для отправки заказа в Kafka. |
| OrderController | Контроллер для обработки POST-запросов, принимающий заказы от клиента. |
| OrderStatusListener | Kafka Listener для получения статусов из топика order-status-topic. |
| KafkaProducerConfig | Конфигурация Kafka Producer для отправки сообщений в Kafka. |

### Как запустить проект
1. Убедиться, что Kafka и Zookeeper запущены:
```bash
docker-compose up -d
```
2. Собрать проект с помощью Maven
```bash
mvn clean install
```
3. Запуск проекта через Maven
```bash
java -jar target/(название собранного jar файла).jar
```

> Эндпоинты

| HTTP Метод | URL         | Описание         | Пример тела запроса                    |
| ---------- | ----------- | ---------------- | -------------------------------------- |
| POST       | /api/orders | Создание заказа. | { "product": "Laptop", "quantity": 3 } |

**Что должно вывести: Order sent to Kafka!**

> Проект Order-status-service

Он выполняет следующие задачи:
- Слушает топик order-topic для получения данных о заказах;
- Создаёт событие OrderStatusEvent с полями status (например, "CREATED") и текущей датой;
- Отправляет событие в топик order-status-topic.

> **Структура классов**

 Класс | Описание |
| --------------------- | -------------------------- |
| OrderStatusServiceApplication | Основной класс для запуска приложения. |
| OrderStatusEvent | Модель данных для отправки статуса заказа в Kafka. |
| OrderListener | Kafka Listener для получения заказов из топика order-topic. |
| KafkaConsumerConfig | 	Конфигурация Kafka Consumer для работы с Kafka. |

### Как запустить проект
1. Убедиться, что Kafka и Zookeeper запущены:
```bash
docker-compose up -d
```
2. Собрать проект с помощью Maven
```bash
mvn clean install
```
3. Запуск проекта через Maven
```bash
java -jar target/(название собранного jar файла).jar
```

## Взаимодействие между проектами
1. Отправка запроса в order-service:
Я рекомендую использовать Postman для отправки запросов, вот пример запроса:
Выбираем POST. В URL пишем: http://localhost:8080/api/orders
В Body => raw вставляем следующий код:
```JSON
{
    "product": "Название продука",
    "quantity": Количество_int_значение
}
```
2. Дальше происходит следующее:
- order-service отправляет сообщение с заказом в топик order-topic;
- order-status-service получает сообщение из order-topic, создает статус заказа и отправляет его в order-status-topic;
- order-status получает статус заказа из order-status-topic.

Логи, которые вы должны получить в Terrminal:
order-service:
Получаение статуса заказа из order-status-topic:
```JSON
Received message: {"status":"CREATED","date":"2024-12-16T15:44:57.662831400Z"}
```
order-status-service:
```JSON
Received message: {"product":"Laptop","quantity":3}
Sent status event: OrderStatusEvent{status='CREATED', date=2024-12-16T15:44:57.662831400Z}
```

# Итог
Два проекта успешно выполняют свои задачи:
- order-service отправляет заказы.
- order-status-service обрабатывает заказы и отправляет статусы.
- Kafka обеспечивает взаимодействие.
