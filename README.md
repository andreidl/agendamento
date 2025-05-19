
# 🏦 Sistema de Agendamento de Transferências Financeiras

## 📌 Visão Geral

Esta aplicação permite o agendamento de transferências bancárias entre contas, respeitando regras de validação como contas numéricas e datas válidas. É composta por dois módulos:

- **Backend:** API REST em Java 11 com Spring Boot.
- **Frontend:** Interface web desenvolvida em Vue.js 3.

---

## ⚙️ Tecnologias e Versões Utilizadas

### 🔙 Backend (Java)
- **Java 11**
- **Spring Boot 3.x**
- Spring Web
- Spring Data JPA (com H2)
- **H2 Database** (modo memória)
- Maven

### 🌐 Frontend (Vue.js)
- **Vue 3 (Composition API)**
- Vite (para build e dev server)
- CSS puro (com possíveis melhorias com Tailwind futuramente)

---

## 🧱 Decisões Arquiteturais

- **Separação clara de camadas:** Controller, Service e Model no backend.
- **Validação no backend e no frontend:** garantindo segurança e experiência do usuário.
- **Banco em memória:** usando H2 para testes e desenvolvimento rápido sem dependência de banco externo.
- **Formatação e máscara de moeda:** feita diretamente no Vue, mas valor real (número) é enviado ao backend.
- **Comunicação via REST:** JSON entre frontend e backend.

---

## 🚀 Como subir o projeto

### 📁 Requisitos
- Java 11+
- Node.js 16+
- Maven

---

### 🧪 Backend

```bash
# Acesse a pasta do backend
cd backend

# Compile e execute
./mvnw spring-boot:run
```

> A API será iniciada em: `http://localhost:8080/transferencias`

---

### 🌐 Frontend

```bash
# Acesse a pasta do frontend
cd frontend

# Instale as dependências
npm install

# Rode o servidor de desenvolvimento
npm run serve
```

> A aplicação Vue será servida em: `http://localhost:8081`

---

