# Customer Service Application

## Overview

The Customer Service Application is a RESTful service designed to manage customer data. This application is built using Java with Spring Boot and includes features such as security configuration, exception handling, and various CRUD operations for customer data.

## Project Structure

```
customer_service/
├── .gitignore
├── customerService.log
├── docker-compose.yml
├── Dockerfile
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── wrkspot/
│   │               └── customer/
│   │                   ├── CustomerApplication.java
│   │                   ├── config/
│   │                   ├── controller/
│   │                   ├── dto/
│   │                   ├── entity/
│   │                   ├── exception/
│   │                   ├── repository/
│   │                   ├── service/
│   │                   └── utils/
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── wrkspot/
│       │           └── customer/
│       └── resources/
├── target/
    └── [Build related files]
```

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- Docker (for containerized deployment)

### Installation

1. **Clone the repository:**
    ```bash
    git clone https://github.com/PanmandHarshad/wrkspot.git
    cd wrkspot
    ```

2. **Build the project:**
    ```bash
    ./mvn clean install
    ```

3. **Run the application:**
    ```bash
    ./mvn spring-boot:run
    ```

### Docker Deployment

1. **Build the Docker image:**
    ```bash
    mvn compile jib:dockerBuild
    ```

2. **Run the Docker container:**
    ```bash
    docker-compose up
    ```

### Docker compose instructions:
Clone the repository
```bash
git clone https://github.com/PanmandHarshad/wrkspot.git
```
Go to customer directory
```bash
cd customer
```
Pull docker images using docker compose
```bash
docker compose pull
```
Start the application in detached mode
```bash
docker compose up -d
```

## Project Details

### Configuration

- **Security Configuration:** Located in `src/main/java/com/wrkspot/customer/config/SecurityConfig.java`
- **Application Properties:** Located in `src/main/resources/application.properties`

### API Endpoints

The application provides the following RESTful endpoints:

- **Customer Controller:**
  - `GET /customers` - Get a list of all customers if request param is not specified, request params are name, state, city
  - `POST /create` - Creating new customer based on provided details of customers
  - `POST /authenticate` - Authenticate the user and return the token
  - `GET /only-in-a` - Get customers only in list A
  - `GET /only-in-b` - Get customers only in list B
  - `GET /in-both-a-and-b` - Get customers in both lists A and B

API Documentation is available at:
http://wrkspot/swagger-ui/index.html


### Exception Handling

Global exception handling is configured in `src/main/java/com/wrkspot/customer/exception/GlobalExceptionHandler.java`.

### Testing

Unit tests are located in the `src/test/java/com/wrkspot/customer/` directory. To run the tests:

```bash
./mvn test
```

## Additional Resources

- **customerService.log:** Log file for the application
- **Dockerfile:** Docker configuration for containerizing the application
- **docker-compose.yml:** Docker Compose configuration for multi-container deployment

## Docker image is available at:
- [Customer Image](https://hub.docker.com/r/panmandharshaddev/customer)
