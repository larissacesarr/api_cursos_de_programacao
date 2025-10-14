# 📚 API de Cursos de Programação

## 🧩 Sobre o desafio

Este projeto foi desenvolvido como parte de um desafio prático, com o objetivo de criar uma **API RESTful fictícia** para uma empresa de cursos de programação.  
O sistema foi construído seguindo os princípios do **CRUD**, permitindo o gerenciamento completo dos cursos (criação, listagem, atualização e remoção), além de controlar o status de ativação.

---

## 🚀 Funcionalidades

A API oferece as seguintes operações:

- **Criar um novo curso** (`POST /course`)
- **Listar todos os cursos** e filtrar por nome ou categoria (`GET /course`)
- **Atualizar informações de um curso** (`PUT /course/:id`)
- **Remover um curso existente** (`DELETE /course/:id`)
- **Ativar ou inativar um curso** (`PATCH /course/:id/active`)

---

## 🧱 Estrutura da Entidade

Cada curso contém as seguintes propriedades:

| Campo         | Tipo        | Descrição |
|----------------|-------------|------------|
| `id`           | `UUID`      | Identificador único do curso |
| `name`         | `string`    | Nome do curso |
| `category`     | `string`    | Categoria do curso (ex: Front end, Back end, etc.) |
| `status`       | `enum`      | Status atual do curso (`ATIVO` ou `INATIVO`) |
| `createdAt`    | `datetime`  | Data e hora da criação do curso |
| `updatedAt`    | `datetime`  | Data e hora da última atualização |

**Exemplo de resposta:**
```json
{
  "id": "8911fc6f-6171-4385-ad21-017f309140f2",
  "name": "React",
  "category": "Front end",
  "status": "ATIVO",
  "createdAt": "2025-10-07T14:22:48.018591",
  "updatedAt": "2025-10-07T18:42:50.489612"
}
```

---

## 🧭 Rotas da API

### ➕ Criar curso  
`POST /course/`

**Body exemplo:**
```json
{
  "name": "Node.js",
  "category": "Back end"
}
```

- O campo `status` é automaticamente definido como `ATIVO` na criação.
- Os campos `id`, `createdAt` e `updatedAt` são gerados automaticamente.

---

### 📋 Listar cursos  
`GET /course`

Lista todos os cursos cadastrados no banco.  
Também é possível filtrar por `name` e/ou `category`:

**Exemplo:**
```
GET /cursos?category=Front end
```

---

### ✏️ Atualizar curso  
`PUT /course/:id`

**Body exemplo:**
```json
{
  "name": "React Avançado"
}
```

- Somente `name` e/ou `category` podem ser alterados.
- O campo `status` é ignorado nessa rota.
- O `updatedAt` é atualizado automaticamente.

---

### 🔄 Alterar status (ativo/inativo)  
`PATCH /course/:id/active`

Essa rota realiza o **toggle** do status entre `ATIVO` e `INATIVO`.

### ❌ Remover curso  
`DELETE /course/:id`

Remove o curso do banco de dados.

---

## 🧩 Validações Implementadas

- Verificação da presença de `name` e `category` nas rotas `POST` e `PUT`.
- Enum para controle de status (`ATIVO`, `INATIVO`).
- Atualização automática dos campos `createdAt` e `updatedAt`.
- Tratamento de exceções personalizadas:
  - Curso não encontrado.
  - Dados inválidos no corpo da requisição.

---

## 🐳 Uso do Docker

O projeto utiliza **Docker** para subir o ambiente do banco de dados, facilitando a execução e evitando configurações manuais.

### 📁 Estrutura de Arquivos Docker

- `docker-compose.yml` → Configura os serviços (ex: banco de dados, app).

### 🏗️ Subindo o ambiente

### 1️⃣ Build da imagem
```bash
docker build -t spring-backend .
```

### 2️⃣ Subir os containers
```bash
docker-compose up -d
```

### 📦 Banco de Dados

O banco é executado em container, com persistência configurada.  
A aplicação se conecta automaticamente ao banco conforme as variáveis de ambiente.

---

## ⚙️ Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot 3+**
- **Spring Data JPA**
- **PostgreSQL**
- **Docker & Docker Compose**
- **Maven**
- **UUID** para identificação única de registros

---

## 🧩 Estrutura do Projeto

```
src/
 ├── main/
 │   ├── java/
 │   │   └── com/seuprojeto/
 │   │       ├── controller/
 │   │       ├── entity/
 │   │       ├── repository/
 │   │       ├── service/
 │   │       └── enums/
 │   └── resources/
 │       ├── application.yml
 └── test/
```

---

## ▶️ Como Executar o Projeto Localmente

### Requisitos
- Java 17+
- Maven
- Docker e Docker Compose
- PostgreSQL (rodando via Docker ou localmente)

### Passos

```bash
# Clonar o repositório
git clone https://github.com/larissacesarr/api_cursos_de_programacao.git

# Acessar o diretório do projeto
cd api_cursos_de_programacao

# Subir containers do banco
docker-compose up -d

# Compilar o projeto
mvn clean install

# Executar a aplicação
mvn spring-boot:run
```
## ▶️ Acessando a Documentação da API (Swagger)

Para visualizar e testar os endpoints da API via Swagger:

1. Certifique-se de que o projeto esteja rodando localmente.
2. Abra o navegador e acesse a seguinte URL:

   http://localhost:8080/swagger-ui/index.html

## 🧠 Observações

- As configurações do banco (usuário, senha, porta, etc.) podem ser ajustadas no arquivo application.properties.
- Certifique-se de que o Docker está em execução antes de iniciar o Spring Boot.
- As tabelas e colunas serão criadas automaticamente via JPA (Hibernate).

---

## 👩‍💻 Autora

Desenvolvido com 💜 por **[Larissa Cesar](https://github.com/larissacesarr)**  
Desenvolvedora de Software e apaixonada por desenvolvimento backend e boas práticas em APIs RESTful. 🚀
