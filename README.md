# SpringBootDemoApp

This repository contains a **Spring Boot demo application** that showcases a basic Spring Boot project setup and common features. It is primarily used as a **learning and practice reference** for Spring Boot concepts and common backend patterns.

## About

This project was created to practice and revise Spring Boot fundamentals in a structured way. It can be referred to in the future for:

- Understanding Spring Boot project structure
- Practicing REST APIs
- Experimenting with database operations
- Revising concepts for interviews or real-world usage

## Contents
```bash
springBootDemoApp
├── .mvn/
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── test/
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
```

- `src/main/java` – Application source code
- `src/main/resources` – Application configuration
- `src/test/java` – Unit and integration tests
- `pom.xml` – Maven dependencies and build configuration

## Getting Started

### Prerequisites

Make sure the following are installed:

- Java JDK 17 or higher (Recommended JDK 21)
- Maven
- Git

### Clone the Repository

```bash
git clone https://github.com/iamhamza-m/springBootDemoApp.git
cd springBootDemoApp
```

Build and Run the Application

Using Maven:
```bash
mvn clean install
mvn spring-boot:run
```

Or using Maven Wrapper:

```bash
./mvnw clean install
./mvnw spring-boot:run
```

By default, the application runs on:

```bash
http://localhost:8080
```
