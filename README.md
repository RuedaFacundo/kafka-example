# Proyecto de ejemplo de uso de Kafka

Este repositorio contiene una API desarrollada en Spring Boot que interactúa con Kafka y MongoDB. Permite la comunicación entre productores y consumidores de mensajes Kafka, almacenando los datos en una base de datos MongoDB.

## Configuración con Docker Compose

### Instrucciones de uso
1. Crear un archivo yml para correr con docker-compose

```yaml
version: '3.8'

services:
   zookeeper:
      image: confluentinc/cp-zookeeper:latest
      ports:
         - "2181:2181"
      environment:
         ZOOKEEPER_CLIENT_PORT: 2181
         ZOOKEEPER_TICK_TIME: 2000

   kafka:
      image: confluentinc/cp-kafka:latest
      ports:
         - "9092:9092"
      environment:
         KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
         KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
         KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
      depends_on:
         - zookeeper

   mongodb:
      image: mongo:latest
      ports:
         - "27017:27017"
      volumes:
         - mongo-data:/data/db

volumes:
   mongo-data:
```
2. Clonar el repositorio y levantar la api de manera local configurando las siguientes variables de entorno:

SPRING_DATA_MONGODB_URI=mongodb://localhost:27017/local
SPRING_KAFKA_BOOTSTRAP_SERVERS=PLAINTEXT://localhost:9092

## Funcionalidades de la API

### Productor y Consumidor Kafka

La API implementa las siguientes funcionalidades:

- Productor: Envía mensajes a un topico Kafka.
- Consumidor: Recibe mensajes desde un topico Kafka y realiza las siguientes acciones:
  - SAVE: Guarda el mensaje recibido en la base de datos MongoDB.
  - DELETE: Elimina el mensaje correspondiente de la base de datos MongoDB.

## Ejemplos de uso con Postman

### Guardado en base de datos

```bash
curl --location 'http://localhost:8080/api/kafka/publish' \
--header 'Content-Type: application/json' \
--data '{
    "feature": "SAVE",
    "message": "Mensaje de prueba"
}'
```

### Borrado en base de datos

```bash
curl --location 'http://localhost:8080/api/kafka/publish' \
--header 'Content-Type: application/json' \
--data '{
    "id": "917d6642-9777-4b30-aecf-851de8a7d708",
    "feature": "DELETE",
    "message": "Mensaje de prueba"
}'
```