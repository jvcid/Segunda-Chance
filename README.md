# Segunda Chance --- Documentação Técnica

## 1. Visão Geral

O **Segunda Chance** é uma plataforma web desenvolvida para a comunidade
acadêmica da Universidade de Fortaleza (Unifor), destinada à compra,
venda e doação de objetos usados. A proposta incentiva economia
circular, reutilização, sustentabilidade e redução de desperdício.

A solução final foi implantada de forma distribuída:

``` text
Usuário
  |
  v
Frontend Angular — Vercel
  |
  | HTTPS / REST / JSON / JWT
  v
Backend Java + Spring Boot — Render
  |
  | JDBC + TLS
  v
TiDB Cloud — compatível com MySQL
```

O frontend e o backend possuem deploy independente e a aplicação
publicada não depende da máquina de desenvolvimento permanecer ligada.

# PARTE I --- BACKEND

## 2. Objetivo do Backend

O backend implementa a API REST do Segunda Chance. Ele concentra regras
de negócio, validação, autenticação, autorização, persistência e
integração com o banco.

Responsabilidades principais:

-   cadastro e autenticação de usuários;
-   autenticação JWT;
-   proteção de rotas;
-   usuários e perfis;
-   categorias;
-   anúncios de venda e doação;
-   pesquisa, filtros e paginação;
-   solicitações;
-   imagens de anúncios;
-   validação de dados;
-   persistência JPA/Hibernate;
-   comunicação com banco remoto;
-   API pública para o frontend.

## 3. Stack do Backend

  Tecnologia          Uso
  ------------------- -----------------------------------
  Java 21             Linguagem e runtime
  Spring Boot 4.1.0   Framework principal
  Spring Web MVC      API REST
  Spring Security     Autenticação e autorização
  Spring Data JPA     Repositórios
  Hibernate           ORM
  Bean Validation     Validação de DTOs
  JJWT                Tokens JWT
  MySQL Connector/J   Driver JDBC
  HikariCP            Pool de conexões
  Maven               Dependências e build
  Docker              Containerização
  Render              Hospedagem da API
  TiDB Cloud          Banco remoto compatível com MySQL
  MySQL Workbench     Inspeção e validação do banco
  Postman             Testes manuais da API

## 4. Arquitetura em Camadas

``` text
HTTP Request
   |
   v
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
TiDB Cloud
```

O projeto utiliza DTOs para não expor diretamente as entidades JPA:

``` text
Request DTO -> Controller -> Service -> Entity/Repository -> Banco
Banco -> Service -> Response DTO -> Cliente
```

Estrutura conceitual:

``` text
src/main/java/com/unifor/segundachance/
├── controller/
├── dto/
│   ├── request/
│   └── response/
├── entity/
├── repository/
├── security/
├── service/
└── SegundachanceApplication.java
```

## 5. Padrão DTO

Foi adotado o padrão `RequestDTO` e `ResponseDTO`. No CRUD de
categorias, por exemplo, foram implementados `CategoryRequestDTO`,
`CategoryResponseDTO`, `CategoryService`, `CategoryController` e
`CategoryRepository`.

Exemplo de validação aplicada:

``` java
@NotBlank(message = "O nome da categoria é obrigatório")
@Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
private String name;
```

Essa abordagem separa o contrato HTTP do modelo de persistência e
facilita validação e manutenção.

## 6. Autenticação e Segurança

A autenticação utiliza Spring Security e JWT. Os componentes
desenvolvidos para esse fluxo incluem:

``` text
SecurityConfig
JwtService
JwtAuthenticationFilter
CustomUserDetailsService
AuthController
UserRepository
```

Fluxo de login:

``` text
E-mail + senha
   |
   v
POST /api/auth/login
   |
   v
Spring Security
   |
   v
Validação
   |
   v
Geração do JWT
   |
   v
Frontend
```

Requisições protegidas utilizam:

``` http
Authorization: Bearer <token>
```

A expiração configurada é de `86400000` ms, equivalente a 24 horas. O
segredo JWT é fornecido por variável de ambiente e não deve ser
versionado.

O cadastro também foi integrado ao frontend através de
`/api/auth/register`. Durante os testes foi identificado que o cadastro
dependia da existência do perfil padrão `USER` no banco; o dado
necessário foi incluído para permitir a conclusão do fluxo.

## 7. Categorias

As categorias são persistidas e fornecidas dinamicamente pela API:

1.  Acessórios
2.  Computação
3.  Eletrônicos
4.  Esportes
5.  Instrumentos musicais
6.  Livros
7.  Materiais acadêmicos
8.  Móveis
9.  Roupas
10. Outros

Isso evita manter uma lista independente e divergente apenas no
frontend.

## 8. Anúncios

O recurso central da aplicação representa itens de `VENDA` ou `DOACAO`.
A integração utiliza informações como identificador, título, descrição,
categoria, tipo, preço, estado, proprietário e imagem principal.

A consulta de anúncios suporta parâmetros utilizados pelo frontend:

``` text
page
size
search
categoryId
tipo
status
```

A resposta paginada disponibiliza dados como `content`, `number` e
`totalPages`.

Foram integrados pesquisa por título, filtro por categoria, filtro por
tipo, estado e paginação. O frontend utiliza, entre outros, o estado
`DISPONIVEL`.

## 9. Solicitações e Imagens

O backend possui fluxo de solicitações para representar o interesse de
usuários nos itens anunciados. O módulo é consumido pela área de
solicitações do frontend.

Também existe integração para imagens de anúncios. No frontend,
`AdImagesService` consome os recursos correspondentes da API. A
correspondência entre a resposta da API e `imagemPrincipalUrl` foi
verificada durante os testes finais da listagem.

## 10. Banco de Dados

### 10.1 Desenvolvimento local

Durante o desenvolvimento foi utilizado MySQL local, inclusive com
configuração em `localhost:3395`. Essa URL não poderia ser utilizada
pelo backend hospedado.

### 10.2 Tentativa com Aiven

Foi inicialmente criado um MySQL remoto no Aiven. O serviço permaneceu
por tempo excessivo em `Building` e depois `Rebuilding`. Como isso
colocava o prazo da entrega em risco, a solução foi substituída.

### 10.3 Solução final: TiDB Cloud

Foi adotado o TiDB Cloud, compatível com o protocolo MySQL. A instância
final foi denominada:

``` text
segunda-chance-db
```

O endpoint utilizado foi:

``` text
gateway01.sa-east-1.prod.aws.tidbcloud.com
```

Porta:

``` text
4000
```

A conexão pública exige TLS.

O banco da aplicação foi criado com:

``` sql
CREATE DATABASE IF NOT EXISTS segundachance;
```

e validado com:

``` sql
SHOW DATABASES;
USE segundachance;
SELECT DATABASE();
```

O resultado de `SELECT DATABASE()` confirmou `segundachance`.

## 11. Cliente MySQL e Workbench

No Windows, `mysql` inicialmente não era reconhecido no PowerShell
porque o executável não estava no `PATH`. Foram localizados:

``` text
C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe
C:\Program Files\MySQL\MySQL Workbench 8.0\mysql.exe
```

Também foi corrigida uma confusão entre comandos de terminal e SQL:
`mysql -h ...` deve ser executado no PowerShell/CMD, enquanto dentro do
prompt `mysql>` são executados comandos como `CREATE DATABASE`, `USE` e
`SELECT`.

O MySQL Workbench foi utilizado para verificar a persistência no TiDB.
Exemplo:

``` sql
SELECT user_id, name, email, role_id
FROM users
ORDER BY user_id DESC;
```

## 12. Configuração de Produção

O `application.yml` foi preparado para receber configurações por
variáveis de ambiente:

``` yaml
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

A URL JDBC de produção segue:

``` text
jdbc:mysql://gateway01.sa-east-1.prod.aws.tidbcloud.com:4000/segundachance?sslMode=REQUIRED
```

No Render foram configuradas:

``` text
SPRING_DATASOURCE_URL
SPRING_DATASOURCE_USERNAME
SPRING_DATASOURCE_PASSWORD
JWT_SECRET
SPRING_JPA_HIBERNATE_DDL_AUTO
```

Credenciais e segredos reais não devem constar no README nem no Git.

## 13. Porta em Produção

A configuração:

``` yaml
server:
  port: ${PORT:8080}
```

permite usar `8080` localmente e a porta fornecida pelo Render em
produção.

## 14. Docker, Maven e Build

O backend foi publicado no Render como Web Service baseado em Docker.

O build executou:

``` bash
mvn clean package -DskipTests
```

O log confirmou compilação de 57 arquivos Java e geração de:

``` text
/app/target/segundachance-0.0.1-SNAPSHOT.jar
```

O Spring Boot Maven Plugin realizou o `repackage`. O log apresentou
`BUILD SUCCESS`, Spring Boot `4.1.0` e Java `21.0.11`.

Fluxo:

``` text
GitHub -> Render -> Docker build -> Maven -> JAR -> Spring Boot
```

## 15. Deploy no Render

Serviço:

``` text
segunda-chance-api
```

URL pública:

``` text
https://segunda-chance-api.onrender.com
```

Durante as correções foram utilizadas opções de Manual Deploy como
`Deploy latest commit`, `Clear build cache & deploy` e reinicialização
do serviço.

Ao final, o Render confirmou:

``` text
Your service is live
```

O backend passou a funcionar independentemente da máquina local.

## 16. Problemas de Deploy e Soluções

### 16.1 `Unable to determine Dialect without JDBC metadata`

O Hibernate não conseguia obter metadados JDBC. A investigação levou à
revisão da `SPRING_DATASOURCE_URL` e da conexão remota.

### 16.2 `Unknown database 'segundachance'`

Depois de alcançar o TiDB, o backend falhou porque o database esperado
ainda não existia. Isso confirmou que host e conexão estavam chegando ao
servidor. A solução foi criar `segundachance` manualmente.

### 16.3 Falha encadeada do JPA

Com o `EntityManagerFactory` indisponível, falharam também dependências
como:

``` text
UserRepository
CustomUserDetailsService
JwtAuthenticationFilter
```

A causa raiz era a persistência, não o filtro JWT. A criação/correção do
banco permitiu a inicialização da cadeia.

### 16.4 `No open ports detected`

O Render exibiu essa mensagem enquanto o Spring encerrava antes de o
Tomcat permanecer ativo. A correção real foi resolver a falha JDBC; não
simplesmente trocar uma porta.

### 16.5 Provisionamento remoto

O Aiven foi abandonado após demora excessiva em `Building/Rebuilding`. O
TiDB foi adotado para reduzir o risco de atraso.

### 16.6 Importação pelo painel TiDB

A importação pelo painel não era o caminho mais rápido para o SQL local.
Foi preferida a conexão MySQL, criação do database e uso do
Hibernate/JPA para criar/atualizar a estrutura.

## 17. Testes do Backend

A validação ocorreu em três níveis:

``` text
I. Backend isolado
   Postman / requisições HTTP

II. Backend + banco
    API -> TiDB

III. Sistema completo
     Vercel -> Render -> TiDB
```

Foram realizados testes de:

-   compilação Maven;
-   inicialização Spring Boot;
-   cadastro;
-   persistência de usuário;
-   login;
-   JWT;
-   rotas autenticadas;
-   categorias;
-   criação de anúncio;
-   venda;
-   doação;
-   listagem;
-   pesquisa;
-   filtros;
-   paginação;
-   imagens;
-   solicitações;
-   persistência no banco;
-   integração em produção.

Os testes de produção foram importantes porque o funcionamento local não
garante que URL, HTTPS, CORS, credenciais, JWT e banco remoto estejam
corretos.

## 18. Estratégia de Diagnóstico

Foram utilizados:

-   logs do Spring Boot;
-   logs de deploy do Render;
-   Postman;
-   MySQL CLI;
-   MySQL Workbench;
-   DevTools do navegador;
-   aba Network/XHR;
-   consultas SQL;
-   testes pelo frontend publicado.

Essa combinação permitiu localizar falhas entre interface, API,
autenticação, configuração de ambiente, JDBC e persistência.

## 19. Segurança e Boas Práticas

Foram aplicados:

-   Spring Security;
-   JWT;
-   autenticação stateless;
-   filtro JWT;
-   DTOs;
-   Bean Validation;
-   credenciais por variáveis de ambiente;
-   `JWT_SECRET` fora do código;
-   TLS obrigatório com o TiDB;
-   separação entre ambientes;
-   banco acessado somente pelo backend;
-   separação Controller/Service/Repository;
-   configuração de porta adequada ao ambiente.

## 20. Execução Local do Backend

Pré-requisitos:

``` text
Java 21
Maven
MySQL ou banco compatível
```

Variáveis necessárias:

``` text
SPRING_DATASOURCE_URL
SPRING_DATASOURCE_USERNAME
SPRING_DATASOURCE_PASSWORD
JWT_SECRET
SPRING_JPA_HIBERNATE_DDL_AUTO
```

Executar:

``` bash
mvn spring-boot:run
```

Build:

``` bash
mvn clean package
```

ou:

``` bash
mvn clean package -DskipTests
```

Sem `PORT`, a aplicação usa `8080`.

## 21. Requisitos Funcionais Atendidos pelo Backend

  Requisito             Implementação
  --------------------- --------------------------
  Cadastro              API + persistência
  Login                 Spring Security
  Autenticação          JWT
  Rotas protegidas      Security + filtro JWT
  Usuários              Service/Repository
  Categorias            API e banco
  Anúncios              API de gerenciamento
  Venda e doação        Tipos de anúncio
  Pesquisa              API
  Filtros               Categoria, tipo e estado
  Paginação             Resposta paginada
  Solicitações          Fluxo integrado
  Imagens               Integração de anúncio
  Validação             Bean Validation
  Persistência          JPA/Hibernate
  Banco remoto          TiDB Cloud
  Deploy                Render
  Integração            Frontend Vercel
  Configuração segura   Variáveis de ambiente

## 22. Requisitos Não Funcionais

**Segurança:** JWT, Spring Security, TLS e segredos externos ao código.

**Manutenibilidade:** separação em controllers, services, repositories,
DTOs, entities e security.

**Desacoplamento:** frontend, backend e persistência são camadas
independentes.

**Portabilidade:** aplicação empacotada em JAR e executada em Docker.

**Disponibilidade externa:** API publicada no Render.

**Escalabilidade de código:** organização por responsabilidades e
recursos.

## 23. Evidências Recomendadas

Para a entrega, manter evidências de:

1.  `BUILD SUCCESS`;
2.  Render com serviço ativo;
3.  `Your service is live`;
4.  URL pública da API;
5.  banco `segundachance` no TiDB;
6.  conexão no Workbench;
7.  tabelas;
8.  usuário persistido;
9.  categorias;
10. anúncio;
11. solicitação;
12. cadastro pelo frontend;
13. login e JWT;
14. requisição protegida;
15. listagem e filtros;
16. venda e doação;
17. integração Vercel -\> Render -\> TiDB.

## 24. Infraestrutura Final

  Camada         Tecnologia                    Hospedagem
  -------------- ----------------------------- ------------
  Frontend       Angular / TypeScript / SCSS   Vercel
  Backend        Java 21 / Spring Boot 4.1     Render
  Persistência   TiDB compatível com MySQL     TiDB Cloud
  Segurança      Spring Security + JWT         Backend
  ORM            JPA / Hibernate               Backend
  Build          Maven + Docker                Render

## 25. Status do Backend

Ao final do processo documentado:

-   build concluído;
-   JAR gerado;
-   imagem Docker construída;
-   Spring Boot inicializado;
-   conexão Render -\> TiDB configurada;
-   database `segundachance` criado;
-   variáveis de ambiente configuradas;
-   API publicada;
-   persistência validada;
-   integração com o frontend disponível.

API:

``` text
https://segunda-chance-api.onrender.com
```

## 26. Checklist Final do Backend

-   [ ] cadastrar novo usuário;
-   [ ] confirmar persistência;
-   [ ] realizar login;
-   [ ] confirmar JWT;
-   [ ] testar rota protegida;
-   [ ] listar categorias;
-   [ ] listar e pesquisar anúncios;
-   [ ] testar filtros e paginação;
-   [ ] criar venda;
-   [ ] criar doação;
-   [ ] testar imagens;
-   [ ] testar solicitações;
-   [ ] confirmar respostas para dados inválidos;
-   [ ] confirmar ausência de segredos no Git;
-   [ ] executar o fluxo usando apenas serviços publicados.

------------------------------------------------------------------------

# PARTE II --- FRONTEND

## 1. Visão Geral

O frontend do **Segunda Chance** corresponde à interface web da
plataforma desenvolvida para a comunidade acadêmica da Universidade de
Fortaleza (Unifor).

A aplicação tem como objetivo permitir que estudantes, professores e
colaboradores encontrem uma nova finalidade para objetos que não
utilizam mais, possibilitando a publicação de itens para **venda ou
doação** e incentivando princípios de economia circular, reutilização e
sustentabilidade dentro do ambiente acadêmico.

O frontend foi desenvolvido como uma aplicação web independente,
responsável pela interação entre o usuário e a API REST do Segunda
Chance.

A aplicação permite realizar operações como:

-   cadastro de usuário;
-   autenticação;
-   navegação pela plataforma;
-   consulta de anúncios;
-   pesquisa de anúncios;
-   filtragem por categoria;
-   filtragem por tipo de anúncio;
-   publicação de anúncios;
-   publicação de itens para venda;
-   publicação de itens para doação;
-   visualização dos próprios anúncios;
-   consulta aos detalhes de um anúncio;
-   gerenciamento de solicitações;
-   acesso às informações do usuário;
-   encerramento da sessão.

O frontend foi desenvolvido utilizando **Angular** e posteriormente
publicado em ambiente de produção através da **Vercel**.

------------------------------------------------------------------------

# 2. Objetivo do Frontend

O frontend foi desenvolvido com o objetivo de fornecer uma interface
simples, organizada e responsiva para utilização das funcionalidades
disponibilizadas pelo backend.

A aplicação atua como cliente da API REST do projeto.

Sua responsabilidade principal consiste em:

1.  receber as ações realizadas pelo usuário;
2.  validar os dados necessários na interface;
3.  realizar requisições HTTP para a API;
4.  processar as respostas retornadas pelo backend;
5.  apresentar as informações de forma compreensível;
6.  controlar o estado da sessão autenticada;
7.  organizar a navegação entre as funcionalidades da plataforma.

A arquitetura mantém o frontend desacoplado do backend.

Dessa forma, a aplicação Angular e a API Spring Boot podem ser
desenvolvidas, executadas e publicadas separadamente.

------------------------------------------------------------------------

# 3. Tecnologias Utilizadas

## 3.1 Angular

O Angular foi utilizado como principal framework de desenvolvimento do
frontend.

A aplicação utiliza componentes para separar as diferentes áreas e
responsabilidades da interface.

Entre os recursos utilizados estão:

-   componentes standalone;
-   templates HTML;
-   SCSS;
-   serviços;
-   injeção de dependências;
-   roteamento;
-   formulários;
-   requisições HTTP;
-   Signals;
-   controle de estado local;
-   diretivas e estruturas condicionais do Angular.

------------------------------------------------------------------------

## 3.2 TypeScript

O TypeScript foi utilizado para implementação da lógica da aplicação.

Sua utilização permitiu definir modelos tipados para representar as
informações recebidas da API, reduzindo inconsistências entre frontend e
backend.

Entre os objetos representados no frontend encontram-se:

-   usuários;
-   anúncios;
-   categorias;
-   solicitações;
-   autenticação;
-   imagens de anúncios;
-   respostas paginadas.

------------------------------------------------------------------------

## 3.3 HTML

Os templates HTML são responsáveis pela estrutura visual das páginas.

Foram utilizadas estruturas de template do Angular para:

-   renderização condicional;
-   iteração de anúncios;
-   iteração de categorias;
-   estados de carregamento;
-   estados de erro;
-   estados sem resultados;
-   paginação;
-   exibição dinâmica de dados.

------------------------------------------------------------------------

## 3.4 SCSS

O SCSS foi utilizado para construção da identidade visual e organização
dos estilos da aplicação.

A interface foi construída procurando manter consistência entre:

-   cores;
-   espaçamentos;
-   tipografia;
-   botões;
-   campos;
-   cards;
-   filtros;
-   cabeçalho;
-   páginas de autenticação;
-   páginas de gerenciamento;
-   mensagens de estado.

------------------------------------------------------------------------

## 3.5 Angular HttpClient

A comunicação com o backend é realizada através do cliente HTTP do
Angular.

O frontend consome os endpoints REST disponibilizados pela API para
operações relacionadas a:

-   autenticação;
-   usuários;
-   anúncios;
-   categorias;
-   solicitações;
-   imagens.

------------------------------------------------------------------------

## 3.6 Vercel

A Vercel foi utilizada para publicação do frontend em ambiente de
produção.

O deploy separado do frontend demonstra a separação entre as camadas da
aplicação.

A aplicação publicada pode consumir a API hospedada externamente através
da URL configurada no ambiente de produção.

------------------------------------------------------------------------

# 4. Arquitetura do Frontend

O projeto foi organizado procurando separar interface, regras de
apresentação, comunicação HTTP e modelos.

A estrutura geral segue o princípio:

``` text
Component
   |
   v
Service
   |
   v
HTTP Client
   |
   v
API REST
```

Os componentes não realizam diretamente a implementação das chamadas
HTTP.

Essa responsabilidade é delegada aos serviços.

Exemplo:

``` text
AdsList
   |
   v
AdsService
   |
   v
API Segunda Chance
```

Esse modelo melhora:

-   organização;
-   manutenção;
-   reutilização;
-   separação de responsabilidades;
-   legibilidade;
-   possibilidade de evolução futura.

------------------------------------------------------------------------

# 5. Organização do Projeto

A aplicação foi dividida conceitualmente em áreas responsáveis pelas
diferentes funcionalidades.

Uma estrutura simplificada pode ser representada por:

``` text
src/
└── app/
    ├── core/
    ├── features/
    │   ├── auth/
    │   ├── ads/
    │   ├── requests/
    │   └── profile/
    │
    └── shared/
        └── components/
            └── layout/
                └── header/
```

Dentro das funcionalidades relacionadas aos anúncios, foram utilizados
elementos como:

``` text
ads/
├── models/
│   ├── ad
│   └── category
│
├── services/
│   ├── ads
│   ├── categories
│   └── ad-images
│
└── pages/
```

A estrutura permite manter próximas as classes relacionadas a uma mesma
funcionalidade.

------------------------------------------------------------------------

# 6. Modelos

O frontend utiliza interfaces e modelos TypeScript para representar os
dados recebidos do backend.

Entre os principais modelos utilizados encontram-se:

## 6.1 Anúncio

Representa um item disponibilizado na plataforma.

Entre as informações utilizadas pela interface estão:

-   identificador;
-   título;
-   descrição;
-   tipo;
-   preço;
-   categoria;
-   imagem;
-   estado do anúncio.

O tipo pode representar, por exemplo:

``` text
VENDA
DOACAO
```

------------------------------------------------------------------------

## 6.2 Categoria

As categorias são carregadas dinamicamente através da API.

Cada categoria possui um identificador utilizado internamente pelo
backend e um nome apresentado ao usuário.

Exemplo conceitual:

``` json
{
  "id": 1,
  "name": "Acessórios"
}
```

------------------------------------------------------------------------

# 7. Categorias

As categorias disponíveis na interface são obtidas do banco de dados
através da API.

Isso evita manter uma lista fixa somente no frontend.

Foram cadastradas categorias destinadas a representar os principais
tipos de objetos utilizados dentro da comunidade acadêmica.

Entre elas:

1.  Acessórios
2.  Computação
3.  Eletrônicos
4.  Esportes
5.  Instrumentos musicais
6.  Livros
7.  Materiais acadêmicos
8.  Móveis
9.  Roupas
10. Outros

A categoria **Outros** é mantida ao final da apresentação para
representar itens que não pertencem às categorias específicas.

------------------------------------------------------------------------

# 8. Sistema de Autenticação

O frontend possui páginas destinadas ao cadastro e autenticação dos
usuários.

## 8.1 Cadastro

A tela de cadastro permite fornecer as informações necessárias para
criação da conta.

O fluxo realizado é:

``` text
Usuário
   |
   v
Formulário de cadastro
   |
   v
AuthService
   |
   v
POST /api/auth/register
   |
   v
API
   |
   v
Banco de dados
```

A interface apresenta ao usuário informações sobre o estado da operação.

Entre os estados previstos estão:

``` text
Criar conta
Criando conta...
Conta criada
Erro ao criar conta
```

------------------------------------------------------------------------

## 8.2 Login

Após possuir uma conta, o usuário pode realizar autenticação através da
página de login.

O frontend envia as credenciais para a API.

Após autenticação válida, o token recebido é utilizado para identificar
as requisições que exigem autenticação.

Fluxo conceitual:

``` text
E-mail + senha
      |
      v
POST /api/auth/login
      |
      v
API
      |
      v
JWT
      |
      v
Frontend
```

------------------------------------------------------------------------

# 9. Integração com JWT

A autenticação entre frontend e backend utiliza JSON Web Token.

Após o login, as requisições para recursos protegidos devem enviar o
token através do cabeçalho HTTP:

``` http
Authorization: Bearer <token>
```

Isso permite que a API identifique o usuário responsável pela operação.

Esse mecanismo é utilizado em operações que dependem da identidade do
usuário, como gerenciamento de anúncios e solicitações.

------------------------------------------------------------------------

# 10. Página Inicial

A página inicial foi desenvolvida para apresentar o conceito do Segunda
Chance e facilitar o acesso às principais funcionalidades.

A identidade da aplicação foi construída em torno da mensagem:

> Dar uma nova vida aos objetos.

A página comunica os três principais comportamentos esperados na
plataforma:

## 10.1 Compre

Permite encontrar itens disponibilizados por outros integrantes da
comunidade.

## 10.2 Venda

Permite dar um novo destino a objetos que o usuário não utiliza mais.

## 10.3 Doe

Permite disponibilizar gratuitamente um objeto para outro integrante da
comunidade.

A interface foi construída para não apresentar o Segunda Chance
simplesmente como uma loja virtual tradicional, mas como uma plataforma
de circulação e reaproveitamento de objetos.

------------------------------------------------------------------------

# 11. Identidade Visual

A identidade visual do frontend foi desenvolvida especificamente para o
projeto Segunda Chance.

Foram considerados os seguintes conceitos:

-   sustentabilidade;
-   confiança;
-   comunidade;
-   colaboração;
-   simplicidade;
-   organização;
-   inovação;
-   reutilização;
-   continuidade.

A aplicação utiliza uma identidade visual consistente nas páginas e
componentes.

Também foram produzidos elementos visuais específicos para:

-   marca Segunda Chance;
-   símbolo utilizado no cabeçalho;
-   representação de compra;
-   representação de venda;
-   representação de doação.

------------------------------------------------------------------------

# 12. Cabeçalho e Navegação

Após a autenticação, o usuário possui acesso ao cabeçalho principal da
aplicação.

Entre as opções de navegação encontram-se:

``` text
Início
Explorar
Meus anúncios
Solicitações
Perfil
Anunciar item
Sair
```

O cabeçalho foi implementado como componente compartilhado para evitar
repetição entre as páginas.

------------------------------------------------------------------------

# 13. Exploração de Anúncios

A página de exploração é responsável por apresentar os anúncios
disponíveis na plataforma.

O frontend consulta a API e utiliza a resposta paginada para construir
dinamicamente os cards.

O carregamento é realizado através do serviço responsável pelos
anúncios.

Exemplo conceitual:

``` typescript
this.adsService.getAds(...)
```

A resposta é utilizada para atualizar:

``` text
content
number
totalPages
```

Assim, o frontend consegue controlar:

-   anúncios da página atual;
-   página selecionada;
-   quantidade de páginas;
-   navegação entre páginas.

------------------------------------------------------------------------

# 14. Pesquisa de Anúncios

A página de exploração possui um campo de pesquisa por título.

O usuário pode informar um termo e executar a busca.

A interface envia o valor para o serviço de anúncios, que realiza a
consulta correspondente na API.

Exemplo de interface:

``` text
Buscar por título...
[ Buscar ]
```

A pesquisa pode ser utilizada em conjunto com os filtros.

------------------------------------------------------------------------

# 15. Filtro por Categoria

O frontend carrega as categorias através do serviço de categorias.

A categoria selecionada é armazenada utilizando um Signal:

``` typescript
selectedCategoryId
```

O identificador é posteriormente enviado na consulta de anúncios.

A interface oferece também a opção:

``` text
Todas
```

que remove o filtro de categoria.

------------------------------------------------------------------------

# 16. Filtro por Tipo

Os anúncios podem ser filtrados pelo tipo da publicação.

A interface disponibiliza:

``` text
Todos
Venda
Doação
```

Internamente, os valores utilizados são compatíveis com os valores
esperados pela API:

``` text
VENDA
DOACAO
```

------------------------------------------------------------------------

# 17. Combinação de Filtros

A tela foi construída para permitir a combinação entre:

-   termo de pesquisa;
-   categoria;
-   tipo.

Assim, o usuário pode realizar uma consulta mais específica.

Exemplo:

``` text
Pesquisa: notebook
Categoria: Computação
Tipo: Venda
```

A aplicação também possui a opção:

``` text
Limpar filtros
```

responsável por restaurar o estado inicial da consulta.

------------------------------------------------------------------------

# 18. Compatibilidade com Parâmetros da URL

A página de anúncios também considera parâmetros recebidos através da
URL.

Por exemplo:

``` text
/ads?category=Livros
```

Quando uma categoria é recebida pelo nome, o frontend procura a
categoria correspondente entre aquelas retornadas pela API.

Em seguida, utiliza seu identificador real para executar a consulta.

Essa solução permite integrar links da página inicial com o sistema de
categorias baseado em IDs utilizado pelo backend.

------------------------------------------------------------------------

# 19. Paginação

A listagem de anúncios possui suporte à paginação retornada pelo
backend.

O frontend mantém os Signals:

``` typescript
currentPage
totalPages
```

A interface disponibiliza controles:

``` text
Anterior
Página X de Y
Próxima
```

Os botões são automaticamente desabilitados quando não existe uma página
anterior ou posterior.

------------------------------------------------------------------------

# 20. Estados da Interface

Uma preocupação durante o desenvolvimento foi não deixar a interface sem
retorno durante operações assíncronas.

Por isso, foram implementados diferentes estados visuais.

## 20.1 Carregamento

Exemplo:

``` text
Carregando anúncios...
```

Também foram utilizados indicadores visuais de carregamento.

------------------------------------------------------------------------

## 20.2 Erro

Caso uma requisição falhe, a interface apresenta uma mensagem ao
usuário.

Exemplo:

``` text
Não foi possível carregar os anúncios.
Tente novamente em alguns instantes.
```

A página também pode disponibilizar:

``` text
Tentar novamente
```

------------------------------------------------------------------------

## 20.3 Resultado vazio

Caso a consulta seja concluída corretamente, mas nenhum anúncio
corresponda aos filtros, é apresentado:

``` text
Nenhum anúncio encontrado.
Tente alterar os filtros ou realizar uma nova busca.
```

------------------------------------------------------------------------

## 20.4 Resultado disponível

Quando existem anúncios, os dados retornados são convertidos em cards.

------------------------------------------------------------------------

# 21. Cards de Anúncios

Cada anúncio é apresentado em um card contendo as informações
necessárias para identificação rápida do item.

Entre os dados apresentados estão:

-   imagem;
-   categoria;
-   tipo;
-   título;
-   descrição;
-   preço.

Quando o anúncio corresponde a uma doação, a interface apresenta:

``` text
Doação
```

em vez de um valor monetário.

Para anúncios de venda, o valor é formatado utilizando o padrão
brasileiro:

``` typescript
ad.preco.toLocaleString('pt-BR', {
  style: 'currency',
  currency: 'BRL',
});
```

------------------------------------------------------------------------

# 22. Tratamento de Imagens

Os anúncios possuem suporte à exibição de uma imagem principal.

Quando existe uma URL de imagem válida, o frontend utiliza:

``` html
<img [src]="ad.imagemPrincipalUrl" [alt]="ad.titulo" />
```

Caso uma imagem não esteja disponível, a interface pode utilizar um
placeholder contendo a categoria do item.

Essa abordagem evita que o layout do card seja quebrado quando um
anúncio não possuir imagem disponível.

------------------------------------------------------------------------

# 23. Publicação de Anúncios

A plataforma possui uma tela específica para publicação de novos itens.

O usuário pode informar dados referentes ao anúncio e selecionar a
finalidade do item.

As opções principais são:

``` text
Vender
Doar
```

Para uma venda, o usuário pode informar um preço.

Para uma doação, o item é disponibilizado sem cobrança.

------------------------------------------------------------------------

# 24. Seleção de Categoria no Cadastro de Anúncio

O formulário de publicação consulta as categorias existentes no backend.

O usuário pode selecionar uma categoria através de um campo de seleção.

Exemplo:

``` text
Selecione uma categoria
Acessórios
Computação
Eletrônicos
Esportes
Instrumentos musicais
Livros
Materiais acadêmicos
Móveis
Roupas
Outros
```

O identificador da categoria selecionada é enviado para a API durante a
criação do anúncio.

------------------------------------------------------------------------

# 25. Meus Anúncios

A aplicação possui uma página destinada aos anúncios pertencentes ao
usuário autenticado.

Essa página permite separar a consulta geral da plataforma dos itens
publicados pelo próprio usuário.

A interface apresenta a quantidade de anúncios publicados e a listagem
correspondente.

Exemplo:

``` text
Meus anúncios

Gerencie os itens que você colocou em circulação na comunidade.

3 anúncios publicados
```

Essa área funciona como espaço de gerenciamento dos itens publicados
pelo usuário.

------------------------------------------------------------------------

# 26. Detalhes do Anúncio

Ao selecionar um anúncio, a aplicação pode navegar para sua página
específica.

A navegação é realizada utilizando o identificador do anúncio.

Exemplo:

``` typescript
this.router.navigate(['/ads', id]);
```

Isso permite manter URLs individuais para os anúncios.

------------------------------------------------------------------------

# 27. Solicitações

O frontend também possui integração com o fluxo de solicitações.

Esse fluxo permite representar o interesse de outro usuário em um item
disponibilizado na plataforma.

A área de solicitações faz parte da navegação autenticada e comunica-se
com os endpoints correspondentes da API.

O objetivo é permitir acompanhar as interações relacionadas aos itens
anunciados.

------------------------------------------------------------------------

# 28. Perfil

A aplicação possui uma área destinada às informações do usuário
autenticado.

A página de perfil integra o conjunto de funcionalidades privadas da
aplicação e pode ser acessada através do cabeçalho.

------------------------------------------------------------------------

# 29. Logout

O cabeçalho possui a opção:

``` text
Sair
```

Essa funcionalidade encerra a sessão do usuário no frontend e impede que
o token de autenticação continue sendo utilizado pela sessão encerrada.

------------------------------------------------------------------------

# 30. Serviços

A comunicação com a API foi organizada em serviços específicos.

Entre os serviços utilizados durante o desenvolvimento encontram-se:

``` text
AuthService
AdsService
CategoriesService
AdImagesService
```

Outros serviços podem existir de acordo com a funcionalidade
correspondente.

Essa divisão evita centralizar todas as chamadas HTTP em uma única
classe.

------------------------------------------------------------------------

# 31. AdsService

O serviço de anúncios centraliza as operações HTTP relacionadas aos
anúncios.

A página de exploração utiliza esse serviço para solicitar anúncios
considerando parâmetros como:

``` text
page
size
search
categoryId
tipo
status
```

Exemplo conceitual:

``` typescript
this.adsService.getAds(
  page,
  8,
  search,
  categoryId,
  tipo,
  'DISPONIVEL'
);
```

------------------------------------------------------------------------

# 32. CategoriesService

O serviço de categorias consulta as categorias disponibilizadas pela
API.

A resposta é utilizada em diferentes partes da aplicação, incluindo:

-   filtros;
-   formulário de criação de anúncio;
-   identificação da categoria dos itens.

Isso evita duplicação da lista de categorias no frontend.

------------------------------------------------------------------------

# 33. Signals

O projeto utiliza Signals do Angular para gerenciamento de estado local
dos componentes.

Na listagem de anúncios, por exemplo, são mantidos estados semelhantes
a:

``` typescript
readonly ads = signal<Ad[]>([]);
readonly categories = signal<Category[]>([]);

readonly isLoading = signal(true);
readonly hasError = signal(false);

readonly currentPage = signal(0);
readonly totalPages = signal(0);

readonly searchTerm = signal('');

readonly selectedCategoryId = signal<number | null>(null);
readonly selectedType = signal<'' | 'VENDA' | 'DOACAO'>('');
```

Essa estratégia facilita a atualização reativa da interface.

------------------------------------------------------------------------

# 34. Tratamento de Operações Assíncronas

As chamadas HTTP são tratadas considerando sucesso e erro.

Exemplo conceitual:

``` typescript
service.getData().subscribe({
  next: (response) => {
    // processamento da resposta
  },

  error: (error) => {
    // tratamento da falha
  },
});
```

Essa estrutura foi utilizada para evitar que falhas de comunicação
deixassem a aplicação sem feedback.

------------------------------------------------------------------------

# 35. Integração Frontend e Backend

Durante o desenvolvimento, o frontend foi inicialmente executado
localmente.

Exemplo:

``` text
http://localhost:4200
```

O backend também pode ser executado localmente durante o
desenvolvimento.

Posteriormente, a aplicação passou a utilizar a API publicada
externamente.

A arquitetura final pode ser representada por:

``` text
Navegador
    |
    v
Frontend Angular
Vercel
    |
    | HTTPS / JSON
    v
API REST
Render
    |
    v
Banco de dados
TiDB Cloud
```

Essa configuração demonstra uma aplicação distribuída em múltiplos
serviços.

------------------------------------------------------------------------

# 36. Configuração de Ambientes

O frontend utiliza arquivos de ambiente para evitar manter URLs
específicas diretamente nos serviços.

Conceitualmente:

``` typescript
environment.apiUrl
```

Isso permite diferenciar a API utilizada durante desenvolvimento da API
utilizada em produção.

Exemplo:

``` text
Desenvolvimento
Angular -> API local

Produção
Vercel -> API publicada no Render
```

Durante o processo de deploy, as configurações dos serviços do frontend
foram ajustadas para utilizar corretamente o ambiente de produção.

------------------------------------------------------------------------

# 37. Deploy

O frontend foi publicado utilizando a Vercel.

A API foi publicada separadamente no Render.

Essa estratégia atende ao objetivo de disponibilizar uma aplicação real
acessível através da Internet.

## 37.1 Frontend

Hospedagem:

``` text
Vercel
```

Aplicação:

``` text
https://segunda-chance-flax.vercel.app
```

## 37.2 Backend

API REST hospedada externamente no Render.

O endereço da API utilizado durante os testes de produção foi:

``` text
https://segunda-chance-api.onrender.com
```

A documentação geral do projeto deverá manter os links atualizados caso
os endereços de produção sejam alterados.

------------------------------------------------------------------------

# 38. Testes Realizados no Frontend

O frontend foi validado durante o desenvolvimento através de testes
manuais e testes de integração com a API.

Os testes não se limitaram à execução isolada das páginas.

Também foram realizados testes utilizando o frontend publicado e o
backend publicado.

------------------------------------------------------------------------

# 39. Teste de Cadastro

Foi testado o fluxo:

``` text
Abrir cadastro
      |
      v
Informar nome
      |
      v
Informar e-mail
      |
      v
Informar senha
      |
      v
Confirmar senha
      |
      v
Criar conta
      |
      v
Frontend envia requisição
      |
      v
API processa cadastro
      |
      v
Usuário é persistido
```

A persistência foi verificada diretamente no banco de dados durante os
testes.

------------------------------------------------------------------------

# 40. Validação do Banco Durante os Testes

Durante os testes de integração, o banco TiDB foi acessado através do
MySQL Workbench.

Foi possível verificar diretamente os registros persistidos.

Por exemplo:

``` sql
SELECT user_id, name, email, role_id
FROM users
ORDER BY user_id DESC;
```

Esse procedimento permitiu confirmar que operações realizadas através do
frontend estavam efetivamente chegando ao backend e sendo persistidas no
banco.

------------------------------------------------------------------------

# 41. Teste das Categorias

As categorias foram cadastradas no banco e posteriormente carregadas
pelo frontend.

Foram verificados:

-   carregamento das categorias;
-   apresentação no formulário;
-   apresentação nos filtros;
-   utilização do identificador correto;
-   ordenação visual;
-   utilização da categoria durante a criação do anúncio.

------------------------------------------------------------------------

# 42. Teste de Criação de Anúncio

O fluxo de criação de anúncios foi testado através da interface.

Foram verificados:

``` text
Título
Descrição
Categoria
Tipo
Preço
Imagem
```

Também foram testadas as diferenças entre:

``` text
Venda
Doação
```

------------------------------------------------------------------------

# 43. Teste da Listagem de Anúncios

A listagem foi testada utilizando os registros existentes na API.

Foram verificados:

-   quantidade retornada;
-   conteúdo da página;
-   categorias;
-   filtros;
-   pesquisa;
-   tipo;
-   paginação;
-   apresentação dos cards;
-   imagens;
-   informações monetárias.

------------------------------------------------------------------------

# 44. Teste de Solicitações

O fluxo de solicitações foi validado integrado ao backend.

Foram verificadas as operações necessárias para que usuários possam
interagir com anúncios disponibilizados na plataforma.

------------------------------------------------------------------------

# 45. Testes em Ambiente Local

Durante o desenvolvimento, o frontend foi executado através do servidor
de desenvolvimento do Angular.

Comando:

``` bash
ng serve
```

Endereço padrão:

``` text
http://localhost:4200
```

Esse ambiente foi utilizado principalmente durante implementação e
diagnóstico.

------------------------------------------------------------------------

# 46. Testes em Produção

Após o deploy, os fluxos foram novamente testados utilizando a aplicação
hospedada na Vercel.

Isso foi necessário porque o funcionamento local não garante
automaticamente o funcionamento em produção.

Foram analisados fatores como:

-   URL da API;
-   comunicação HTTPS;
-   autenticação;
-   persistência;
-   tempo de resposta;
-   carregamento da instância do backend;
-   requisições HTTP;
-   respostas de erro;
-   integração com o banco remoto.

------------------------------------------------------------------------

# 47. Ferramentas de Diagnóstico

Durante o desenvolvimento foram utilizadas as ferramentas de
desenvolvedor do navegador.

Principalmente:

``` text
Console
Network
XHR
```

A aba de rede foi utilizada para identificar:

-   endpoint chamado;
-   método HTTP;
-   código de resposta;
-   tempo da requisição;
-   falhas de comunicação;
-   requisições que não estavam sendo executadas;
-   respostas da API.

Também foram utilizados logs temporários no frontend durante a
investigação de problemas.

------------------------------------------------------------------------

# 48. Problemas Encontrados Durante a Integração

O desenvolvimento do frontend exigiu diagnóstico de diferentes problemas
de integração.

Entre os principais estiveram:

## 48.1 Endpoint de cadastro

Durante os primeiros testes em produção, a interface não conseguia
concluir corretamente o cadastro.

A investigação envolveu:

-   inspeção da aba Network;
-   verificação do endpoint;
-   comparação entre ambiente local e produção;
-   análise da API publicada;
-   análise dos dados necessários no banco.

------------------------------------------------------------------------

## 48.2 Perfil padrão inexistente

O cadastro dependia da existência do perfil:

``` text
USER
```

no banco de dados.

A ausência desse registro impedia a conclusão correta do cadastro.

Após a configuração adequada do banco, o fluxo pôde ser concluído.

------------------------------------------------------------------------

## 48.3 Tempo de inicialização do backend

A instância utilizada para hospedagem da API pode entrar em estado de
inatividade.

Nesse cenário, a primeira requisição pode apresentar um tempo de
resposta consideravelmente maior.

Durante os testes, uma operação de criação de conta inicialmente
aparentou permanecer carregando indefinidamente.

A verificação do banco posteriormente confirmou que o usuário havia sido
criado.

Esse comportamento foi considerado durante a análise dos estados de
carregamento da interface.

------------------------------------------------------------------------

## 48.4 Configuração de ambiente

Foi necessário garantir que os serviços Angular utilizassem a URL
correta da API publicada em produção.

A configuração inadequada dos ambientes poderia fazer a aplicação
publicada continuar tentando acessar um backend local ou um endpoint
incorreto.

------------------------------------------------------------------------

## 48.5 Listagem sem cards

Durante a etapa final de integração, foi identificado um cenário no qual
a API indicava a existência de anúncios, mas os cards não eram
apresentados corretamente.

O diagnóstico envolveu:

-   `AdsList`;
-   `AdsService`;
-   resposta paginada;
-   template HTML;
-   propriedades do modelo `Ad`;
-   categorias;
-   imagens;
-   estados `isLoading` e `hasError`.

Esse processo fez parte da validação final da integração entre os dados
retornados pela API e a renderização Angular.

------------------------------------------------------------------------

# 49. Testes de Build

Antes da publicação definitiva, o frontend deve ser validado através do
processo de build.

Comando:

``` bash
ng build
```

O objetivo é confirmar que:

-   os componentes compilam;
-   os templates são válidos;
-   as dependências estão disponíveis;
-   não existem erros TypeScript impeditivos;
-   a versão de produção pode ser gerada.

------------------------------------------------------------------------

# 50. Fluxo Funcional Validado

O fluxo principal da aplicação pode ser representado por:

``` text
Cadastro
   |
   v
Login
   |
   v
Página inicial
   |
   +----------------------+
   |                      |
   v                      v
Explorar              Anunciar item
   |                      |
   v                      v
Filtros               Categoria
Pesquisa              Venda/Doação
   |                  Preço/Imagem
   v                      |
Detalhes                  v
   |                   Publicar
   v                      |
Solicitação                v
                      Meus anúncios
```

Esse fluxo representa o núcleo funcional do frontend.

------------------------------------------------------------------------

# 51. Integração Completa da Aplicação

A solução final não depende de um único processo local.

Os componentes são executados em serviços independentes:

``` text
┌──────────────────────────┐
│        USUÁRIO           │
│       Navegador          │
└────────────┬─────────────┘
             |
             | HTTPS
             v
┌──────────────────────────┐
│         VERCEL           │
│    Frontend Angular      │
└────────────┬─────────────┘
             |
             | REST / JSON
             v
┌──────────────────────────┐
│         RENDER           │
│   API Spring Boot        │
└────────────┬─────────────┘
             |
             | JDBC
             v
┌──────────────────────────┐
│       TiDB CLOUD         │
│     Banco de dados       │
└──────────────────────────┘
```

Essa arquitetura permite demonstrar o funcionamento completo de uma
aplicação web utilizando frontend, backend e banco de dados publicados.

------------------------------------------------------------------------

# 52. Requisitos Funcionais Atendidos pelo Frontend

Considerando as funcionalidades implementadas durante o desenvolvimento,
o frontend oferece suporte aos seguintes requisitos funcionais:

  Requisito                      Implementação no frontend
  ------------------------------ ------------------------------------
  Cadastro de usuário            Tela e integração com API
  Login                          Tela e integração com autenticação
  Sessão autenticada             Integração utilizando token
  Navegação                      Angular Router
  Visualização de anúncios       Página de exploração
  Pesquisa                       Busca por título
  Filtro por categoria           Implementado
  Filtro por tipo                Venda e doação
  Categorias dinâmicas           Obtidas através da API
  Publicação de item             Formulário de anúncio
  Venda                          Suportada
  Doação                         Suportada
  Preço                          Tratado para anúncios de venda
  Imagens                        Suporte a imagem principal
  Meus anúncios                  Área específica
  Detalhes de anúncio            Rota individual
  Solicitações                   Interface integrada ao fluxo
  Perfil                         Área autenticada
  Logout                         Disponível no cabeçalho
  Paginação                      Implementada na exploração
  Estados de carregamento        Implementados
  Tratamento de erro             Implementado
  Resultado vazio                Implementado
  Deploy                         Vercel
  Integração com API publicada   Implementada

------------------------------------------------------------------------

# 53. Requisitos Não Funcionais

Além das funcionalidades, o desenvolvimento procurou atender
características importantes para a qualidade da aplicação.

## 53.1 Usabilidade

A interface utiliza navegação direta, identificação clara das ações e
feedback visual durante operações assíncronas.

## 53.2 Organização

Componentes, modelos e serviços foram separados de acordo com suas
responsabilidades.

## 53.3 Manutenibilidade

A separação entre componentes e serviços reduz o acoplamento entre
interface e comunicação HTTP.

## 53.4 Escalabilidade de código

A organização por funcionalidades permite adicionar novos módulos sem
concentrar toda a aplicação em poucos arquivos.

## 53.5 Integração

O frontend foi desenvolvido para operar com uma API REST independente.

## 53.6 Disponibilidade externa

A publicação na Vercel permite acesso ao frontend sem necessidade de
executar o Angular localmente.

------------------------------------------------------------------------

# 54. Execução Local

## 54.1 Pré-requisitos

É necessário possuir:

``` text
Node.js
npm
Angular CLI
```

## 54.2 Instalar dependências

Dentro da pasta do frontend:

``` bash
npm install
```

## 54.3 Executar

``` bash
ng serve
```

ou:

``` bash
npm start
```

quando o script correspondente estiver configurado no `package.json`.

A aplicação estará normalmente disponível em:

``` text
http://localhost:4200
```

------------------------------------------------------------------------

# 55. Build de Produção

Para gerar uma versão de produção:

``` bash
ng build
```

Os arquivos gerados podem então ser utilizados pela plataforma de
hospedagem.

------------------------------------------------------------------------

# 56. Deploy do Frontend

O deploy foi realizado utilizando integração entre o repositório do
projeto e a Vercel.

Fluxo:

``` text
Alteração no frontend
        |
        v
Commit
        |
        v
Push para GitHub
        |
        v
Vercel
        |
        v
Build
        |
        v
Deploy
```

Isso facilita a atualização da versão publicada conforme novas
alterações são integradas ao repositório.

------------------------------------------------------------------------

# 57. Validação Final Recomendada

Antes da entrega definitiva, deve ser realizado um teste completo
utilizando exclusivamente os serviços publicados.

O fluxo recomendado é:

1.  abrir o frontend publicado;
2.  criar uma nova conta;
3.  realizar login;
4.  acessar a página inicial;
5.  acessar a página de exploração;
6.  testar pesquisa;
7.  testar filtro por categoria;
8.  testar filtro por tipo;
9.  criar um anúncio de venda;
10. criar um anúncio de doação;
11. verificar a exibição dos anúncios;
12. acessar os detalhes de um anúncio;
13. verificar Meus Anúncios;
14. realizar o fluxo de solicitação utilizando outro usuário, quando
    necessário;
15. verificar a área de solicitações;
16. verificar o perfil;
17. realizar logout;
18. atualizar a página e verificar o comportamento da sessão;
19. testar a aplicação em uma largura de tela reduzida;
20. verificar o console do navegador em busca de erros inesperados.

------------------------------------------------------------------------

# 58. Evidências Recomendadas para o Relatório

Para documentar o funcionamento do frontend no relatório ou diário de
bordo, recomenda-se registrar capturas das seguintes telas:

1.  página inicial;
2.  cadastro;
3.  login;
4.  exploração de anúncios;
5.  filtros;
6.  formulário de criação de anúncio;
7.  anúncio publicado;
8.  Meus Anúncios;
9.  detalhes do anúncio;
10. solicitações;
11. perfil;
12. frontend publicado na Vercel;
13. requisição bem-sucedida na aba Network;
14. registro persistido no banco após operação realizada pelo frontend.

Essas evidências demonstram não apenas a existência das telas, mas a
integração real entre as diferentes camadas da aplicação.

------------------------------------------------------------------------

# 59. Critérios Técnicos Demonstrados

O desenvolvimento do frontend demonstra a aplicação prática de conceitos
de desenvolvimento web moderno, incluindo:

-   desenvolvimento baseado em componentes;
-   TypeScript;
-   Angular;
-   roteamento SPA;
-   comunicação HTTP;
-   consumo de API REST;
-   autenticação JWT;
-   gerenciamento de estado;
-   programação reativa;
-   formulários;
-   validação;
-   paginação;
-   filtros;
-   tratamento de erros;
-   integração frontend/backend;
-   integração com banco remoto;
-   configuração de ambientes;
-   deploy em nuvem;
-   diagnóstico através das ferramentas do navegador;
-   versionamento com Git.

------------------------------------------------------------------------

# 60. Diferencial de Deploy

Além da execução local, o frontend foi disponibilizado em um serviço
real de hospedagem.

A utilização conjunta de:

``` text
Vercel
Render
TiDB Cloud
```

permite que a aplicação funcione através da Internet sem depender do
ambiente de desenvolvimento dos integrantes do projeto.

Essa característica demonstra o funcionamento integrado das três
principais camadas:

``` text
Interface
API
Persistência
```

------------------------------------------------------------------------

# 61. Status do Frontend

O frontend encontra-se em etapa final de integração e validação.

As principais funcionalidades da aplicação foram implementadas,
incluindo:

-   autenticação;
-   cadastro;
-   navegação;
-   categorias;
-   anúncios;
-   venda;
-   doação;
-   filtros;
-   pesquisa;
-   paginação;
-   gerenciamento dos próprios anúncios;
-   solicitações;
-   perfil;
-   comunicação com a API;
-   deploy.

A etapa final consiste principalmente na validação completa da
apresentação dos anúncios e imagens, revisão visual, execução dos testes
finais e registro das evidências para entrega.

------------------------------------------------------------------------

# 62. Considerações Finais

O frontend do Segunda Chance foi desenvolvido não apenas como uma
coleção de telas, mas como uma aplicação Angular integrada a uma
infraestrutura real.

O sistema conecta:

``` text
Angular
   |
   v
API REST Spring Boot
   |
   v
TiDB
```

e possui implantação independente através de serviços de nuvem.

A interface procura representar a proposta central do projeto: facilitar
a circulação de objetos dentro da comunidade acadêmica, permitindo que
itens ainda úteis recebam uma segunda chance através da venda ou da
doação.

O resultado demonstra conhecimentos de desenvolvimento frontend,
integração de sistemas, consumo de APIs, autenticação, tratamento de
dados, deploy e diagnóstico de aplicações web.

------------------------------------------------------------------------

# 63. Links

## Frontend

``` text
https://segunda-chance-flax.vercel.app
```

## API

``` text
https://segunda-chance-api.onrender.com
```

## Repositório

Adicionar o endereço definitivo do repositório GitHub utilizado para a
entrega.

------------------------------------------------------------------------

# 64. Observação sobre a Documentação Geral

Este documento descreve especificamente a camada de **frontend** do
Segunda Chance.

A documentação completa do projeto deve ser complementada por:

I. documentação do backend;

II. documentação do banco de dados;

III. arquitetura completa;

IV. endpoints da API;

V. diário de bordo;

VI. evidências de testes;

VII. histórico de desenvolvimento;

VIII. divisão das atividades da equipe;

IX. dificuldades encontradas e soluções adotadas;

X. vídeo demonstrativo e demais materiais exigidos para a entrega.

------------------------------------------------------------------------

# PARTE III --- INTEGRAÇÃO E ENTREGA

## Arquitetura Final

``` text
Navegador
   |
   v
Vercel / Angular
   |
   | REST + JSON + JWT
   v
Render / Spring Boot
   |
   | JDBC + TLS
   v
TiDB Cloud
```

## Links de Produção

Frontend:

``` text
https://segunda-chance-flax.vercel.app
```

Backend:

``` text
https://segunda-chance-api.onrender.com
```

Banco:

``` text
TiDB Cloud
Instância: segunda-chance-db
Database: segundachance
```

Adicionar ao documento final a URL definitiva do repositório GitHub.

## Escopo deste README

Este README reúne a documentação técnica do produto: objetivo,
arquitetura, frontend, backend, banco, autenticação, funcionalidades,
tecnologias, testes, execução, Docker, deploy, integração, dificuldades,
soluções e evidências.

O **diário de bordo deve permanecer separado**, conforme solicitado, e
deve registrar a evolução cronológica, datas, atividades, divisão do
trabalho e histórico de desenvolvimento.

## Observação sobre o edital

Este documento consolida os requisitos técnicos que estão sustentados
pelo material e pelo desenvolvimento realizado. Caso o edital oficial
exija campos formais adicionais --- como identificação completa da
equipe, turma, professor, vídeo demonstrativo, licença ou algum formato
específico --- esses itens devem ser adicionados conforme o texto
oficial. Nenhum requisito não fornecido foi inventado.

## Considerações Finais

O Segunda Chance foi levado de um ambiente local a uma arquitetura
publicada e distribuída. O Angular fornece a interface; o Spring Boot
centraliza regras, segurança e API; o TiDB mantém a persistência; Vercel
e Render disponibilizam as aplicações na Internet.

A solução demonstra integração real entre frontend, API REST,
autenticação JWT, banco remoto, Docker, configuração de ambientes,
versionamento e deploy em nuvem.
