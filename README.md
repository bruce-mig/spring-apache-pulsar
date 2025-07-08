# spring-apache-pulsar

Pub/Sub with Apache Pulsar using Spring Boot

---

## Overview

This project demonstrates how to integrate [Apache Pulsar](https://pulsar.apache.org/) with a Spring Boot application to implement pub/sub messaging. It provides a local Pulsar cluster setup via Docker Compose, code samples for producing and consuming messages, and useful references for managing Pulsar clusters and users.

---

## Features

- **Local Pulsar cluster** setup with Docker Compose (both standalone and multi-node options)
- **Spring Boot integration** for producing and consuming Pulsar messages
- **Swagger UI** for testing API endpoints
- Pulsar Manager UI for cluster/user management

---

## Prerequisites

- [Docker](https://www.docker.com/) & [Docker Compose](https://docs.docker.com/compose/)
- Java 17+ and Maven (for Spring Boot app)
- Optional: [Pulsar Manager](https://github.com/apache/pulsar-manager) for UI management

---

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/bruce-mig/spring-apache-pulsar.git
cd spring-apache-pulsar
```

### 2. Set up Pulsar cluster

Create data directories (Linux):

```bash
sudo mkdir -p ./data/zookeeper ./data/bookkeeper
sudo chown -R 10000 data
```

Start multi-node Pulsar cluster:

```bash
docker compose -f cluster-compose.yaml up -d
```

Or, for standalone mode:

```bash
docker compose -f standalone-compose.yaml up -d
```

Access Pulsar Manager UI at: http://localhost:9527

### 3. Create Admin user

```bash
CSRF_TOKEN=$(curl http://localhost:7750/pulsar-manager/csrf-token)

curl \
-H "X-XSRF-TOKEN: $CSRF_TOKEN" \
-H "Cookie: XSRF-TOKEN=$CSRF_TOKEN;" \
-H "Content-Type: application/json" \
-X PUT http://localhost:7750/pulsar-manager/users/superuser \
-d '{"name": "admin", "password": "apachepulsar", "description": "test", "email": "username@test.org"}'
```

## Application Usage
#### 1. Build & Run Spring Boot App
```bash
# producer
cd producer
./mvnw spring-boot:run

# consumer
cd consumer
./mvnw spring-boot:run
```

#### 2. Access API Documentation
Swagger UI: http://localhost:9191/swagger-ui/index.html

---

##  Apache Pulsar Architecture
![pulsar](pulsar.png)

---
## Project Structure

- producer: Spring Boot source code for Pulsar producer  
- consumer: Spring Boot source code for Pulsar consumer  
- commons: Commons package  
- cluster-compose.yaml, standalone-compose.yaml: Docker Compose configurations  
- data/: Volume mounts for Pulsar/ZooKeeper/BookKeeper data  
- pulsar.png: Architecture diagram  

## References
[Apache Pulsar Documentation](https://pulsar.apache.org/docs/)  
[Spring for Apache Pulsar](https://spring.io/projects/spring-pulsar)  
[Pulsar Manager](https://github.com/apache/pulsar-manager)  

## License
This project is licensed under the [MIT License](LICENCE).

Contributing
Contributions are welcome! Please open issues or submit pull requests for improvements or bug fixes.
