# Spring Boot API

## 📖 Descrição

Esta API foi construída usando **Spring Boot** e oferece endpoints para gerenciar pedidos, autenticação, envio de e-mails e outras funcionalidades. 

## 🛠️ Tecnologias Utilizadas

- **Java 21+**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **Spring Security**
- **Spring Mail** (Envio de e-mails)
- **H2 / MySQL** (Banco de Dados)
- **JWT** (JSON Web Token para autenticação)
- **Maven** (Gerenciamento de dependências)

---

## ⚙️ Funcionalidades

Esta API oferece as seguintes funcionalidades:

### **1. Registro de Usuário**
- **Endpoint**: `POST /api/register`
- Permite a criação de novos usuários no sistema.
- Geração automática de um e-mail de boas-vindas para o novo usuário.

### **2. Autenticação**
- **Endpoint**: `POST /api/login`
- Permite que os usuários façam login utilizando nome de usuário e senha.
- Retorna um **token JWT** para autenticação subsequente.

### **3. Envio de E-mails**
- **Funcionalidade**: Envio automático de e-mails de confirmação ao criar um pedido.
- Utiliza o **Spring Mail** para enviar e-mails através de servidores SMTP configuráveis.

### **4. Proteção de Endpoints**
- **Autenticação via JWT**: Endpoints protegidos requerem um token JWT no cabeçalho para acesso.
- **Spring Security**: Controle de acesso baseado em papéis de usuário (ex: administrador, usuário básico).

### **5. CRUD de Pedido**
- **Função**: Gerenciamento de Pedidos.
- **Endpoints**:
    - `GET /api/users` - Listar todos os pedidos.
    - `GET /api/users/{id}` - Obter detalhes de um pedido específico.
    - `PUT /api/users/{id}` - Atualizar os dados de um pedido.
    - `DELETE /api/users/{id}` - Excluir um pedido.

### **6. Regras de Negócio**
- **Hashing de Senha**: Senhas dos usuários são sempre armazenadas de forma segura, utilizando **BCrypt**.
- **Papéis de Usuário**: Suporte a múltiplos papéis (ex: admin, usuário básico) para controlar permissões de acesso.

### **7. Respostas de Erro Detalhadas**
- **Tratamento de Erros**: A API retorna mensagens de erro claras e códigos HTTP adequados para facilitar o diagnóstico de problemas.
- Exemplo: `400 Bad Request` para dados inválidos, `401 Unauthorized` para falta de autenticação.

### **8. Banco de Dados**
- **Suporte a H2 e MySQL**: O banco de dados é configurável para uso com H2 (para desenvolvimento/testes) ou MySQL (para produção).
- **Spring Data JPA**: Utiliza o Spring Data JPA para interação com o banco de dados.

### **9. Segurança**
- **JWT para Autenticação e Autorização**: Todos os endpoints protegidos requerem um token JWT no cabeçalho da requisição.
- **Spring Security**: Configuração de regras de segurança para proteger rotas sensíveis.

### **10. Documentação**
- **Swagger (futuro)**: Planejado para ser integrado para uma documentação interativa dos endpoints.
- **Exemplo de uso**: Exemplos de requisições e respostas para facilitar o consumo da API.
