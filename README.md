# order-management-system
- This is my self-learning and experiment to set up a microfrontend + microservice based application simulating an e-commerce. Which would have all the necessary functionalities and feature of a real world application.

- Built to demonstrate: Next.js micro-frontends, Spring Boot microservices, Kafka event streaming, Docker, Kubernetes, and CI/CD — all on a zero-cost infrastructure stack.

## Intro into folder structure
order-management-system/
├── .github/
│   └── workflows/          ← CI/CD 
├── frontend/
│   ├── shell-app/          ← Next.js host
│   ├── product-app/        ← micro-frontend remote
│   └── cart-app/           ← micro-frontend remote
├── backend/
│   ├── api-gateway/        ← Spring Cloud Gateway
│   ├── discovery-server/   ← self-registry
│   ├── order-service/      ← Spring Boot
│   ├── inventory-service/  ← Spring Boot
│   └── notification-service/ ← Spring Boot
├── k8s/                    ← Kubernetes YAMLs
├── docker-compose.yml      ← wires everything
└── README.md               ← architecture doc

## Rendering platforms
| Platform | Projects to create |
|---|---|
| Vercel | one per frontend) | 
| Render | one per backend service) | 
| Railway | shared PostgreSQL) | 
