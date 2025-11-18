# Gateway Service

API Gateway service for My City Connect project. This service provides centralized routing for all microservices.

## Overview

The Gateway Service acts as a single entry point for all client requests, routing them to the appropriate microservices:
- OAuth Service (Authentication & Authorization)
- Visa Service
- Registration Service

## Port Configuration

- **Gateway Service**: `9098`
- **OAuth Service**: `9097`
- **Visa Service**: `9096`
- **Registration Service**: `9091`

## Routing Configuration

The gateway routes requests based on URL path patterns:

| Path Pattern | Target Service | Port |
|-------------|---------------|------|
| `/api/auth/**` | oauth-service | 9097 |
| `/api/visa/**` | visa-service | 9096 |
| `/api/registration/**` | registration-service | 9091 |

## Usage

### Running the Service

```bash
mvn spring-boot:run
```

### Accessing Services Through Gateway

Instead of calling services directly:
- ~~http://localhost:9097/api/auth/login~~
- ~~http://localhost:9096/api/visa/apply~~
- ~~http://localhost:9091/api/registration/citizen~~

Use the gateway:
- http://localhost:8080/api/auth/login
- http://localhost:8080/api/visa/apply
- http://localhost:8080/api/registration/citizen

## Features

- **Centralized Routing**: Single entry point for all services
- **CORS Configuration**: Global CORS handling for cross-origin requests
- **Health Monitoring**: Actuator endpoints for health checks and metrics
- **Load Balancing**: Can be configured for multiple service instances
- **Request/Response Filtering**: Built-in support for request/response transformation

## Monitoring

Health check endpoint:
```
GET http://localhost:9098/actuator/health
```

Gateway routes information:
```
GET http://localhost:9098/actuator/gateway/routes
```

## Technology Stack

- Spring Boot 3.3.5
- Spring Cloud Gateway 2023.0.4
- Java 21
