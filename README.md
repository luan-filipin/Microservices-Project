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
- Docker (opcional)
- Postman (para testes)

## ✅ Funcionalidades

- Login com autenticação JWT
- Registro de usuários
- Criação, busca, atualização e exclusão de funcionários
- Autorização baseada em roles (`ROLE_USER`, `ROLE_ADMIN`, etc.)
- Validação de tokens no Gateway
- Comunicação entre serviços via Eureka Discovery

## ▶️ Como rodar o projeto

### Pré-requisitos

- Java 17+
- Maven
- MongoDB rodando localmente (ou via Docker)
- (Opcional) Docker e Docker Compose

### Passo a passo

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/seu-usuario/nome-do-repositorio.git

