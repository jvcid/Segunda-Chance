<<<<<<< HEAD
<<<<<<< HEAD
# SegundaChance

This project was generated using [Angular CLI](https://github.com/angular/angular-cli) version 22.1.3.

## Development server

To start a local development server, run:

```bash
ng serve
```

Once the server is running, open your browser and navigate to `http://localhost:4200/`. The application will automatically reload whenever you modify any of the source files.

## Code scaffolding

Angular CLI includes powerful code scaffolding tools. To generate a new component, run:

```bash
ng generate component component-name
```

For a complete list of available schematics (such as `components`, `directives`, or `pipes`), run:

```bash
ng generate --help
```

## Building

To build the project run:

```bash
ng build
```

This will compile your project and store the build artifacts in the `dist/` directory. By default, the production build optimizes your application for performance and speed.

## Running unit tests

To execute unit tests with the [Vitest](https://vitest.dev/) test runner, use the following command:

```bash
ng test
```

## Running end-to-end tests

For end-to-end (e2e) testing, run:

```bash
ng e2e
```

Angular CLI does not come with an end-to-end testing framework by default. You can choose one that suits your needs.

## Additional Resources

For more information on using the Angular CLI, including detailed command references, visit the [Angular CLI Overview and Command Reference](https://angular.dev/tools/cli) page.
=======
# Segunda Chance

## Sobre o Projeto

O Segunda Chance é uma plataforma web desenvolvida para conectar pessoas interessadas em comprar, vender e doar produtos usados, incentivando a reutilização de itens e promovendo o consumo consciente.

Este projeto está sendo desenvolvido como parte da minha formação em Ciência da Computação na Universidade de Fortaleza (UNIFOR), mas também possui um objetivo maior: servir como projeto de portfólio para demonstrar conhecimentos em desenvolvimento Full Stack e arquitetura de software durante processos seletivos para estágio.

O desenvolvimento está sendo realizado seguindo práticas utilizadas em projetos profissionais, priorizando organização do código, separação de responsabilidades, regras de negócio, autenticação segura e escalabilidade.

---

# Objetivos

O projeto busca implementar uma aplicação completa contendo:

- autenticação segura utilizando JWT;
- gerenciamento de usuários;
- cadastro de categorias;
- anúncios de compra, venda e doação;
- sistema de solicitações entre usuários;
- gerenciamento de imagens;
- frontend responsivo em Angular;
- documentação da API;
- testes automatizados;
- containerização utilizando Docker.
=======
# Segunda Chance

Sistema web desenvolvido como projeto para processo seletivo de estágio, com foco em arquitetura moderna, boas práticas de desenvolvimento e experiência do usuário.

O objetivo da plataforma é facilitar a compra, venda e doação de produtos entre usuários por meio de anúncios, solicitações e autenticação segura.

---

## Desenvolvedor

**João Victor Martins Cid**

Projeto desenvolvido individualmente.

---

# Objetivo

O Segunda Chance busca incentivar a reutilização de produtos, permitindo que usuários anunciem itens para venda ou doação de maneira simples, organizada e segura.

Além de atender aos requisitos do processo seletivo, o projeto foi planejado para servir como peça de portfólio, adotando padrões utilizados em aplicações profissionais.
>>>>>>> 6b2f2366fa45ff65055fd1b7dec9f3b4a1d65262

---

# Tecnologias

## Backend

<<<<<<< HEAD
- Java 21
- Spring Boot 4.1
- Spring Security
- JWT
- Spring Data JPA
- Hibernate
- MySQL
=======
- Java 25
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate
- PostgreSQL
>>>>>>> 6b2f2366fa45ff65055fd1b7dec9f3b4a1d65262
- Maven

## Frontend

<<<<<<< HEAD
- Angular
- TypeScript
- HTML
- CSS

---

# Arquitetura

O backend foi desenvolvido utilizando arquitetura em camadas.

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

- DTO (Request/Response)
- Specification
- Exception Handler
- JWT Authentication
- Bean Validation

---

# Funcionalidades Implementadas

## Autenticação

- Cadastro de usuários
- Login utilizando JWT
- Criptografia de senha com BCrypt
- Controle de autenticação via Bearer Token

---

## Usuários

- Cadastro
- Consulta
- Atualização
- Exclusão

---

## Categorias

- CRUD completo

---

## Anúncios

- CRUD completo
- Paginação
- Ordenação
- Pesquisa
- Filtros dinâmicos
- Status do anúncio
- Associação com categorias
- Associação com proprietário

---

## Solicitações

- Criar solicitação
- Atualizar solicitação
- Excluir solicitação
- Aprovar solicitação
- Rejeitar solicitação

### Regras implementadas

- Um usuário não pode solicitar o próprio anúncio.
- Um usuário não pode solicitar duas vezes o mesmo anúncio.
- Apenas solicitações pendentes podem ser alteradas.
- Apenas o proprietário do anúncio pode aprovar ou rejeitar solicitações.
- Ao aprovar uma solicitação:
    - o anúncio torna-se reservado;
    - todas as demais solicitações são rejeitadas automaticamente.

---

## Imagens

- Cadastro de imagens
- Associação com anúncios
- Definição automática da imagem principal
=======
- Angular 20
- TypeScript
- Angular Router
- Angular Signals
- SCSS
- Angular HttpClient

---

# Estrutura do Projeto

```
Segunda-Chance-main
│
├── backend/
│   ├── src/
│   ├── pom.xml
│   └── segunda_chance.sql
│
├── frontend/
│   └── Estrutura inicial Angular
│
├── docs/
│
└── README.md
```

---

# Status do Projeto

## Backend

Status: Concluído

Implementações realizadas:

- Autenticação JWT
- Cadastro e login de usuários
- CRUD completo de usuários
- CRUD completo de categorias
- CRUD completo de anúncios
- CRUD completo de solicitações
- CRUD completo de imagens dos anúncios
- Controle de autorização baseado em proprietário
- Tratamento global de exceções
- Validações de regras de negócio
- Paginação
- Ordenação
- Filtros utilizando Spring Specification
- Imagem principal do anúncio
- Testes manuais completos utilizando Postman

---

## Frontend

Status: Em desenvolvimento

Estrutura inicial criada.

Atualmente o projeto possui a organização base para implementação das telas e da arquitetura da aplicação.

Estrutura planejada:

```
frontend
│
└── src
    └── app
        ├── core
        ├── shared
        ├── features
        ├── layouts
        ├── guards
        ├── interceptors
        ├── models
        ├── services
        └── pages
```
>>>>>>> 6b2f2366fa45ff65055fd1b7dec9f3b4a1d65262

---

# Próximas Etapas

<<<<<<< HEAD
O projeto continua em desenvolvimento.

Os próximos objetivos incluem:

- proteção completa do gerenciamento de imagens;
- endpoint para anúncios do usuário autenticado;
- endpoint para solicitações enviadas;
- endpoint para solicitações recebidas;
- documentação utilizando Swagger/OpenAPI;
- testes unitários com JUnit e Mockito;
- Docker e Docker Compose;
- upload real de imagens;
- logs estruturados;
- melhoria da cobertura de validações.

---

# Frontend

O frontend será desenvolvido em Angular.

As funcionalidades previstas incluem:

- Login
- Cadastro
- Página inicial
- Pesquisa de anúncios
- Página do anúncio
- Perfil do usuário
- Dashboard
- Gerenciamento de anúncios
- Gerenciamento de solicitações
- Upload de imagens

---

# Objetivo do Projeto

Este projeto representa minha principal iniciativa de portfólio durante a graduação.

Além de atender aos requisitos acadêmicos, ele está sendo desenvolvido com foco em boas práticas de engenharia de software, organização de código e utilização de tecnologias amplamente empregadas no mercado, servindo como demonstração prática das minhas habilidades para oportunidades de estágio e desenvolvimento profissional.

---

# Desenvolvedor

**João Victor Martins Cid**

Estudante de Ciência da Computação

Universidade de Fortaleza (UNIFOR)

---

# Status

O backend encontra-se em fase avançada de desenvolvimento, com aproximadamente **95% das funcionalidades principais concluídas**.

O frontend será iniciado após a finalização dos últimos ajustes do backend.
>>>>>>> a0e8ea50291200481d9acb1886e61341146ed221
=======
## Fase 1

- Integração Angular com API REST
- Sistema de autenticação
- AuthGuard
- Interceptor JWT
- Serviços HTTP

## Fase 2

- Landing Page
- Tela de Login
- Tela de Cadastro
- Listagem de anúncios
- Detalhes do anúncio

## Fase 3

- Criar anúncio
- Editar anúncio
- Perfil
- Meus anúncios
- Solicitações enviadas
- Solicitações recebidas

## Fase 4

- Responsividade completa
- Progressive Web App (PWA)
- Ajustes visuais
- Documentação final

---

# Arquitetura de Rotas

## Públicas

```
/
├── Landing Page

/login

/register

/ads

/ads/:id

/about

/404
```

## Protegidas

```
/profile

/profile/edit

/my-ads

/ads/create

/ads/edit/:id

/requests

/requests/sent

/requests/received
```

---

# Arquitetura da Aplicação

O projeto segue separação por camadas.

## Backend

```
Controller

↓

Service

↓

Repository

↓

Database
```

## Frontend

```
Pages

↓

Components

↓

Services

↓

HTTP Client

↓

Backend REST
```

---

# Diferenciais Implementados

- JWT Stateless Authentication
- Autorização baseada em proprietário do recurso
- DTOs para entrada e saída
- Regras de negócio isoladas na camada de serviço
- Filtros dinâmicos com Specification
- Paginação e ordenação
- Tratamento global de exceções
- Estrutura preparada para evolução da aplicação

---

# Próximos Diferenciais

- Progressive Web App
- Deploy
- Melhorias de UX/UI
- Dashboard do usuário
- Upload de imagens
- Busca avançada
- Tema claro e escuro

---

# Estado Atual

Backend:
- Concluído
- Testado
- Preparado para integração

Frontend:
- Estrutura inicial criada
- Arquitetura definida
- Desenvolvimento em andamento

---

## Licença

Projeto desenvolvido exclusivamente para fins acadêmicos, avaliação técnica e composição de portfólio.
>>>>>>> 6b2f2366fa45ff65055fd1b7dec9f3b4a1d65262
