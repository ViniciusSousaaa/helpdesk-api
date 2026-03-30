# 🛠️ Helpdesk API - Back-end

Uma API RESTful completa e segura construída com Java e Spring Boot para gerenciamento de chamados de suporte técnico (Helpdesk). Este sistema fornece toda a lógica de negócios, persistência de dados e controle de acesso baseado em papéis (RBAC) para a aplicação web.

## 🚀 Tecnologias Utilizadas

* **Java**
* **Spring Boot** (Web, Data JPA, Security)
* **PostgreSQL** (Banco de dados relacional)
* **Hibernate** (Mapeamento Objeto-Relacional)
* **Lombok** (Redução de boilerplate)
* **Auth0 java-jwt** (Geração e validação de tokens JWT)
* **Maven** (Gerenciamento de dependências)

## 🔒 Segurança e Funcionalidades

* **Autenticação JWT:** Endpoints protegidos onde apenas usuários com tokens válidos podem acessar os recursos.
* **RBAC (Role-Based Access Control):** 
* `USER`: Pode criar contas, fazer login, abrir novos chamados e visualizar o histórico.
* `ADMIN`: Possui todos os privilégios do usuário comum e a permissão exclusiva de fechar (resolver) chamados.
* **Criptografia:** Senhas salvas com hash seguro no banco de dados.
* **Relacionamento de Dados:** Usuários e seus respectivos tickets de suporte.

## ⚙️ Como executar localmente

1. Clone este repositório.
2. Certifique-se de ter o PostgreSQL rodando localmente (ou via Docker) na porta `5432` com um banco de dados chamado `helpdesk-db`.
3. Configure as credenciais do banco no arquivo `src/main/resources/application.properties`.
4. Execute a aplicação na sua IDE favorita (IntelliJ, Eclipse) ou via terminal com `mvn spring-boot:run`.
5. A API estará pronta para receber requisições em http://localhost:8080 (Utilize o Postman ou o Front-end para interagir).