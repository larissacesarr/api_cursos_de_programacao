# 📚 API de Cursos de Programação

## 🧩 Sobre o desafio

Este projeto foi desenvolvido como parte de um desafio prático, com o objetivo de criar uma **API RESTful fictícia** para uma empresa de cursos de programação.  
O sistema foi construído seguindo os princípios do **CRUD**, permitindo o gerenciamento completo dos cursos (criação, listagem, atualização e remoção), além de controlar o status de ativação.

---

## 🚀 Funcionalidades

A API oferece as seguintes operações:

- **Criar um novo curso** (`POST /cursos`)
- **Listar todos os cursos** e filtrar por nome ou categoria (`GET /cursos`)
- **Atualizar informações de um curso** (`PUT /cursos/:id`)
- **Remover um curso existente** (`DELETE /cursos/:id`)
- **Ativar ou inativar um curso** (`PATCH /cursos/:id/active`)

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
`POST /cursos`

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
`GET /cursos`

Lista todos os cursos cadastrados no banco.  
Também é possível filtrar por `name` e/ou `category`:

**Exemplo:**
```
GET /cursos?category=Front end
```

---

### ✏️ Atualizar curso  
`PUT /cursos/:id`

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
`PATCH /cursos/:id/active`

Essa rota realiza o **toggle** do status entre `ATIVO` e `INATIVO`.

**Resposta exemplo:**
```json
{
  "message": "Status do curso atualizado para INATIVO"
}
```

---

### ❌ Remover curso  
`DELETE /cursos/:id`

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

- `Dockerfile` → Define o ambiente e dependências da aplicação.
- `docker-compose.yml` → (caso tenha sido utilizado) Configura os serviços (ex: banco de dados, app).

### 🏗️ Subindo o ambiente

```bash
# Build e inicialização do container
docker-compose up --build
```

### 📦 Banco de Dados

O banco é executado em container, com persistência configurada.  
A aplicação se conecta automaticamente ao banco conforme as variáveis de ambiente.

---

## ⚙️ Tecnologias Utilizadas

- **Node.js**
- **Express**
- **Docker**
- **PostgreSQL**
- **Sequelize / Prisma**
- **UUID**
- **Nodemon**

---

## 🧩 Estrutura do Projeto

```
api_cursos_de_programacao/
│
├── src/
│   ├── controllers/       # Lógica das rotas
│   ├── models/            # Modelos e entidades
│   ├── routes/            # Definição das rotas da API
│   ├── enums/             # Enum de status (ATIVO / INATIVO)
│   └── database/          # Configuração e conexão com o banco
│
├── Dockerfile
├── package.json
├── .env
└── README.md
```

---

## ▶️ Como Executar o Projeto Localmente

### Requisitos
- Node.js 18+
- Docker e Docker Compose

### Passos

```bash
# Clonar o repositório
git clone https://github.com/larissacesarr/api_cursos_de_programacao.git

# Acessar o diretório do projeto
cd api_cursos_de_programacao

# Instalar dependências
npm install

# Subir containers do banco
docker-compose up -d

# Rodar o servidor em modo desenvolvimento
npm run dev
```

A API estará disponível em:
```
http://localhost:3333
```

---

## 💡 Possíveis Melhorias Futuras

- Adicionar autenticação JWT.
- Implementar testes automatizados (Jest / Supertest).
- Adicionar paginação e ordenação na listagem.
- Documentar rotas com Swagger.
- Criar seeders e migrations automatizadas.

---

## 👩‍💻 Autora

Desenvolvido com 💜 por **[Larissa Cesar](https://github.com/larissacesarr)**  
Estudante de Engenharia de Software e apaixonada por desenvolvimento backend e boas práticas em APIs RESTful. 🚀
