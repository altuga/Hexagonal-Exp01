# Hexagonal Architecture Diagram

## Overview
This application demonstrates hexagonal architecture (ports and adapters) for a discount calculation system.

## Architecture Diagram

```
┌─────────────────────────────────────────────────────────────────────┐
│                         INBOUND ADAPTERS                            │
│                    (Driving/Primary Adapters)                       │
│                                                                     │
│  ┌──────────────────────────────────────────────────────────┐     │
│  │  DiscountController (REST API)                           │     │
│  │  POST /api/discounts/apply                               │     │
│  │  Request: {"amount": 1000.0}                             │     │
│  │  Response: 900.0                                         │     │
│  └──────────────────────────────────────────────────────────┘     │
│                              │                                      │
└──────────────────────────────┼──────────────────────────────────────┘
                               │
                               ▼
┌─────────────────────────────────────────────────────────────────────┐
│                        APPLICATION CORE                             │
│                          (Hexagon)                                  │
│                                                                     │
│  ┌────────────────────────────────────────────────────────┐       │
│  │  INBOUND PORT (Use Case Interface)                     │       │
│  │  ┌──────────────────────────────────────────┐          │       │
│  │  │  DiscountUseCase                         │          │       │
│  │  │  + applyDiscount(amount): double         │          │       │
│  │  └──────────────────────────────────────────┘          │       │
│  └────────────────────────────────────────────────────────┘       │
│                              │                                      │
│                              ▼                                      │
│  ┌────────────────────────────────────────────────────────┐       │
│  │  APPLICATION SERVICE                                    │       │
│  │  ┌──────────────────────────────────────────┐          │       │
│  │  │  DiscountService                         │          │       │
│  │  │  - discountRateRepository                │          │       │
│  │  │  + applyDiscount(amount): double         │          │       │
│  │  └──────────────────────────────────────────┘          │       │
│  └────────────────────────────────────────────────────────┘       │
│                              │                                      │
│                              ▼                                      │
│  ┌────────────────────────────────────────────────────────┐       │
│  │  DOMAIN MODEL (Business Logic)                         │       │
│  │  ┌──────────────────────────────────────────┐          │       │
│  │  │  Discount                                │          │       │
│  │  │  - minAmount: double                     │          │       │
│  │  │  - discountPercent: double               │          │       │
│  │  │  + appliesTo(amount): boolean            │          │       │
│  │  │  + calculateDiscountedAmount(...): double│          │       │
│  │  └──────────────────────────────────────────┘          │       │
│  └────────────────────────────────────────────────────────┘       │
│                              │                                      │
│                              ▼                                      │
│  ┌────────────────────────────────────────────────────────┐       │
│  │  OUTBOUND PORT (Repository Interface)                  │       │
│  │  ┌──────────────────────────────────────────┐          │       │
│  │  │  DiscountRateRepository                  │          │       │
│  │  │  + findAll(): List<Discount>             │          │       │
│  │  └──────────────────────────────────────────┘          │       │
│  └────────────────────────────────────────────────────────┘       │
│                              │                                      │
└──────────────────────────────┼──────────────────────────────────────┘
                               │
                               ▼
┌─────────────────────────────────────────────────────────────────────┐
│                       OUTBOUND ADAPTERS                             │
│                   (Driven/Secondary Adapters)                       │
│                                                                     │
│  ┌──────────────────────────────────────────────────────────┐     │
│  │  InMemoryDiscountRateRepository                          │     │
│  │  Stores discount rules in memory:                        │     │
│  │  - Amount >= 100   → 5% discount                         │     │
│  │  - Amount >= 1000  → 10% discount                        │     │
│  │  - Amount >= 10000 → 20% discount                        │     │
│  └──────────────────────────────────────────────────────────┘     │
│                                                                     │
└─────────────────────────────────────────────────────────────────────┘
```

## Flow of Control

1. **HTTP Request** → `DiscountController` receives POST request
2. **Inbound Adapter** → Controller calls `DiscountUseCase.applyDiscount(amount)`
3. **Application Service** → `DiscountService` retrieves discount rules from `DiscountRateRepository`
4. **Domain Logic** → `Discount.calculateDiscountedAmount()` applies business rules
5. **Outbound Adapter** → `InMemoryDiscountRateRepository` returns discount rules
6. **Response** → Discounted amount returned through the layers

## Package Structure

```
jug.istanbul
├── Hex01Application.java          (Main & Bean Configuration)
├── domain/
│   ├── Discount.java               (Entity + Business Logic)
│   └── port/
│       └── DiscountRateRepository.java  (Outbound Port)
├── application/
│   ├── port/in/
│   │   └── DiscountUseCase.java    (Inbound Port)
│   └── service/
│       └── DiscountService.java    (Application Service)
└── adapter/
    ├── in/web/
    │   └── DiscountController.java (Inbound REST Adapter)
    └── out/persistence/
        └── InMemoryDiscountRateRepository.java (Outbound Adapter)
```

## Key Principles

- **Domain Layer**: Pure business logic, no framework dependencies
- **Ports**: Interfaces that define boundaries (inbound for use cases, outbound for dependencies)
- **Adapters**: Implementations that connect external world to the application core
- **Dependency Direction**: Always points inward toward the domain (Dependency Inversion)
- **Testability**: Domain and service layers can be tested without adapters
- **Flexibility**: Adapters are swappable (e.g., replace in-memory with database)
