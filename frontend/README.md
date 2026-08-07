# Segunda Chance

Uma plataforma web desenvolvida para a Universidade de Fortaleza (UNIFOR) com o objetivo de incentivar a economia circular por meio da compra, venda e doação de objetos usados entre estudantes, professores e colaboradores.

O projeto foi concebido para promover sustentabilidade, reutilização de recursos e fortalecimento da comunidade acadêmica, oferecendo um ambiente seguro, organizado e intuitivo para negociação de itens.

---

# Sumário

- Sobre o Projeto
- Objetivos
- Funcionalidades
- Tecnologias
- Arquitetura
- Estrutura do Projeto
- Identidade Visual
- Backend
- Frontend
- Banco de Dados
- Segurança
- Como Executar
- Próximas Etapas
- Desenvolvedor

---

# Sobre o Projeto

O Segunda Chance nasceu da ideia de que inúmeros objetos deixam de ser utilizados mesmo permanecendo em perfeito estado de conservação.

Livros, materiais acadêmicos, eletrônicos, móveis, utensílios e diversos outros itens frequentemente acabam esquecidos ou descartados quando poderiam continuar sendo úteis para outra pessoa.

A plataforma conecta pessoas pertencentes à comunidade acadêmica da Universidade de Fortaleza para facilitar esse processo de reutilização, criando um ambiente exclusivo para anúncios de compra, venda e doação.

Diferentemente de marketplaces tradicionais, o foco do projeto não está apenas na comercialização de produtos, mas principalmente em incentivar:

- reutilização;
- economia circular;
- sustentabilidade;
- colaboração;
- consumo consciente;
- fortalecimento da comunidade universitária.

---

# Objetivos

O sistema foi planejado para fornecer uma solução completa contendo:

- autenticação segura utilizando JWT;
- gerenciamento de usuários;
- gerenciamento de categorias;
- anúncios de compra;
- anúncios de venda;
- anúncios de doação;
- sistema de solicitações entre usuários;
- gerenciamento de imagens;
- frontend responsivo;
- documentação da API;
- testes automatizados;
- futura containerização com Docker.

---

# Funcionalidades

## Autenticação

- Cadastro de usuários
- Login utilizando JWT
- Logout
- BCrypt Password Encoder
- Auth Guard no Angular
- Interceptor para envio automático do Bearer Token
- Rotas protegidas

---

## Usuários

- Cadastro
- Consulta
- Atualização
- Exclusão
- Perfil

---

## Categorias

- CRUD completo

---

## Anúncios

- Cadastro
- Consulta
- Atualização
- Exclusão
- Associação com categorias
- Associação com proprietário
- Busca
- Filtros
- Paginação

---

## Solicitações

- Criar solicitação
- Atualizar solicitação
- Cancelar solicitação
- Aprovar
- Rejeitar

### Regras de negócio

I. Um usuário não pode solicitar seu próprio anúncio.

II. Um usuário não pode solicitar duas vezes o mesmo anúncio.

III. Apenas solicitações pendentes podem ser modificadas.

IV. Apenas o proprietário do anúncio pode aprovar ou rejeitar solicitações.

V. Ao aprovar uma solicitação:

- o anúncio torna-se reservado;
- todas as demais solicitações são automaticamente rejeitadas.

---

## Imagens

O projeto prevê suporte para:

- múltiplas imagens por anúncio;
- imagem principal;
- upload de arquivos;
- armazenamento seguro.

---

# Tecnologias

## Backend

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- JWT
- Bean Validation
- Maven
- MySQL

---

## Frontend

- Angular
- TypeScript
- HTML5
- SCSS
- Angular Router
- Reactive Forms
- HttpClient
- Signals

---

## Ferramentas

- Git
- GitHub
- Postman
- VS Code
- IntelliJ IDEA

---

# Arquitetura

O backend segue arquitetura em camadas.

```
Controller
        │
Service
        │
Repository
        │
Database
```

Além disso, foram adotados os padrões:

- DTO Request
- DTO Response
- Services
- Repository Pattern
- Exception Handler
- Bean Validation
- JWT Authentication
- Dependency Injection

---

# Estrutura do Projeto

```
Segunda-Chance
│
├── backend
│
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   ├── dto
│   ├── security
│   ├── config
│   └── exception
│
├── frontend
│
│   ├── core
│   ├── shared
│   ├── features
│   │
│   ├── auth
│   ├── ads
│   ├── requests
│   ├── profile
│   ├── about
│   └── landing
│
└── README.md
```

---

# Identidade Visual

Toda a identidade visual foi desenvolvida especificamente para o projeto.

O conceito central da marca é:

> Dar uma nova vida aos objetos.

A identidade busca transmitir:

- confiança;
- colaboração;
- organização;
- simplicidade;
- sustentabilidade;
- inovação;
- comunidade.

O símbolo da marca representa:

- um "S" abstrato;
- continuidade;
- economia circular;
- troca entre pessoas;
- renovação.

A paleta utiliza tons de azul inspirados na identidade institucional da Universidade de Fortaleza, porém com linguagem própria.

Também foram desenvolvidos:

- logotipo principal;
- logotipo horizontal;
- símbolo;
- favicon;
- ícone PWA;
- avatar;
- versões para fundo claro e escuro;
- aplicações institucionais.

---

# Backend

O backend encontra-se praticamente concluído.

Atualmente possui:

- autenticação JWT;
- Spring Security;
- DTOs;
- Services;
- Controllers;
- validações;
- CRUDs;
- arquitetura organizada.

---

# Frontend

O frontend está sendo desenvolvido utilizando Angular.

## Já implementado

### Sistema

- estrutura completa do projeto;
- roteamento;
- layout compartilhado;
- AuthGuard;
- interceptor HTTP;
- autenticação integrada ao backend.

### Interface

- identidade visual completa;
- design system;
- Landing Page;
- Login;
- Página Sobre;
- Header institucional;
- componentes reutilizáveis.

### Experiência do usuário

- layout responsivo;
- formulários reativos;
- feedback visual;
- organização por features.

---

# Banco de Dados

O sistema utiliza banco relacional.

Principais entidades:

- User
- Category
- Advertisement
- Request
- Image

Relacionamentos foram planejados para permitir expansão futura mantendo baixo acoplamento.

---

# Segurança

O projeto utiliza:

- Spring Security
- JWT
- BCrypt
- Rotas protegidas
- AuthGuard
- Interceptor HTTP
- Validação de entrada
- Bean Validation

---

# Como Executar

## Backend

```bash
cd backend

mvn spring-boot:run
```

Servidor:

```
http://localhost:8080
```

---

## Frontend

```bash
cd frontend

npm install

ng serve
```

Aplicação:

```
http://localhost:4200
```

---

# Próximas Etapas

O projeto encontra-se em fase final de desenvolvimento.

As próximas implementações incluem:

- tela de cadastro;
- listagem completa de anúncios;
- detalhes do anúncio;
- criação de anúncios;
- edição de anúncios;
- gerenciamento de solicitações;
- perfil do usuário;
- upload real de imagens;
- integração completa frontend/backend;
- refinamento da interface;
- documentação da API com Swagger;
- Docker.

---

# Objetivos Acadêmicos

Este projeto foi desenvolvido para atender aos requisitos da disciplina da Universidade de Fortaleza, porém sua arquitetura e organização foram planejadas seguindo padrões utilizados em aplicações profissionais.

Além do contexto acadêmico, o Segunda Chance constitui o principal projeto de portfólio do desenvolvedor.

---

# Desenvolvedor

**João Victor Martins Cid**

Graduando em Ciência da Computação

Universidade de Fortaleza (UNIFOR)

GitHub:
https://github.com/jvcid

---

# Status

**Backend:** aproximadamente 95% concluído.

**Frontend:** em desenvolvimento ativo.

O projeto encontra-se na fase de integração entre frontend e backend, com identidade visual definida, autenticação implementada e estrutura principal da aplicação consolidada.
