# Segunda Chance

## Marketplace para Compra, Venda e Doação de Itens

### Status do Projeto

**Situação atual:** Backend concluído. Frontend em desenvolvimento.

  Módulo            Progresso
  --------------- -----------
  Backend                100%
  Frontend                20%
  Projeto Geral           70%

------------------------------------------------------------------------

## Sobre

O **Segunda Chance** é um marketplace desenvolvido como projeto de
estágio da Universidade de Fortaleza (Unifor). Seu objetivo é permitir
que estudantes, professores e colaboradores comprem, vendam ou doem
itens, incentivando a economia circular e o reaproveitamento de recursos
dentro da comunidade acadêmica.

------------------------------------------------------------------------

# Tecnologias

## Backend

-   Java 21
-   Spring Boot 4.1
-   Spring Security
-   JWT
-   Spring Data JPA
-   Bean Validation
-   Specifications
-   MySQL
-   Maven

## Frontend

-   Angular 22
-   TypeScript
-   SCSS
-   Angular Router
-   Angular Signals
-   Reactive Forms
-   HttpClient

------------------------------------------------------------------------

# Backend (Concluído)

O backend encontra-se totalmente funcional e pronto para consumo pelo
frontend.

## Recursos implementados

-   Autenticação JWT
-   Cadastro e Login
-   CRUD de Usuários
-   CRUD de Categorias
-   CRUD de Anúncios
-   CRUD de Solicitações
-   CRUD de Imagens
-   DTOs
-   Bean Validation
-   Tratamento Global de Exceções
-   Paginação
-   Ordenação
-   Busca dinâmica com Specification
-   Controle de permissões
-   Regras de negócio
-   Imagem principal do anúncio
-   Endpoints REST funcionais

------------------------------------------------------------------------

# Frontend

## Estrutura

``` text
src/app
├── core
├── features
├── pages
├── shared
│   ├── components
│   │   ├── feedback
│   │   ├── layout
│   │   └── ui
│   ├── directives
│   ├── models
│   ├── pipes
│   ├── styles
│   └── utils
```

## Concluído

-   Workspace Angular configurado
-   Standalone Components
-   Arquitetura modular
-   Rotas públicas e protegidas
-   Login integrado ao backend
-   JWT
-   Auth Guard
-   HTTP Interceptor
-   Reactive Forms
-   Angular Signals
-   Base do Design System
-   Arquivos de cores e variáveis

## Em desenvolvimento

-   Identidade visual
-   Landing Page
-   Header e Footer
-   Cadastro
-   Listagem de anúncios
-   Detalhes
-   Meus anúncios
-   Solicitações
-   Perfil
-   Responsividade
-   PWA

------------------------------------------------------------------------

# Design System

A identidade visual será própria, inspirada na linguagem institucional
da Unifor, priorizando:

-   confiança
-   sustentabilidade
-   simplicidade
-   comunidade
-   economia circular

Estrutura:

``` text
shared/styles
├── _colors.scss
├── _variables.scss
├── _typography.scss
├── _spacing.scss
├── _buttons.scss
├── _forms.scss
├── _cards.scss
└── _utilities.scss
```

------------------------------------------------------------------------

# Roadmap

1.  Finalizar Design System
2.  Header
3.  Footer
4.  Landing Page
5.  Cadastro
6.  Listagem de anúncios
7.  Detalhes
8.  Criar anúncio
9.  Meus anúncios
10. Solicitações
11. Perfil
12. Responsividade
13. PWA
14. Documentação final

------------------------------------------------------------------------

# Execução

## Backend

``` bash
mvn spring-boot:run
```

Servidor:

    http://localhost:8080

## Frontend

``` bash
npm install
ng serve
```

Aplicação:

    http://localhost:4200

------------------------------------------------------------------------

# Objetivo

Entregar uma aplicação moderna, organizada e de qualidade profissional
que atenda integralmente aos requisitos do estágio e sirva como projeto
de portfólio.

------------------------------------------------------------------------

# Licença

Projeto desenvolvido para fins acadêmicos e de portfólio.
