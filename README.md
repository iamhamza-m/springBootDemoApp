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


### Test Data

You can use below test data to setup your application and have some dummy data to run the operations.

```json
[{
"carModel": 1,
"carMakeYear": 2018,
"carName": "Civic",
"carCompany": "Honda"
},
{
"carModel": 2,
"carMakeYear": 2019,
"carName": "Accord",
"carCompany": "Honda"
},
{
"carModel": 3,
"carMakeYear": 2020,
"carName": "City",
"carCompany": "Honda"
},
{
"carModel": 4,
"carMakeYear": 2017,
"carName": "Corolla",
"carCompany": "Toyota"
},
{
"carModel": 5,
"carMakeYear": 2018,
"carName": "Camry",
"carCompany": "Toyota"
},
{
"carModel": 6,
"carMakeYear": 2021,
"carName": "Fortuner",
"carCompany": "Toyota"
},
{
"carModel": 7,
"carMakeYear": 2016,
"carName": "Swift",
"carCompany": "Maruti"
},
{
"carModel": 8,
"carMakeYear": 2019,
"carName": "Baleno",
"carCompany": "Maruti"
},
{
"carModel": 9,
"carMakeYear": 2022,
"carName": "Brezza",
"carCompany": "Maruti"
},
{
"carModel": 10,
"carMakeYear": 2020,
"carName": "Creta",
"carCompany": "Hyundai"
},
{
"carModel": 11,
"carMakeYear": 2021,
"carName": "Verna",
"carCompany": "Hyundai"
},
{
"carModel": 12,
"carMakeYear": 2023,
"carName": "i20",
"carCompany": "Hyundai"
},
{
"carModel": 13,
"carMakeYear": 2015,
"carName": "X5",
"carCompany": "BMW"
},
{
"carModel": 14,
"carMakeYear": 2018,
"carName": "3 Series",
"carCompany": "BMW"
},
{
"carModel": 15,
"carMakeYear": 2022,
"carName": "7 Series",
"carCompany": "BMW"
},
{
"carModel": 16,
"carMakeYear": 2019,
"carName": "A4",
"carCompany": "Audi"
},
{
"carModel": 17,
"carMakeYear": 2020,
"carName": "A6",
"carCompany": "Audi"
},
{
"carModel": 18,
"carMakeYear": 2021,
"carName": "Q7",
"carCompany": "Audi"
},
{
"carModel": 19,
"carMakeYear": 2020,
"carName": "Model 3",
"carCompany": "Tesla"
},
{
"carModel": 20,
"carMakeYear": 2021,
"carName": "Model Y",
"carCompany": "Tesla"
},
{
"carModel": 21,
"carMakeYear": 2023,
"carName": "Cybertruck",
"carCompany": "Tesla"
}]
```