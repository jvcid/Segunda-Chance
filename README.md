# Segunda Chance

Marketplace acadêmico de economia circular desenvolvido para a comunidade da Universidade de Fortaleza (Unifor).

O **Segunda Chance** permite que estudantes, professores e colaboradores publiquem itens para **venda ou doação**, encontrem materiais usados dentro da comunidade acadêmica e deem uma nova utilidade a objetos que ainda podem ser reaproveitados.

**Autor:** João Victor Martins Cid

---

## Aplicação em Produção

| Camada | Serviço | URL |
|---|---|---|
| Frontend | Vercel | https://segunda-chance-flax.vercel.app |
| Backend | Render | https://segunda-chance-api.onrender.com |
| Banco de dados | TiDB Cloud | Instância `segunda-chance-db` / database `segundachance` |

Repositório:

```text
https://github.com/jvcid/Segunda-Chance
```

> A instância gratuita do Render pode entrar em modo de inatividade. A primeira requisição após um período sem uso pode levar alguns segundos enquanto o serviço é inicializado novamente.

---

## Sumário

- [1. Sobre o Projeto](#1-sobre-o-projeto)
- [2. Arquitetura](#2-arquitetura)
- [3. Tecnologias](#3-tecnologias)
- [4. Funcionalidades](#4-funcionalidades)
- [5. Backend](#5-backend)
- [6. Frontend](#6-frontend)
- [7. Banco de Dados](#7-banco-de-dados)
- [8. Autenticação e Segurança](#8-autenticação-e-segurança)
- [9. API REST](#9-api-rest)
- [10. Execução Local](#10-execução-local)
- [11. Docker](#11-docker)
- [12. Deploy](#12-deploy)
- [13. Testes](#13-testes)
- [14. PWA e Responsividade](#14-pwa-e-responsividade)
- [15. Atendimento ao Edital](#15-atendimento-ao-edital)
- [16. Diário de Bordo da IA](#16-diário-de-bordo-da-ia)
- [17. Estrutura do Repositório](#17-estrutura-do-repositório)
- [18. Considerações Finais](#18-considerações-finais)

---

# 1. Sobre o Projeto

O **Segunda Chance** foi desenvolvido como desafio técnico para o processo seletivo do Laboratório Vortex (Unifor).

A proposta é oferecer um marketplace de economia circular dentro do ambiente universitário, permitindo que a comunidade acadêmica disponibilize itens usados para:

- venda;
- doação;
- reutilização;
- circulação dentro do campus.

Entre os itens que podem ser anunciados estão livros, materiais acadêmicos, eletrônicos, acessórios, móveis, roupas, instrumentos musicais e outros objetos úteis.

O sistema foi concebido como uma aplicação completa, composta por:

```text
Frontend
+
API REST
+
Autenticação
+
Persistência
+
Banco em nuvem
+
Deploy
```

---

# 2. Arquitetura

A solução final utiliza serviços independentes para cada camada.

```text
┌──────────────────────────────┐
│           USUÁRIO            │
│          Navegador           │
└──────────────┬───────────────┘
               |
               | HTTPS
               v
┌──────────────────────────────┐
│           VERCEL             │
│      Frontend Angular        │
└──────────────┬───────────────┘
               |
               | REST / JSON / JWT
               v
┌──────────────────────────────┐
│           RENDER             │
│   API Java + Spring Boot     │
└──────────────┬───────────────┘
               |
               | JDBC + TLS
               v
┌──────────────────────────────┐
│        TiDB CLOUD            │
│   Banco compatível MySQL     │
└──────────────────────────────┘
```

O frontend não acessa diretamente o banco de dados.

Toda comunicação é realizada através da API:

```text
Angular -> Spring Boot -> TiDB
```

Essa separação permite desenvolver, testar e publicar cada camada de forma independente.

---

# 3. Tecnologias

## Backend

- Java 21
- Spring Boot 4.1
- Spring Web MVC
- Spring Security
- Spring Data JPA
- Hibernate
- Bean Validation
- JWT
- Maven
- MySQL Connector/J
- HikariCP
- Docker

## Frontend

- Angular
- TypeScript
- HTML
- SCSS
- Angular Router
- Angular HttpClient
- Angular Signals
- formulários e validações
- arquitetura baseada em componentes e serviços

## Banco de Dados

- MySQL no ambiente local
- TiDB Cloud em produção
- MySQL Workbench para inspeção e validação

## Infraestrutura

- Git / GitHub
- Docker / Docker Compose
- Render
- Vercel
- TiDB Cloud
- Postman

---

# 4. Funcionalidades

O sistema oferece suporte às principais operações esperadas para o marketplace.

## Usuários

- cadastro;
- login;
- autenticação JWT;
- sessão autenticada;
- perfil;
- logout.

## Anúncios

- criação;
- listagem;
- pesquisa;
- filtro por categoria;
- filtro por tipo;
- filtro por estado;
- paginação;
- detalhes;
- gerenciamento dos próprios anúncios;
- atualização;
- exclusão;
- publicação para venda;
- publicação para doação;
- preço para anúncios de venda;
- imagem principal.

## Categorias

As categorias são obtidas dinamicamente através da API e persistidas no banco.

Entre as categorias utilizadas estão:

1. Acessórios
2. Computação
3. Eletrônicos
4. Esportes
5. Instrumentos musicais
6. Livros
7. Materiais acadêmicos
8. Móveis
9. Roupas
10. Outros

## Solicitações

O sistema possui fluxo de solicitações relacionado ao interesse dos usuários nos itens anunciados.

## Progressive Web App (PWA)

O frontend também funciona como uma **Progressive Web App**, com suporte a:

- manifesto web (`manifest.webmanifest`);
- Service Worker do Angular;
- execução em modo `standalone`;
- instalação da aplicação em dispositivos compatíveis;
- ícones PWA em múltiplas resoluções;
- ícones com suporte `maskable`;
- cache do shell principal da aplicação;
- cache de imagens e assets estáticos;
- cache de anúncios públicos previamente carregados;
- cache das categorias públicas;
- estratégia `freshness`, priorizando dados atualizados da API;
- fallback para dados públicos armazenados em cache quando a rede estiver indisponível ou lenta;
- atualização automática dos arquivos versionados pelo Service Worker;
- navegação SPA compatível com o deploy na Vercel.

## Experiência Mobile

A interface possui comportamento específico para dispositivos móveis e tablets, incluindo:

- layout responsivo;
- reorganização de grids para uma coluna em telas menores;
- formulários adaptáveis;
- cards responsivos;
- filtros adaptáveis;
- paginação responsiva;
- menu de navegação mobile;
- botão de menu com abertura e fechamento controlados por Angular Signals;
- navegação mobile diferente para usuários autenticados e não autenticados;
- acesso mobile a Início, Explorar, Meus anúncios, Solicitações, Perfil e publicação de anúncios;
- acesso mobile a Login, Cadastro e Sobre para usuários não autenticados;
- fechamento automático do menu após a navegação;
- botão de menu com área de toque adequada;
- adaptação dos botões do cabeçalho em telas pequenas;
- preservação das funcionalidades de cadastro, login, exploração e publicação em dispositivos móveis.

---

# 5. Backend

O backend é uma API REST desenvolvida com Java e Spring Boot.

Sua arquitetura segue a separação:

```text
Controller
   |
   v
Service
   |
   v
Repository
   |
   v
JPA / Hibernate
   |
   v
Banco de Dados
```

Também são utilizados DTOs para separar os contratos HTTP das entidades persistidas:

```text
Request DTO
   |
   v
Controller
   |
   v
Service
   |
   v
Entity / Repository
   |
   v
Response DTO
```

Essa organização melhora:

- separação de responsabilidades;
- manutenção;
- legibilidade;
- validação;
- segurança;
- evolução futura da API.

## Configuração de produção

O backend recebe as configurações sensíveis através de variáveis de ambiente.

Exemplo de `application.yml`:

```yaml
spring:
  datasource:
    url: ${SPRING_DATASOURCE_URL}
    username: ${SPRING_DATASOURCE_USERNAME}
    password: ${SPRING_DATASOURCE_PASSWORD}
    driver-class-name: com.mysql.cj.jdbc.Driver

  jpa:
    open-in-view: false
    hibernate:
      ddl-auto: ${SPRING_JPA_HIBERNATE_DDL_AUTO:update}
    show-sql: false
    properties:
      hibernate:
        format_sql: true

server:
  port: ${PORT:8080}

jwt:
  secret: ${JWT_SECRET}
  expiration: 86400000
```

Variáveis utilizadas:

```text
SPRING_DATASOURCE_URL
SPRING_DATASOURCE_USERNAME
SPRING_DATASOURCE_PASSWORD
SPRING_JPA_HIBERNATE_DDL_AUTO
JWT_SECRET
```

Credenciais reais e segredos não devem ser versionados no repositório.

---

# 6. Frontend

O frontend foi desenvolvido em Angular e é responsável pela interface, navegação e comunicação com a API.

Entre os recursos utilizados estão:

- componentes standalone;
- templates HTML;
- SCSS;
- services;
- Angular Router;
- HttpClient;
- Signals;
- formulários;
- validações;
- estados de carregamento;
- estados de erro;
- respostas vazias;
- paginação;
- filtros;
- Angular Service Worker;
- manifesto PWA;
- cache de aplicação e dados públicos;
- layout responsivo;
- navegação mobile;
- deploy SPA com fallback de rotas na Vercel.

Organização conceitual:

```text
src/app/
├── core/
├── features/
│   ├── auth/
│   ├── ads/
│   ├── requests/
│   └── profile/
└── shared/
    └── components/
```

A comunicação segue:

```text
Component
   |
   v
Service
   |
   v
HttpClient
   |
   v
API REST
```

A URL da API deve ser centralizada na configuração de ambiente, evitando dependência de `localhost` na versão publicada.

Produção:

```text
https://segunda-chance-api.onrender.com
```

---

# 7. Banco de Dados

## Desenvolvimento

Durante o desenvolvimento foi utilizado MySQL local.

## Produção

A persistência de produção utiliza **TiDB Cloud**, compatível com o protocolo MySQL.

Instância:

```text
segunda-chance-db
```

Database:

```text
segundachance
```

A conexão pública utiliza TLS.

Durante o deploy, o banco foi criado e validado diretamente pelo cliente MySQL:

```sql
CREATE DATABASE IF NOT EXISTS segundachance;

SHOW DATABASES;

USE segundachance;

SELECT DATABASE();
```

O resultado confirmou que o backend estava utilizando o database correto.

A persistência também foi validada através do MySQL Workbench.

Exemplo:

```sql
SELECT user_id, name, email, role_id
FROM users
ORDER BY user_id DESC;
```

---

# 8. Autenticação e Segurança

A autenticação utiliza Spring Security e JSON Web Token.

Fluxo:

```text
Usuário
   |
   v
Login
   |
   v
POST /api/auth/login
   |
   v
Spring Security
   |
   v
JWT
   |
   v
Frontend
```

Requisições protegidas enviam:

```http
Authorization: Bearer <token>
```

O backend possui componentes responsáveis por:

```text
SecurityConfig
JwtService
JwtAuthenticationFilter
CustomUserDetailsService
```

A aplicação utiliza:

- JWT;
- autenticação stateless;
- rotas públicas e protegidas;
- Bean Validation;
- DTOs;
- variáveis de ambiente;
- TLS na conexão com o banco;
- controle de propriedade nas operações protegidas.

---

# 9. API REST

A API envia e recebe dados no formato JSON.

Entre os recursos utilizados estão:

```text
/api/auth
/api/anuncios
/api/categories
/api/imagens-anuncio
```

Outros recursos da aplicação incluem usuários e solicitações.

## Anúncios

A API permite operações de criação, listagem, atualização e remoção.

A consulta suporta filtros como:

```text
titulo
categoryId
tipo
status
page
size
```

Exemplo de resposta de anúncio:

```json
{
  "id": 3,
  "titulo": "Notebook",
  "descricao": "Notebook usado",
  "tipo": "VENDA",
  "preco": 2300.00,
  "status": "DISPONIVEL",
  "categoryId": 1,
  "categoryName": "Eletrônicos",
  "userId": 3,
  "userName": "Usuário",
  "imagemPrincipalUrl": null
}
```

---

# 10. Execução Local

## Pré-requisitos

### Backend

- Java 21
- Maven
- MySQL ou banco compatível

### Frontend

- Node.js
- npm
- Angular CLI

## Clonar o projeto

```bash
git clone https://github.com/jvcid/Segunda-Chance.git
cd Segunda-Chance
```

## Backend

Entre na pasta:

```bash
cd backend
```

Configure as variáveis de ambiente necessárias:

```text
SPRING_DATASOURCE_URL
SPRING_DATASOURCE_USERNAME
SPRING_DATASOURCE_PASSWORD
SPRING_JPA_HIBERNATE_DDL_AUTO
JWT_SECRET
```

Execute:

```bash
mvn spring-boot:run
```

A API utilizará, por padrão:

```text
http://localhost:8080
```

## Frontend

Entre na pasta do frontend:

```bash
cd frontend
```

Instale as dependências:

```bash
npm install
```

Execute:

```bash
ng serve
```

ou:

```bash
npm start
```

A aplicação normalmente estará disponível em:

```text
http://localhost:4200
```

## Build do backend

```bash
mvn clean package
```

ou:

```bash
mvn clean package -DskipTests
```

## Build do frontend

```bash
ng build
```

---

# 11. Docker

O backend e a infraestrutura local também foram preparados para utilização com Docker.

Com Docker Desktop ativo:

```bash
docker compose up -d
```

Verificar:

```bash
docker compose ps
```

Parar:

```bash
docker compose down
```

Durante o desenvolvimento, também foi possível iniciar apenas banco e Adminer:

```bash
docker compose up -d db adminer
```

O Docker foi utilizado para tornar o ambiente mais reproduzível e aproximar a execução local do processo de deploy.

---

# 12. Deploy

## Frontend

O frontend foi publicado na Vercel:

```text
https://segunda-chance-flax.vercel.app
```

## Backend

A API foi publicada no Render:

```text
https://segunda-chance-api.onrender.com
```

O backend utiliza Docker e Maven durante o build.

Fluxo:

```text
Commit
  |
  v
GitHub
  |
  v
Render
  |
  v
Docker Build
  |
  v
Maven
  |
  v
Spring Boot
```

O processo final foi confirmado pela plataforma com:

```text
Your service is live
```

## Banco

O banco remoto utiliza TiDB Cloud.

Arquitetura final:

```text
Vercel
   |
   v
Render
   |
   v
TiDB Cloud
```

---

# 13. Testes

Os testes ocorreram em diferentes níveis.

## Backend isolado

Foram realizadas requisições através do Postman para validar:

- cadastro;
- login;
- JWT;
- CRUD;
- validações;
- erros;
- permissões.

Exemplos de respostas verificadas:

```text
200 OK
201 Created
204 No Content
400 Bad Request
401 Unauthorized
403 Forbidden
404 Not Found
```

## Integração com banco

A persistência foi validada diretamente no banco.

## Integração completa

Foram testados fluxos utilizando:

```text
Frontend Vercel
   |
   v
Backend Render
   |
   v
TiDB Cloud
```

Entre os fluxos verificados estão:

- criação de usuário;
- persistência;
- login;
- autenticação;
- categorias;
- anúncios;
- filtros;
- paginação;
- venda;
- doação;
- imagens;
- solicitações.

## Diagnóstico

Durante o desenvolvimento também foram utilizadas:

- aba Network;
- XHR;
- Console;
- logs do Spring Boot;
- logs do Render;
- MySQL Workbench;
- MySQL CLI.

---

# 14. PWA e Responsividade

O frontend possui implementação completa de **Progressive Web App (PWA)** e comportamento responsivo para desktop, tablets e dispositivos móveis.

A implementação foi verificada tanto nos arquivos-fonte quanto no build de produção presente no frontend.

## 14.1 Manifesto da aplicação

O projeto possui:

```text
public/manifest.webmanifest
```

O manifesto define a identidade e o comportamento da aplicação instalada:

```json
{
  "name": "Segunda Chance",
  "short_name": "2ª Chance",
  "lang": "pt-BR",
  "start_url": "/welcome",
  "scope": "/",
  "display": "standalone",
  "orientation": "any",
  "background_color": "#f4faff",
  "theme_color": "#0f7dbb"
}
```

O manifesto é referenciado diretamente pelo `index.html`:

```html
<link rel="manifest" href="manifest.webmanifest" />
```

Também são definidos:

```html
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="theme-color" content="#0f7dbb" />
```

Isso permite que a aplicação tenha identidade própria quando executada em dispositivos compatíveis e mantenha comportamento adequado em telas móveis.

## 14.2 Ícones da PWA

O projeto possui ícones em diferentes resoluções:

```text
72x72
96x96
128x128
144x144
152x152
192x192
384x384
512x512
```

Os ícones são declarados no manifesto como:

```json
"purpose": "any maskable"
```

Dessa forma, podem ser utilizados tanto como ícones convencionais quanto em interfaces que aplicam máscaras específicas ao ícone instalado.

Os tamanhos `192x192` e `512x512`, utilizados pelos mecanismos de instalação de PWA, estão presentes no projeto.

## 14.3 Service Worker

O frontend utiliza:

```text
@angular/service-worker
```

A dependência está integrada ao Angular e o Service Worker é registrado através da configuração principal da aplicação:

```typescript
provideServiceWorker('ngsw-worker.js', {
  enabled: !isDevMode(),
  registrationStrategy: 'registerWhenStable:30000'
})
```

Com isso:

```text
Desenvolvimento
    |
    v
Service Worker desabilitado

Produção
    |
    v
Service Worker habilitado
```

O registro é realizado apenas fora do modo de desenvolvimento, evitando interferência do cache durante o desenvolvimento local.

## 14.4 Configuração do build PWA

O `angular.json` contém a configuração de Service Worker no build de produção:

```json
"production": {
  "serviceWorker": "ngsw-config.json"
}
```

O diretório `public` também é incluído entre os assets da aplicação.

O build de produção presente no projeto confirma a geração de arquivos como:

```text
ngsw-worker.js
ngsw.json
safety-worker.js
worker-basic.min.js
manifest.webmanifest
```

Portanto, a configuração PWA não está presente apenas no código-fonte: ela já é processada pelo build do Angular.

## 14.5 Cache do shell da aplicação

O arquivo:

```text
ngsw-config.json
```

define dois grupos principais de assets.

### Arquivos essenciais

O grupo `app` utiliza:

```json
"installMode": "prefetch",
"updateMode": "prefetch"
```

Ele inclui arquivos essenciais como:

```text
index.html
manifest.webmanifest
favicon.ico
CSS
JavaScript
```

Esses recursos são carregados antecipadamente pelo Service Worker.

### Imagens e assets

O grupo `assets` utiliza:

```json
"installMode": "lazy",
"updateMode": "prefetch"
```

Ele gerencia recursos como:

```text
PNG
JPG
SVG
WebP
fontes
ícones
imagens da identidade visual
```

Assim, recursos mais pesados podem ser armazenados conforme são utilizados, enquanto novas versões podem ser atualizadas pelo Service Worker.

## 14.6 Cache dos dados públicos

A PWA também possui uma estratégia específica para dados públicos da API de produção.

As rotas configuradas são:

```text
https://segunda-chance-api.onrender.com/api/anuncios**
https://segunda-chance-api.onrender.com/api/categories**
```

A configuração utilizada é:

```json
{
  "strategy": "freshness",
  "maxSize": 50,
  "maxAge": "1h",
  "timeout": "3s"
}
```

O comportamento esperado pode ser representado por:

```text
Usuário solicita os dados
        |
        v
Service Worker consulta a API
        |
        +-------------------------------+
        |                               |
        v                               v
API responde                      API indisponível
normalmente                       ou lenta
        |                               |
        v                               v
Dados atualizados                 Cache disponível
        |                               |
        +---------------+---------------+
                        |
                        v
                  Interface Angular
```

A estratégia prioriza informações novas da API, mas permite o reaproveitamento dos dados públicos previamente carregados quando necessário.

O build atual também confirma que as URLs de produção foram incorporadas ao `ngsw.json`, sem dependência de `localhost:8080` para o cache da versão publicada.

## 14.7 Instalação

O conjunto:

```text
manifest.webmanifest
+
Service Worker
+
HTTPS da Vercel
+
ícones PWA
+
display: standalone
```

fornece a estrutura necessária para que o navegador apresente a aplicação como PWA instalável em dispositivos compatíveis.

Quando instalada, a aplicação utiliza:

```json
"display": "standalone"
```

fazendo com que seja aberta em uma janela com aparência de aplicativo, sem depender da interface convencional de uma aba do navegador.

A rota inicial definida é:

```text
/welcome
```

que corresponde a uma rota existente da aplicação.

## 14.8 Navegação SPA na Vercel

Como o Angular utiliza roteamento do lado do cliente, o frontend possui:

```text
vercel.json
```

com:

```json
{
  "rewrites": [
    {
      "source": "/(.*)",
      "destination": "/index.html"
    }
  ]
}
```

Essa configuração faz com que acessos diretos a rotas como:

```text
/welcome
/ads
/login
/register
/profile
/requests
/my-ads
```

sejam direcionados ao Angular, que então resolve a rota correspondente.

Isso também é importante para a PWA, já que sua rota inicial é `/welcome`.

## 14.9 Sistema Mobile

A versão mobile não consiste apenas na redução visual da interface desktop.

O frontend possui regras de layout específicas para telas menores em diferentes componentes e páginas.

Entre os breakpoints utilizados estão valores como:

```text
1100px
900px
850px
800px
750px
620px
600px
560px
520px
500px
```

Em telas menores, diferentes partes da aplicação passam a utilizar:

```scss
grid-template-columns: 1fr;
```

permitindo que conteúdos que aparecem lado a lado no desktop sejam apresentados verticalmente no celular.

## 14.10 Menu mobile

Em telas de até `900px`, a navegação desktop é substituída por um botão próprio de menu.

O estado do menu é controlado por Angular Signals:

```typescript
readonly isMobileMenuOpen = signal(false);

toggleMobileMenu(): void {
  this.isMobileMenuOpen.update((value) => !value);
}

closeMobileMenu(): void {
  this.isMobileMenuOpen.set(false);
}
```

O cabeçalho disponibiliza um botão:

```text
☰
```

e, quando aberto:

```text
✕
```

O botão utiliza também o estado:

```html
aria-expanded
```

para indicar programaticamente se a navegação está aberta.

## 14.11 Navegação mobile autenticada

Para usuários autenticados, o menu mobile disponibiliza:

```text
Início
Explorar
Meus anúncios
Solicitações
Perfil
Anunciar item
Sair
```

Assim, as funcionalidades principais continuam acessíveis mesmo quando a navegação horizontal do desktop é removida.

## 14.12 Navegação mobile pública

Para usuários não autenticados, o menu disponibiliza:

```text
Início
Explorar
Sobre
Entrar
Criar conta
```

O menu também é fechado automaticamente quando uma rota é escolhida.

## 14.13 Ajustes para telas pequenas

Em telas menores, o cabeçalho reduz:

- largura do logotipo;
- espaçamento interno;
- quantidade de ações visíveis diretamente no header.

Em dispositivos pequenos, os botões desktop do cabeçalho são ocultados e as ações passam a ser concentradas no menu mobile.

O botão de menu utiliza:

```scss
width: 44px;
height: 44px;
```

e os itens do menu possuem altura mínima de:

```scss
min-height: 46px;
```

criando áreas de interação adequadas para toque.

## 14.14 Responsividade das funcionalidades

A adaptação não se restringe ao cabeçalho.

Existem regras responsivas nas principais áreas do frontend, incluindo:

- Landing Page;
- login;
- cadastro;
- exploração de anúncios;
- pesquisa;
- filtros;
- cards;
- criação de anúncio;
- edição de anúncio;
- detalhes do anúncio;
- Meus Anúncios;
- solicitações;
- perfil;
- edição de perfil;
- página Sobre;
- página de erro;
- cabeçalho.

Isso permite que o fluxo principal continue utilizável em telas reduzidas:

```text
Cadastro
   |
   v
Login
   |
   v
Explorar
   |
   +-------------------+
   |                   |
   v                   v
Detalhes          Anunciar item
   |                   |
   v                   v
Solicitação       Meus anúncios
```

## 14.15 Situação final da implementação

A versão atual contém:

| Recurso | Situação |
|---|---|
| Manifesto PWA | Implementado |
| Service Worker | Implementado |
| Registro do Service Worker | Implementado |
| Build de produção com PWA | Implementado |
| Ícones 192x192 e 512x512 | Implementados |
| Ícones maskable | Implementados |
| `display: standalone` | Implementado |
| Cache do app shell | Implementado |
| Cache de assets | Implementado |
| Cache de anúncios públicos | Implementado |
| Cache de categorias | Implementado |
| API de produção no cache | Configurada |
| SPA fallback da Vercel | Implementado |
| Layout responsivo | Implementado |
| Menu mobile | Implementado |
| Navegação autenticada mobile | Implementada |
| Navegação pública mobile | Implementada |
| Formulários responsivos | Implementados |
| Cards e grids responsivos | Implementados |

---

# 15. Atendimento ao Edital

## Backend — requisitos obrigatórios

| Requisito | Implementação |
|---|---|
| API REST estruturada | Spring Boot |
| CRUD de anúncios | Implementado |
| Criar anúncios | Implementado |
| Listar anúncios | Implementado |
| Filtrar anúncios | Implementado |
| Deletar anúncios | Implementado |
| Persistência funcional | TiDB Cloud / MySQL |
| Comunicação em JSON | API REST |

## Backend — diferenciais

| Diferencial | Implementação |
|---|---|
| Autenticação | JWT |
| Separação de usuários | Usuário autenticado e propriedade dos recursos |
| Tratamento de erros | Implementado |
| Validação de campos | Bean Validation |
| Banco real | TiDB Cloud |
| Containerização | Docker |
| Deploy da API | Render |

## Frontend

| Requisito | Implementação |
|---|---|
| Tecnologia web moderna | Angular |
| TypeScript | Utilizado |
| Interface integrada à API | Implementada |
| Landing Page | Implementada |
| Cadastro e login | Implementados |
| Anúncios | Implementados |
| Venda e doação | Implementadas |
| Filtros e pesquisa | Implementados |
| Paginação | Implementada |
| Deploy | Vercel |
| PWA instalável | Implementada com manifesto, Service Worker, ícones e modo standalone |
| Service Worker | Implementado com Angular Service Worker |
| Responsividade | Implementada em desktop, tablet e mobile |
| Navegação mobile | Implementada com menu responsivo |
| Cache offline | Implementado para shell, assets e dados públicos previamente carregados |
| Deploy SPA | Vercel com fallback de rotas para `index.html` |

## Deploy

Além da execução local:

```text
Frontend -> Vercel
Backend -> Render
Banco -> TiDB Cloud
```

O deploy real constitui um diferencial adicional da entrega.

---

# 16. Diário de Bordo da IA

O edital permite o uso de Inteligência Artificial Generativa, desde que seu uso seja consciente, documentado e acompanhado de análise crítica.

## Ferramentas utilizadas

A principal ferramenta utilizada foi o **ChatGPT**, empregado como apoio em:

- análise de erros;
- revisão de código;
- estruturação de funcionalidades;
- configuração de autenticação;
- integração com banco;
- Docker;
- deploy;
- testes;
- documentação.

Também foi utilizado o **Claude** como ferramenta complementar para acompanhamento geral da aplicação e revisão do escopo em relação ao edital.

## Estratégia de Engenharia de Prompts

A estratégia utilizada consistiu em fornecer às IAs o contexto real da aplicação.

Em vez de utilizar apenas perguntas genéricas, foram compartilhados:

- classes Java;
- componentes Angular;
- respostas HTTP;
- logs;
- estruturas de banco;
- capturas de tela;
- mensagens de erro;
- texto literal do edital.

As respostas eram comparadas com o comportamento real da aplicação antes de serem aceitas.

### Exemplo 1 — associação das roles

Durante a análise dos perfis de acesso, a IA interpretou incorretamente a correspondência entre os IDs de `USER` e `ADMIN`.

Após consultar diretamente a tabela `roles`, foi enviado:

> `a tabela esta o inverso disso, eu estou como 1 e eles como 30001`

A evidência do banco mostrou que a interpretação anterior estava incorreta. O resultado real da persistência foi utilizado para corrigir a análise.

### Exemplo 2 — auditoria do edital

Em determinado momento, a análise da IA indicava que o projeto estava essencialmente concluído.

Antes de aceitar essa conclusão, o edital foi fornecido novamente e foi enviado:

> `primeiro verifique se tudo foi cumprido`

A nova análise, confrontando diretamente o sistema com o texto do edital, revelou requisitos e evidências que ainda precisavam ser considerados.

A partir disso, a auditoria passou a ser realizada requisito por requisito.

### Exemplo 3 — diagnóstico do deploy

Durante o deploy do backend no Render, apareceu inicialmente:

> `No open ports detected, continuing to scan...`

A mensagem poderia sugerir uma falha na configuração da porta.

Em vez de alterar imediatamente a porta, os logs completos foram analisados.

Um dos erros enviados à IA foi:

> `Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'entityManagerFactory' [...] Unable to determine Dialect without JDBC metadata`

Depois, um erro mais específico apareceu:

> `Failed to initialize JPA EntityManagerFactory [...] Unknown database 'segundachance'`

A análise mostrou que a ausência de porta era uma consequência da falha de inicialização da persistência.

Para validar a hipótese diretamente no ambiente real, o TiDB foi acessado pelo cliente MySQL:

> `SHOW DATABASES;`

> `USE segundachance;`

> `SELECT DATABASE();`

Somente após confirmar o database correto foi realizado um novo deploy.

O processo terminou com:

> `Your service is live`

Esse caso demonstrou a importância de separar sintomas apresentados pela plataforma da causa raiz registrada nos logs.

## Reflexão crítica

A IA acelerou significativamente o desenvolvimento, especialmente na investigação de erros e na organização das soluções.

Entretanto, as respostas nunca foram consideradas automaticamente corretas.

Um exemplo ocorreu na associação das roles, quando a interpretação da IA foi contradita pelos dados reais do banco.

Outro ocorreu durante a auditoria do edital, quando uma conclusão prematura de que o projeto estava concluído foi revista após comparação direta com os requisitos oficiais.

O deploy forneceu um terceiro exemplo: a mensagem `No open ports detected` poderia ter levado a uma correção incorreta na porta, enquanto a causa real estava na inicialização da camada de persistência.

O processo de uso de IA adotado durante o projeto pode ser resumido por:

```text
Hipótese ou sugestão da IA
        |
        v
Verificação no ambiente real
        |
        v
Comparação com código, logs, banco ou edital
        |
        v
Correção do contexto
        |
        v
Novo teste
        |
        v
Validação da solução
```

A IA foi utilizada como **ferramenta de apoio ao raciocínio e à produtividade**, e não como fonte automática de verdade.

Código-fonte, banco de dados, logs, documentação oficial, Postman, comportamento do frontend e ambiente de produção foram considerados as fontes finais de validação.

Quando uma resposta da IA entrava em conflito com essas evidências, a decisão era tomada com base no comportamento real do sistema.

---

# 17. Estrutura do Repositório

Estrutura conceitual:

```text
Segunda-Chance/
│
├── README.md
├── compose.yaml
│
├── backend/
│   ├── Dockerfile
│   ├── pom.xml
│   ├── database/
│   └── src/
│       └── main/
│           ├── java/
│           └── resources/
│
└── frontend/
    ├── package.json
    ├── angular.json
    └── src/
```

O `README.md` da raiz é o documento principal do repositório e contém as informações técnicas e o Diário de Bordo da IA exigidos pelo edital.

---

# 18. Considerações Finais

O Segunda Chance foi desenvolvido como uma solução full-stack real, evoluindo de um ambiente local para uma arquitetura publicada em serviços de nuvem.

A aplicação integra:

```text
Angular
+
Angular Service Worker / PWA
+
Interface Mobile Responsiva
+
Spring Boot
+
API REST
+
JWT
+
JPA / Hibernate
+
TiDB Cloud
+
Docker
+
Render
+
Vercel
```

Além da implementação funcional, o projeto exigiu diagnóstico de erros, integração entre diferentes ambientes, configuração de segurança, persistência remota, deploy e validação em produção.

A aplicação publicada pode ser acessada sem que a máquina de desenvolvimento permaneça ligada.

---

## Autor

**João Victor Martins Cid**

Projeto desenvolvido para o Processo Seletivo Vortex 2026 — Universidade de Fortaleza.
