# Microservices Project

Este projeto é uma arquitetura baseada em **Microserviços** utilizando **Spring Boot**, com autenticação via **JWT**, comunicação por meio de um **API Gateway** e descoberta de serviços com **Eureka Server**.

## 📦 Estrutura dos Microserviços

- **auth-service**: Responsável pela autenticação de usuários, geração e validação de tokens JWT.
- **employee-service**: Realiza o CRUD de funcionários (Employees), protegendo as rotas com autenticação e autorização.
- **gateway-service**: API Gateway utilizando Spring Cloud Gateway, responsável por rotear as requisições e validar tokens.
- **eureka-server**: Descoberta de serviços.

## 🔧 Tecnologias Utilizadas

- Java 17
- Spring Boot 3.x
- Spring Security (JWT)
- Spring Cloud Gateway
- Spring WebFlux
- Spring Data MongoDB
- Spring Cloud Eureka
- Lombok
- MapStruct


## ✅ Funcionalidades

- Login com autenticação JWT
- Registro de usuários
- Criação, busca, atualização e exclusão de funcionários
- Autorização baseada em roles (`ROLE_USER`, `ROLE_ADMIN`, etc.)
- Validação de tokens no Gateway
- Comunicação entre serviços via Eureka Discovery

## 🚀 Endpoints da API

### 📌 auth-service (`/auth-service/api/user`)

| Método | Rota             | Descrição               | Autenticação |
|--------|------------------|-------------------------|--------------|
| POST   | `/create`        | Criação de usuário      | ❌ Não       |
| POST   | `/login`         | Login e geração de JWT  | ❌ Não       |

### 📌 employee-service (`/employee-service/api/employee`)

| Método | Rota                  | Descrição                      | Autenticação |
|--------|-----------------------|--------------------------------|--------------|
| POST   | `/create`             | Criação de funcionário         | ✅ Sim       |
| GET    | `/findByDocument/{}` | Buscar funcionário por Document     | ✅ Sim       |
| GET    | `/findAll`           | Buscar todos os funcionários   | ✅ Sim       |
| DELETE | `/delete/{}`         | Remover funcionário por Document    | ✅ Sim       |
| PUT    | `/put/{}`            | Atualizar funcionário por Document  | ✅ Sim       |

> **Importante:** todas as rotas do `employee-service` exigem token JWT válido que é validado pelo Gateway antes de acessar o microserviço.

