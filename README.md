# Lab 6: REST Controllers and AOP

This project demonstrates RESTful web services implementation using Spring Boot with Aspect-Oriented Programming (AOP) for cross-cutting concerns.

## Features

### REST API Endpoints
- **GET** `/api/cartoons` - Retrieve all cartoons (200 OK)
- **GET** `/api/cartoons/appeared/{year}` - Find cartoons by year (200 OK)
- **GET** `/api/cartoons/{id}` - Find cartoon by ID (200 OK, 404 if not found)
- **DELETE** `/api/cartoons/{id}` - Delete cartoon by ID (204 NO CONTENT, 404 if not found)
- **DELETE** `/api/cartoons/appeared/{year}` - Delete all cartoons by year (204 NO CONTENT)

### Key Technologies
- Spring Boot 3.5.5
- Spring Web (REST Controllers)
- Spring AOP (Aspect-Oriented Programming)
- Spring JDBC (Data Access)
- H2 Database (In-memory)
- OpenAPI/Swagger (API Documentation)
- Lombok (Reduces boilerplate code)

### Architecture Layers
- **Controller Layer**: REST endpoints with proper HTTP status codes
- **Service Layer**: Business logic and transaction management
- **Repository Layer**: Data access using Spring JDBC
- **DTO Layer**: Data Transfer Objects to hide database structure
- **AOP Layer**: Cross-cutting concerns (logging, transaction monitoring)

### AOP Implementation
The project includes AOP aspects for:
- Logging method executions (`@Before`, `@After`, `@AfterReturning`)
- Exception handling (`@AfterThrowing`)
- Transaction monitoring (`@After` for `@Transactional` methods)

### Exception Handling
- Centralized exception handling using `@RestControllerAdvice`
- Custom `CartoonNotFoundException` with 404 NOT FOUND response
- Error responses include message and timestamp

### API Documentation
- Swagger UI available at: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI specification for interactive API testing

## Running the Application

1. Ensure Java 24 is installed
2. Run the application:
   ```bash
   mvn spring-boot:run
   ```
3. Access Swagger UI at: `http://localhost:8080/swagger-ui/index.html`
4. Test endpoints using Postman or Swagger UI

## Project Structure

```
src/main/java/ie/spring/heroes/
├── controller/
│   ├── CartoonController.java       # REST endpoints
│   └── GlobalExceptionHandler.java  # Centralized exception handling
├── service/
│   ├── CartoonService.java          # Service interface
│   ├── CartoonServiceImpl.java      # Service implementation
│   └── CartoonNotFoundException.java # Custom exception
├── daos/
│   ├── CartoonRepository.java       # Repository interface
│   ├── CartoonRepositoryImpl.java   # Repository implementation
│   └── entities/
│       └── Cartoon.java             # Entity class
├── dtos/
│   └── CartoonDTO.java              # Data Transfer Object
└── aspects/
    └── Aspects.java                # AOP aspects for logging
```

## Database

- H2 in-memory database
- Schema and sample data loaded automatically on startup
- Cartoons table with fields: cartoon_id, title, creator, year_appeared, genre

## Dependencies

- `spring-boot-starter-web` - Web and REST support
- `spring-boot-starter-aop` - Aspect-Oriented Programming
- `spring-boot-starter-data-jdbc` - JDBC support
- `springdoc-openapi-starter-webmvc-ui` - Swagger/OpenAPI documentation
- `commons-lang3` - Apache Commons Lang (security fix)
- `lombok` - Reduces boilerplate code
- `h2` - In-memory database

## Best Practices Implemented

- ✅ Resource-based REST endpoints
- ✅ Appropriate HTTP status codes
- ✅ DTOs to hide database structure
- ✅ Centralized exception handling
- ✅ AOP for cross-cutting concerns
- ✅ Transaction management
- ✅ Clean separation of concerns
