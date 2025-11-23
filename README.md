# Hexagonal Architecture Discount Application

A simple Java Spring Boot application demonstrating Hexagonal Architecture (Ports and Adapters) principles.

## Project Structure

The project follows the hexagonal architecture package structure:

```
jug.istanbul
├── domain/                 # Core business logic (The Hexagon)
│   ├── Discount.java       # Domain Entity & Logic
│   └── port/               # Outbound Ports
├── application/            # Application Layer
│   ├── port/in/            # Inbound Ports (Use Cases)
│   └── service/            # Application Services
└── adapter/                # Adapters Layer
    ├── in/web/             # Inbound Adapters (REST Controller)
    └── out/persistence/    # Outbound Adapters (Repositories)
```

## Features

- **Domain-Centric Design**: Business logic is isolated in the domain layer.
- **Ports & Adapters**: Clear separation between core logic and external concerns.
- **Rule-Based Discounts**:
  - Amount >= 100: 5% discount
  - Amount >= 1000: 10% discount
  - Amount >= 10000: 20% discount

## API Usage

### Apply Discount

**Endpoint:** `POST /api/discounts/apply`

**Request:**
```json
{
  "amount": 1000.0
}
```

**Response:**
```
900.0
```

## Running the Application

1. Build the project:
   ```bash
   mvn clean package
   ```

2. Run the application:
   ```bash
   java -jar target/hex01-0.0.1-SNAPSHOT.jar
   ```
