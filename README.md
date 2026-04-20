# 🚀 API E-commerce (Spring Boot)

[![Java](https://img.shields.io/badge/Java-21-007396?logo=openjdk)](https://www.java.com)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.5-6DB33F?logo=springboot)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.9-CE5B2A?logo=apachemaven)](https://maven.apache.org)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?logo=mysql)](https://www.mysql.com)
[![Swagger](https://img.shields.io/badge/Swagger-UI-85EA2D?logo=swagger)](https://swagger.io)

**API REST completa para e-commerce** desenvolvida em **Java 21 + Spring Boot** com foco em aprendizado, boas práticas e arquitetura limpa.

> Este projeto está em **desenvolvimento inicial** e serve como portfólio/prática técnica. O objetivo é evoluir para uma API robusta e pronta para produção.

## ✨ Funcionalidades Atuais

- ✅ CRUD completo de **Produtos**
- ✅ Documentação automática com **Swagger/OpenAPI**
- ✅ Validação de dados
- ✅ Tratamento global de exceções
- ✅ Configuração com variáveis de ambiente

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 4.0.5**
  - Spring Web MVC
  - Spring Data JPA
  - SpringDoc OpenAPI (Swagger)
- **Banco de dados**: MySQL 8
- **Lombok** (redução de boilerplate)
- **Maven** (gerenciamento de dependências)

## 📋 Pré-requisitos

- **JDK 21** ou superior
- **Maven 3.9+** (ou use o Maven Wrapper `./mvnw`)
- **MySQL 8.0+** (ou Docker)
- **Git**

## 🚀 Como Rodar Localmente

1. **Clone o repositório**
   ```bash
   git clone https://github.com/AndrewGRM/API-ecommerce.git
   cd API-ecommerce

2. Configure o banco de dados
   - Crie um banco chamado ecommerce_db
   - Ou configure as variáveis de ambiente (recomendado):
      ```bash
      export DATABASE_URL=jdbc:mysql://localhost:3306/ecommerce_db?createDatabaseIfNotExist=true
      export DATABASE_USERNAME=seu_usuario
      export DATABASE_PASSWORD=sua_senha

3. Execute a aplicação
