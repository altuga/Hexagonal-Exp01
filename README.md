# Hex01 — Hexagonal Architecture Example (Java + Spring Boot)

This is a minimal Java example demonstrating Hexagonal Architecture (ports & adapters) inspired by Alistair Cockburn's article.

Project structure (important parts):
- `com.example.hex.domain` — domain entity and outbound port (`Discount`, `DiscountRepository`).
- `com.example.hex.application.port.in` — inbound port / use case (`DiscountUseCase`).
- `com.example.hex.application.service` — application service implementing use cases (`DiscountService`).
- `com.example.hex.adapter.out.persistence` — outbound adapter (in-memory repo).
- `com.example.hex.adapter.in.web` — inbound adapter (REST controller).

Prerequisites
- Java 17
- Maven

Build
```bash
mvn -DskipTests package
```

Run
```bash
java -jar target/hex01-0.0.1-SNAPSHOT.jar
```

Example API (after app starts on port 8080)

Create a discount:
```bash
curl -s -X POST http://localhost:8080/api/discounts -H "Content-Type: application/json" -d '{"name":"Black Friday","percent":20.0}' | jq
```

List discounts:
```bash
curl -s http://localhost:8080/api/discounts | jq
```

Apply discount to an amount (replace `<id>`):
```bash
curl -s -X POST http://localhost:8080/api/discounts/<id>/apply -H "Content-Type: application/json" -d '{"amount":100.0}' | jq
```

Notes
- This project is intentionally small to clearly show the hexagonal layering. Swap the `InMemoryTaskRepository` bean with a JPA or other adapter to persist tasks without changing the application service or controller.
