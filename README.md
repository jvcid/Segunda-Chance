segunda-feira 08:23
quando eu crio um projeto novo com spring, o que eu devo configurar? quero que voce me forneça o passo a passo

mas o que eu devo configurar aqui agora? lembrando que eu preciso das dependencias de json, JWT, criptografia, etc

segunda-feira 09:52
onde eu consigo criar o DER?

assim?

assim?


qual a forma correta de escreverx?

o que eu devo colocar aqui?

assim?

segunda_chance.sql
Arquivo
eu ja tenho o arquivo do schema

onde eu coloco ele como padrao?

Porque quando eu baixei não veio com todas os packeges q criei?


Código colado.json
Arquivo

onde fica esse jsson?

ainda falta criarmos o controller e o request


LABORATORIO VORTEX.pdf
PDF
como estamos? voce considera que eu estou mais destacado?

ainda sim


assim?

onde?

mas é que tipo de configuração?

Texto colado.txt
Documento

Texto colado(1).txt
Documento
falta o que para completarmos 100% do back e partir para o angula?

package com.unifor.segundachance.service;

import com.unifor.segundachance.dto.request.LoginRequestDTO;
import com.unifor.segundachance.dto.response.LoginResponseDTO;
import com.unifor.segundachance.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(
            AuthenticationManager authenticationManager,
            JwtService jwtService
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public LoginResponseDTO login(LoginRequestDTO dto) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(),
                        dto.getPassword()
                )
        );

        UserDetails userDetails =
                (UserDetails) authentication.getPrincipal();

        String token = jwtService.generateToken(userDetails);

        return new LoginResponseDTO(token);
    }
}

{
  "name": "Usuário JWT",
  "email": "jwt@unifor.br",
  "password": "senha123",
  "roleId": 1
}

{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqd3RAdW5pZm9yLmJyIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTc4NTk5NjEyNywiZXhwIjoxNzg2MDgyNTI3fQ.V72M-Iu4gNDfRUz9mPneHuPpONXVIv5BLGdL2A5frYg",
    "type": "Bearer"
}

crie uma requisição pra mim

v



 :: Spring Boot ::                (v4.1.0)

2026-08-06T03:21:44.770-03:00  INFO 15808 --- [  restartedMain] c.u.s.SegundachanceApplication           : Starting SegundachanceApplication using Java 25.0.2 with PID 15808 (C:\Users\marti\OneDrive\Documents\trabalhos\autorais\segundachance\segundachance\target\classes started by marti in C:\Users\marti\OneDrive\Documents\trabalhos\autorais\segundachance\segundachance)
2026-08-06T03:21:44.773-03:00  INFO 15808 --- [  restartedMain] c.u.s.SegundachanceApplication           : No active profile set, falling back to 1 default profile: "default"
2026-08-06T03:21:44.835-03:00  INFO 15808 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
2026-08-06T03:21:44.835-03:00  INFO 15808 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
2026-08-06T03:21:45.591-03:00  INFO 15808 --- [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2026-08-06T03:21:45.674-03:00  INFO 15808 --- [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 74 ms. Found 6 JPA repository interfaces.
2026-08-06T03:21:46.258-03:00  INFO 15808 --- [  restartedMain] o.s.boot.tomcat.TomcatWebServer          : Tomcat initialized with port 8080 (http)
2026-08-06T03:21:46.271-03:00  INFO 15808 --- [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2026-08-06T03:21:46.271-03:00  INFO 15808 --- [  restartedMain] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/11.0.22]
2026-08-06T03:21:46.318-03:00  INFO 15808 --- [  restartedMain] b.w.c.s.WebApplicationContextInitializer : Root WebApplicationContext: initialization completed in 1481 ms
2026-08-06T03:21:46.485-03:00  INFO 15808 --- [  restartedMain] org.hibernate.orm.jpa                    : HHH008540: Processing PersistenceUnitInfo [name: default]
2026-08-06T03:21:46.535-03:00  INFO 15808 --- [  restartedMain] org.hibernate.orm.core                   : HHH000001: Hibernate ORM core version 7.4.1.Final
2026-08-06T03:21:47.003-03:00  INFO 15808 --- [  restartedMain] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
2026-08-06T03:21:47.045-03:00  INFO 15808 --- [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2026-08-06T03:21:47.178-03:00  INFO 15808 --- [  restartedMain] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection com.mysql.cj.jdbc.ConnectionImpl@1b9d0b2c
2026-08-06T03:21:47.179-03:00  INFO 15808 --- [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2026-08-06T03:21:47.244-03:00  INFO 15808 --- [  restartedMain] org.hibernate.orm.connections.pooling    : HHH10001005: Database info:
	Database JDBC URL [jdbc:mysql://localhost:3395/segundachance?serverTimezone=America/Fortaleza&useSSL=false&allowPublicKeyRetrieval=true]
	Database driver: MySQL Connector/J
	Database dialect: MySQLDialect
	Database version: 8.0.46
	Default catalog/schema: segundachance/undefined
	Autocommit mode: undefined/unknown
	Isolation level: REPEATABLE_READ [default REPEATABLE_READ]
	JDBC fetch size: none
	Pool: DataSourceConnectionProvider
	Minimum pool size: undefined/unknown
	Maximum pool size: undefined/unknown
2026-08-06T03:21:48.203-03:00  INFO 15808 --- [  restartedMain] org.hibernate.orm.core                   : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
2026-08-06T03:21:48.250-03:00  INFO 15808 --- [  restartedMain] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2026-08-06T03:21:48.309-03:00  INFO 15808 --- [  restartedMain] o.s.d.j.r.query.QueryEnhancerFactories   : Hibernate is in classpath; If applicable, HQL parser will be used.
2026-08-06T03:21:48.574-03:00  INFO 15808 --- [  restartedMain] eAuthenticationProviderManagerConfigurer : Global AuthenticationManager configured with AuthenticationProvider bean with name authenticationProvider
2026-08-06T03:21:48.574-03:00  WARN 15808 --- [  restartedMain] r$InitializeUserDetailsManagerConfigurer : Global AuthenticationManager configured with an AuthenticationProvider bean. UserDetailsService beans will not be used by Spring Security for automatically configuring username/password login. Consider removing the AuthenticationProvider bean. Alternatively, consider using the UserDetailsService in a manually instantiated DaoAuthenticationProvider. If the current configuration is intentional, to turn off this warning, increase the logging level of 'org.springframework.security.config.annotation.authentication.configuration.InitializeUserDetailsBeanManagerConfigurer' to ERROR
2026-08-06T03:21:49.143-03:00  INFO 15808 --- [  restartedMain] o.s.boot.tomcat.TomcatWebServer          : Tomcat started on port 8080 (http) with context path '/'
2026-08-06T03:21:49.148-03:00  INFO 15808 --- [  restartedMain] c.u.s.SegundachanceApplication           : Started SegundachanceApplication in 4.848 seconds (process running for 5.38)
2026-08-06T03:22:19.887-03:00  INFO 15808 --- [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2026-08-06T03:22:19.887-03:00  INFO 15808 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2026-08-06T03:22:19.889-03:00  INFO 15808 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 2 ms
=== JWT FILTER ===
URI: /api/anuncios
Authorization: null
Header ausente ou inválido

{
  "name": "asdasd",
  "email": "jwtasasdasd@dfor.br",
  "password": "senha123",
  "roleId": 1
}

e agora




{
    "id": 2,
    "titulo": "Notebook usado",
    "descricao": "Notebook funcionando perfeitamente",
    "tipo": "VENDA",
    "preco": 1120.00,
    "status": "DISPONIVEL",
    "createdAt": "2026-08-06T10:29:11",
    "categoryId": 2,
    "categoryName": "Eletrônicos",
    "userId": 4,
    "userName": "Usuário JWT"
}

to tentando criar outro user pra fazer os testes mas não to conseguindo



        roles r1_0 
    where
        r1_0.perfil_id=?
2026-08-06T10:52:12.015-03:00  WARN 2092 --- [nio-8080-exec-1] .m.m.a.ExceptionHandlerExceptionResolver : Resolved [org.springframework.web.servlet.resource.NoResourceFoundException: No static resource api/solicitacoes/1/aprovar for request '/api/solicitacoes/1/aprovar'.] eu ja testei com todos os users

eu posso so sair testando os tokens que tenho salvo?



Texto colado(2).txt
Documento
PS C:\Windows\system32>
PS C:\Windows\system32> $token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqd3RAdW5pZm9yLmJyIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTc4NjAyNzc5OSwiZXhwIjoxNzg2MTE0MTk5fQ.vcixm4M0n_oIP3zHuQclvQ4hbPiavMD5dNwHUe4NDjs"
PS C:\Windows\system32> $body = @{
>>     titulo = "Cadeira de escritório"
>>     descricao = "Cadeira em ótimo estado, com regulagem de altura."
>>     tipo = "VENDA"
>>     preco = 250.00
>>     categoryId = 2
>> } | ConvertTo-Json
>>
>> Invoke-RestMethod 
>>     -Method POST 
>>     -Uri "http://localhost:8080/api/anuncios" 
>>     -Headers @{
>>         Authorization = "Bearer $token"
>>     } 
>>     -ContentType "application/json" 
>>     -Body $body
Invoke-RestMethod : O servidor remoto retornou um erro: (500) Erro Interno do Servidor.
No linha:9 caractere:1
+ Invoke-RestMethod 
+ ~~~~~~~~~~~~~~~~~~~
    + CategoryInfo          : InvalidOperation: (System.Net.HttpWebRequest:HttpWebRequest) [Invoke-RestMethod], WebExc
   eption
    + FullyQualifiedErrorId : WebCmdletWebResponseException,Microsoft.PowerShell.Commands.InvokeRestMethodCommand

2026-08-06T11:58:33.657-03:00  INFO 7792 --- [  restartedMain] org.hibernate.orm.core                   : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
2026-08-06T11:58:33.706-03:00  INFO 7792 --- [  restartedMain] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2026-08-06T11:58:33.773-03:00  INFO 7792 --- [  restartedMain] o.s.d.j.r.query.QueryEnhancerFactories   : Hibernate is in classpath; If applicable, HQL parser will be used.
2026-08-06T11:58:34.079-03:00  INFO 7792 --- [  restartedMain] eAuthenticationProviderManagerConfigurer : Global AuthenticationManager configured with AuthenticationProvider bean with name authenticationProvider
2026-08-06T11:58:34.080-03:00  WARN 7792 --- [  restartedMain] r$InitializeUserDetailsManagerConfigurer : Global AuthenticationManager configured with an AuthenticationProvider bean. UserDetailsService beans will not be used by Spring Security for automatically configuring username/password login. Consider removing the AuthenticationProvider bean. Alternatively, consider using the UserDetailsService in a manually instantiated DaoAuthenticationProvider. If the current configuration is intentional, to turn off this warning, increase the logging level of 'org.springframework.security.config.annotation.authentication.configuration.InitializeUserDetailsBeanManagerConfigurer' to ERROR
2026-08-06T11:58:34.700-03:00  INFO 7792 --- [  restartedMain] o.s.boot.tomcat.TomcatWebServer          : Tomcat started on port 8080 (http) with context path '/'
2026-08-06T11:58:34.705-03:00  INFO 7792 --- [  restartedMain] c.u.s.SegundachanceApplication           : Started SegundachanceApplication in 4.79 seconds (process running for 5.319)
2026-08-06T11:58:40.924-03:00  INFO 7792 --- [nio-8080-exec-2] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2026-08-06T11:58:40.924-03:00  INFO 7792 --- [nio-8080-exec-2] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2026-08-06T11:58:40.926-03:00  INFO 7792 --- [nio-8080-exec-2] o.s.web.servlet.DispatcherServlet        : Completed initialization in 2 ms

Database JDBC URL [jdbc:mysql://localhost:3395/segundachance?serverTimezone=America/Fortaleza&useSSL=false&allowPublicKeyRetrieval=true]
	Database driver: MySQL Connector/J
	Database dialect: MySQLDialect
	Database version: 8.0.46
	Default catalog/schema: segundachance/undefined
	Autocommit mode: undefined/unknown
	Isolation level: REPEATABLE_READ [default REPEATABLE_READ]
	JDBC fetch size: none
	Pool: DataSourceConnectionProvider
	Minimum pool size: undefined/unknown
	Maximum pool size: undefined/unknown
2026-08-06T11:58:33.657-03:00  INFO 7792 --- [  restartedMain] org.hibernate.orm.core                   : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
2026-08-06T11:58:33.706-03:00  INFO 7792 --- [  restartedMain] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2026-08-06T11:58:33.773-03:00  INFO 7792 --- [  restartedMain] o.s.d.j.r.query.QueryEnhancerFactories   : Hibernate is in classpath; If applicable, HQL parser will be used.
2026-08-06T11:58:34.079-03:00  INFO 7792 --- [  restartedMain] eAuthenticationProviderManagerConfigurer : Global AuthenticationManager configured with AuthenticationProvider bean with name authenticationProvider
2026-08-06T11:58:34.080-03:00  WARN 7792 --- [  restartedMain] r$InitializeUserDetailsManagerConfigurer : Global AuthenticationManager configured with an AuthenticationProvider bean. UserDetailsService beans will not be used by Spring Security for automatically configuring username/password login. Consider removing the AuthenticationProvider bean. Alternatively, consider using the UserDetailsService in a manually instantiated DaoAuthenticationProvider. If the current configuration is intentional, to turn off this warning, increase the logging level of 'org.springframework.security.config.annotation.authentication.configuration.InitializeUserDetailsBeanManagerConfigurer' to ERROR
2026-08-06T11:58:34.700-03:00  INFO 7792 --- [  restartedMain] o.s.boot.tomcat.TomcatWebServer          : Tomcat started on port 8080 (http) with context path '/'
2026-08-06T11:58:34.705-03:00  INFO 7792 --- [  restartedMain] c.u.s.SegundachanceApplication           : Started SegundachanceApplication in 4.79 seconds (process running for 5.319)
2026-08-06T11:58:40.924-03:00  INFO 7792 --- [nio-8080-exec-2] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2026-08-06T11:58:40.924-03:00  INFO 7792 --- [nio-8080-exec-2] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2026-08-06T11:58:40.926-03:00  INFO 7792 --- [nio-8080-exec-2] o.s.web.servlet.DispatcherServlet        : Completed initialization in 2 ms

nenhum anuncio aparece mais

LABORATORIO VORTEX(1).pdf
PDF
considerando os requisitos, o backend supre bem?

Sim. Considerando exatamente o edital, o seu backend supre muito bem os requisitos e ultrapassa bastante o mínimo exigido.
Comparação com os requisitos obrigatórios

O edital exige uma API REST estruturada em qualquer tecnologia adequada, endpoints CRUD de anúncios, persistência funcional e comunicação estritamente em JSON.
API REST estruturada

Você implementou:

    Spring Boot;

    arquitetura em camadas;

    controllers;

    services;

    repositories;

    DTOs de entrada e saída;

    entidades JPA;

    tratamento global de exceções.

Portanto, esse requisito está completamente atendido.
CRUD de anúncios

O mínimo pede criar, listar, filtrar e deletar anúncios.

Seu backend já possui:

POST   /api/anuncios
GET    /api/anuncios
GET    /api/anuncios/{id}
PUT    /api/anuncios/{id}
DELETE /api/anuncios/{id}

Além disso, você implementou:

    associação automática com o usuário autenticado;

    categoria;

    venda e doação;

    preço condicional;

    status DISPONIVEL, RESERVADO e FINALIZADO;

    bloqueio para edição e exclusão por outro usuário.

O único ponto literal que ainda merece implementação é o filtro de anúncios, porque o edital usa expressamente a palavra “filtrar”. Atualmente você lista, busca por ID, cria, altera e exclui, mas ainda não confirmou filtros por categoria, tipo ou título.

Eu adicionaria pelo menos:

GET /api/anuncios?categoryId=2
GET /api/anuncios?tipo=VENDA
GET /api/anuncios?status=DISPONIVEL
GET /api/anuncios?titulo=notebook

Esse é o principal item restante para afirmar que o requisito de CRUD de anúncios foi cumprido literalmente em sua totalidade.
Persistência de dados

O edital aceitaria até SQLite ou armazenamento em memória.

Você está usando:

    MySQL real;

    Hibernate/JPA;

    relacionamentos entre tabelas;

    validação do schema;

    persistência de usuários, anúncios, categorias, imagens e solicitações.

Isso excede claramente o mínimo e atende também ao diferencial de banco real.
Entrada e saída em JSON

Todos os seus controllers recebem e retornam DTOs em JSON.

Você também já confirmou respostas como:

{
  "id": 4,
  "titulo": "Cadeira de escritorio",
  "status": "DISPONIVEL",
  "userId": 4,
  "userName": "Usuário JWT"
}

Esse requisito está completamente atendido.
Requisitos bônus do backend

O edital considera como diferenciais autenticação JWT, tratamento robusto de erros, validações e banco real.

Você implementou todos eles.
Autenticação

Você possui:

    cadastro de usuários;

    BCrypt;

    login;

    geração de JWT;

    validação do token;

    filtro JWT;

    aplicação stateless;

    autoridades como ROLE_USER;

    identificação do usuário pelo token.

Além disso, eliminou o envio inseguro de userId na criação de anúncios e solicitações.

Isso está acima do que o edital chama de “autenticação básica”.
Validação e tratamento de erros

Você já implementou:

    campos obrigatórios;

    limites de tamanho;

    validação de e-mail;

    preço não negativo;

    preço obrigatório para venda;

    e-mail duplicado;

    solicitação duplicada;

    recurso não encontrado;

    tentativa de agir em nome de outro usuário;

    tentativa de solicitar anúncio próprio;

    alteração de solicitações não pendentes;

    códigos 400, 403, 404, 409 e 500.

Também identificamos que credenciais inválidas ainda precisam ficar definitivamente mapeadas para 401, e JSON malformado deve retornar 400, não 500. São ajustes pequenos, mas importantes para a versão final.
Banco real

Você usa MySQL 8, enquanto o edital coloca banco relacional real como diferencial. Portanto, esse bônus está atendido.
Funcionalidades além do edital

O edital pede essencialmente gerenciamento de anúncios. Seu backend também possui:

    CRUD de categorias;

    CRUD de usuários;

    imagens associadas aos anúncios;

    sistema de solicitações;

    prevenção de solicitação duplicada;

    bloqueio de solicitação do próprio item;

    aprovação e rejeição;

    reserva automática do anúncio;

    rejeição automática de outras solicitações pendentes;

    proteção por propriedade do recurso.

Esses recursos ajudam bastante na demonstração de arquitetura e solução de problemas, que são critérios centrais do processo. O edital afirma que será avaliada a capacidade de arquitetar soluções e entregar um produto funcional, não apenas escrever código.
O que falta para considerar o backend encerrado

Eu fecharia estes pontos:

    Filtros de anúncios, pois aparecem explicitamente no requisito obrigatório.

    GET /api/users/me, importante para o Angular identificar o usuário conectado.

    POST /api/auth/register, evitando deixar o CRUD geral de usuários público.

    CORS para http://localhost:4200.

    Respostas JSON definitivas para 401 e 403.

    Corrigir JSON inválido para retornar 400.

    Revisar quais rotas são públicas.

    Remover logs temporários e printStackTrace.

    Documentar todas as rotas no README.

    Opcionalmente adicionar paginação e ordenação.

Avaliação objetiva
Critério do backend	Situação
API REST estruturada	Atendido
CRUD de anúncios	Atendido, faltando consolidar filtros
Persistência funcional	Superado
JSON	Atendido
JWT	Diferencial atendido
Validações	Diferencial atendido
Tratamento de erros	Atendido, com pequenos ajustes
Banco real	Diferencial atendido
Regras de autorização	Acima do exigido
Fluxo de solicitações	Acima do exigido

Eu avaliaria o backend atual como:

    100% dos requisitos mínimos estruturais;

    aproximadamente 90% a 95% do backend que planejamos;

    acima do nível obrigatório do edital;

    forte nos diferenciais técnicos.

O risco principal da entrega não está mais no backend. O edital também exige frontend responsivo e PWA instalável, com manifesto e Service Worker; isso é obrigatório, não bônus. Portanto, depois dos ajustes listados, o foco deve mudar rapidamente para Angular, responsividade e PWA.

2026-08-06T12:19:18.771-03:00  INFO 7648 --- [  restartedMain] org.hibernate.orm.core                   : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
2026-08-06T12:19:18.818-03:00  INFO 7648 --- [  restartedMain] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2026-08-06T12:19:18.884-03:00  INFO 7648 --- [  restartedMain] o.s.d.j.r.query.QueryEnhancerFactories   : Hibernate is in classpath; If applicable, HQL parser will be used.
2026-08-06T12:19:19.193-03:00  INFO 7648 --- [  restartedMain] eAuthenticationProviderManagerConfigurer : Global AuthenticationManager configured with AuthenticationProvider bean with name authenticationProvider
2026-08-06T12:19:19.194-03:00  WARN 7648 --- [  restartedMain] r$InitializeUserDetailsManagerConfigurer : Global AuthenticationManager configured with an AuthenticationProvider bean. UserDetailsService beans will not be used by Spring Security for automatically configuring username/password login. Consider removing the AuthenticationProvider bean. Alternatively, consider using the UserDetailsService in a manually instantiated DaoAuthenticationProvider. If the current configuration is intentional, to turn off this warning, increase the logging level of 'org.springframework.security.config.annotation.authentication.configuration.InitializeUserDetailsBeanManagerConfigurer' to ERROR
2026-08-06T12:19:19.979-03:00  INFO 7648 --- [  restartedMain] o.s.boot.tomcat.TomcatWebServer          : Tomcat started on port 8080 (http) with context path '/'
2026-08-06T12:19:19.986-03:00  INFO 7648 --- [  restartedMain] c.u.s.SegundachanceApplication           : Started SegundachanceApplication in 4.925 seconds (process running for 5.495)

package com.unifor.segundachance.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unifor.segundachance.exception.ApiError;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    public CustomAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException, ServletException {

        ApiError error = new ApiError(
                HttpStatus.UNAUTHORIZED.value(),
                "Unauthorized",
                "É necessário estar autenticado para acessar este recurso."
        );

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        objectMapper.writeValue(response.getOutputStream(), error);
    }
}


{
    "status": 401,
    "error": "Unauthorized",
    "message": "É necessário estar autenticado para acessar este recurso.",
    "timestamp": "2026-08-06T12:37:29.166632"
}

quinta-feira 15:27

ontem 01:34
como é que eu configuro pro novo pc

consegui rodar, mas ta tudo certo?


{
    "status": 401,
    "error": "Unauthorized",
    "message": "E-mail ou senha inválidos",
    "timestamp": "2026-08-07T01:57:06.9676877"
}

{

"id": 9,

"name": "Teste Proprietario",

"email": "proprietario@teste.com",

"roleId": 1,

"roleName": "USER"

}

{

"id": 10,

"name": "Teste Interessado",

"email": "interessado@teste.com",

"roleId": 1,

"roleName": "USER"

}

{
    "status": 401,
    "error": "Unauthorized",
    "message": "É necessário estar autenticado para acessar este recurso.",
    "timestamp": "2026-08-07T02:00:31.5899552"
}

{
    "id": 5,
    "name": "Teste Backend"
}

[
    {
        "id": 2,
        "name": "Eletrônicos"
    },
    {
        "id": 3,
        "name": "João Silva"
    },
    {
        "id": 1,
        "name": "Livros"
    },
    {
        "id": 4,
        "name": "rogerio ceni"
    },
    {
        "id": 5,
        "name": "Teste Backend"
    }
]

{
    "status": 500,
    "error": "Internal Server Error",
    "message": "MethodArgumentTypeMismatchException: Method parameter 'id': Failed to convert value of type 'java.lang.String' to required type 'java.lang.Integer'; For input string: \"CATEGORY_ID\"",
    "timestamp": "2026-08-07T02:01:46.2513153"
}

{
    "id": 2,
    "name": "Eletrônicos"
}

{
    "id": 5,
    "titulo": "Notebook Teste QA",
    "descricao": "Notebook utilizado para a rodada final de testes do backend.",
    "tipo": "VENDA",
    "preco": 2500.00,
    "status": "DISPONIVEL",
    "createdAt": "2026-08-07T02:02:37.7036069",
    "categoryId": 2,
    "categoryName": "Eletrônicos",
    "userId": 9,
    "userName": "Teste Proprietario",
    "imagemPrincipalUrl": null

{
    "status": 400,
    "error": "Bad Request",
    "message": "Preço é obrigatório para anúncios de venda",
    "timestamp": "2026-08-07T02:03:12.1160038"
}
}

{
    "id": 6,
    "titulo": "Livros para doacao",
    "descricao": "Livros usados disponíveis gratuitamente.",
    "tipo": "DOACAO",
    "preco": null,
    "status": "DISPONIVEL",
    "createdAt": "2026-08-07T02:04:16.2788958",
    "categoryId": 2,
    "categoryName": "Eletrônicos",
    "userId": 9,
    "userName": "Teste Proprietario",
    "imagemPrincipalUrl": null
}

{

"content": [

{

"id": 6,

"titulo": "Livros para doacao",

"descricao": "Livros usados disponíveis gratuitamente.",

"tipo": "DOACAO",

"preco": null,

"status": "DISPONIVEL",

"createdAt": "2026-08-07T02:04:16",

"categoryId": 2,

"categoryName": "Eletrônicos",

"userId": 9,

"userName": "Teste Proprietario",

"imagemPrincipalUrl": null

},

{

"id": 5,

"titulo": "Notebook Teste QA",

"descricao": "Notebook utilizado para a rodada final de testes do backend.",

"tipo": "VENDA",

"preco": 2500.00,

"status": "DISPONIVEL",

"createdAt": "2026-08-07T02:02:38",

"categoryId": 2,

"categoryName": "Eletrônicos",

"userId": 9,

"userName": "Teste Proprietario",

"imagemPrincipalUrl": null

},

{

"id": 4,

"titulo": "Cadeira de escritorio",

"descricao": "Cadeira em otimo estado, com regulagem de altura.",

"tipo": "VENDA",

"preco": 250.00,

"status": "DISPONIVEL",

"createdAt": "2026-08-06T11:55:53",

"categoryId": 2,

"categoryName": "Eletrônicos",

"userId": 4,

"userName": "Usuário JWT",

"imagemPrincipalUrl": null

},

{

"id": 3,

"titulo": "Notebook usado",

"descricao": "Notebook funcionando perfeitamente",

"tipo": "VENDA",

"preco": 1800.00,

"status": "RESERVADO",

"createdAt": "2026-08-06T10:36:58",

"categoryId": 2,

"categoryName": "Eletrônicos",

"userId": 7,

"userName": "Segundo Usuário",

"imagemPrincipalUrl": null

},

{

"id": 2,

"titulo": "Notebook usado",

"descricao": "Notebook funcionando perfeitamente",

"tipo": "VENDA",

"preco": 1120.00,

"status": "DISPONIVEL",

"createdAt": "2026-08-06T10:29:11",

"categoryId": 2,

"categoryName": "Eletrônicos",

"userId": 4,

"userName": "Usuário JWT",

"imagemPrincipalUrl": null

},

{

"id": 1,

"titulo": "Calculadora Científica Casio",

"descricao": "Pouco usada, funcionando perfeitamente.",

"tipo": "VENDA",

"preco": 120.00,

"status": "DISPONIVEL",

"createdAt": "2026-08-05T13:09:37",

"categoryId": 2,

"categoryName": "Eletrônicos",

"userId": 1,

"userName": "Martin",

"imagemPrincipalUrl": "https://images.unsplash.com/photo-1545239351-1141bd82e8a6"

}

],

"empty": false,

"first": true,

"last": true,

"number": 0,

"numberOfElements": 6,

"pageable": {

"offset": 0,

"pageNumber": 0,

"pageSize": 10,

"paged": true,

"sort": {

"empty": false,

"sorted": true,

"unsorted": false

},

"unpaged": false

},

"size": 10,

"sort": {

"empty": false,

"sorted": true,

"unsorted": false

},

"totalElements": 6,

"totalPages": 1

}

{
    "id": 6,
    "titulo": "Livros para doacao",
    "descricao": "Livros usados disponíveis gratuitamente.",
    "tipo": "DOACAO",
    "preco": null,
    "status": "DISPONIVEL",
    "createdAt": "2026-08-07T02:04:16",
    "categoryId": 2,
    "categoryName": "Eletrônicos",
    "userId": 9,
    "userName": "Teste Proprietario",
    "imagemPrincipalUrl": null
}

[
    {
        "id": 6,
        "titulo": "Livros para doacao",
        "descricao": "Livros usados disponíveis gratuitamente.",
        "tipo": "DOACAO",
        "preco": null,
        "status": "DISPONIVEL",
        "createdAt": "2026-08-07T02:04:16",
        "categoryId": 2,
        "categoryName": "Eletrônicos",
        "userId": 9,
        "userName": "Teste Proprietario",
        "imagemPrincipalUrl": null
    },
    {
        "id": 5,
        "titulo": "Notebook Teste QA",
        "descricao": "Notebook utilizado para a rodada final de testes do backend.",
        "tipo": "VENDA",
        "preco": 2500.00,
        "status": "DISPONIVEL",
        "createdAt": "2026-08-07T02:02:38",
        "categoryId": 2,
        "categoryName": "Eletrônicos",
        "userId": 9,
        "userName": "Teste Proprietario",
        "imagemPrincipalUrl": null
    }
]

{
    "content": [
        {
            "id": 5,
            "titulo": "Notebook Teste QA",
            "descricao": "Notebook utilizado para a rodada final de testes do backend.",
            "tipo": "VENDA",
            "preco": 2500.00,
            "status": "DISPONIVEL",
            "createdAt": "2026-08-07T02:02:38",
            "categoryId": 2,
            "categoryName": "Eletrônicos",
            "userId": 9,
            "userName": "Teste Proprietario",
            "imagemPrincipalUrl": null
        },
        {
            "id": 3,
            "titulo": "Notebook usado",
            "descricao": "Notebook funcionando perfeitamente",
            "tipo": "VENDA",
            "preco": 1800.00,
            "status": "RESERVADO",
            "createdAt": "2026-08-06T10:36:58",
            "categoryId": 2,
            "categoryName": "Eletrônicos",
            "userId": 7,
            "userName": "Segundo Usuário",
            "imagemPrincipalUrl": null
        },
        {
            "id": 2,
            "titulo": "Notebook usado",
            "descricao": "Notebook funcionando perfeitamente",
            "tipo": "VENDA",
            "preco": 1120.00,
            "status": "DISPONIVEL",
            "createdAt": "2026-08-06T10:29:11",
            "categoryId": 2,
            "categoryName": "Eletrônicos",
            "userId": 4,
            "userName": "Usuário JWT",
            "imagemPrincipalUrl": null
        }
    ],
    "empty": false,
    "first": true,
    "last": true,
    "number": 0,
    "numberOfElements": 3,
    "pageable": {
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 10,
        "paged": true,
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "unpaged": false
    },
    "size": 10,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "totalElements": 3,
    "totalPages": 1
}

{

"content": [

{

"id": 5,

"titulo": "Notebook Teste QA",

"descricao": "Notebook utilizado para a rodada final de testes do backend.",

"tipo": "VENDA",

"preco": 2500.00,

"status": "DISPONIVEL",

"createdAt": "2026-08-07T02:02:38",

"categoryId": 2,

"categoryName": "Eletrônicos",

"userId": 9,

"userName": "Teste Proprietario",

"imagemPrincipalUrl": null

},

{

"id": 4,

"titulo": "Cadeira de escritorio",

"descricao": "Cadeira em otimo estado, com regulagem de altura.",

"tipo": "VENDA",

"preco": 250.00,

"status": "DISPONIVEL",

"createdAt": "2026-08-06T11:55:53",

"categoryId": 2,

"categoryName": "Eletrônicos",

"userId": 4,

"userName": "Usuário JWT",

"imagemPrincipalUrl": null

},

{

"id": 3,

"titulo": "Notebook usado",

"descricao": "Notebook funcionando perfeitamente",

"tipo": "VENDA",

"preco": 1800.00,

"status": "RESERVADO",

"createdAt": "2026-08-06T10:36:58",

"categoryId": 2,

"categoryName": "Eletrônicos",

"userId": 7,

"userName": "Segundo Usuário",

"imagemPrincipalUrl": null

},

{

"id": 2,

"titulo": "Notebook usado",

"descricao": "Notebook funcionando perfeitamente",

"tipo": "VENDA",

"preco": 1120.00,

"status": "DISPONIVEL",

"createdAt": "2026-08-06T10:29:11",

"categoryId": 2,

"categoryName": "Eletrônicos",

"userId": 4,

"userName": "Usuário JWT",

"imagemPrincipalUrl": null

},

{

"id": 1,

"titulo": "Calculadora Científica Casio",

"descricao": "Pouco usada, funcionando perfeitamente.",

"tipo": "VENDA",

"preco": 120.00,

"status": "DISPONIVEL",

"createdAt": "2026-08-05T13:09:37",

"categoryId": 2,

"categoryName": "Eletrônicos",

"userId": 1,

"userName": "Martin",

"imagemPrincipalUrl": "https://images.unsplash.com/photo-1545239351-1141bd82e8a6"

}

],

"empty": false,

"first": true,

"last": true,

"number": 0,

"numberOfElements": 5,

"pageable": {

"offset": 0,

"pageNumber": 0,

"pageSize": 10,

"paged": true,

"sort": {

"empty": false,

"sorted": true,

"unsorted": false

},

"unpaged": false

},

"size": 10,

"sort": {

"empty": false,

"sorted": true,

"unsorted": false

},

"totalElements": 5,

"totalPages": 1

}

{
    "content": [
        {
            "id": 6,
            "titulo": "Livros para doacao",
            "descricao": "Livros usados disponíveis gratuitamente.",
            "tipo": "DOACAO",
            "preco": null,
            "status": "DISPONIVEL",
            "createdAt": "2026-08-07T02:04:16",
            "categoryId": 2,
            "categoryName": "Eletrônicos",
            "userId": 9,
            "userName": "Teste Proprietario",
            "imagemPrincipalUrl": null
        }
    ],
    "empty": false,
    "first": true,
    "last": true,
    "number": 0,
    "numberOfElements": 1,
    "pageable": {
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 10,
        "paged": true,
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "unpaged": false
    },
    "size": 10,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "totalElements": 1,
    "totalPages": 1
}

{

"content": [

{

"id": 6,

"titulo": "Livros para doacao",

"descricao": "Livros usados disponíveis gratuitamente.",

"tipo": "DOACAO",

"preco": null,

"status": "DISPONIVEL",

"createdAt": "2026-08-07T02:04:16",

"categoryId": 2,

"categoryName": "Eletrônicos",

"userId": 9,

"userName": "Teste Proprietario",

"imagemPrincipalUrl": null

},

{

"id": 5,

"titulo": "Notebook Teste QA",

"descricao": "Notebook utilizado para a rodada final de testes do backend.",

"tipo": "VENDA",

"preco": 2500.00,

"status": "DISPONIVEL",

"createdAt": "2026-08-07T02:02:38",

"categoryId": 2,

"categoryName": "Eletrônicos",

"userId": 9,

"userName": "Teste Proprietario",

"imagemPrincipalUrl": null

},

{

"id": 4,

"titulo": "Cadeira de escritorio",

"descricao": "Cadeira em otimo estado, com regulagem de altura.",

"tipo": "VENDA",

"preco": 250.00,

"status": "DISPONIVEL",

"createdAt": "2026-08-06T11:55:53",

"categoryId": 2,

"categoryName": "Eletrônicos",

"userId": 4,

"userName": "Usuário JWT",

"imagemPrincipalUrl": null

},

{

"id": 2,

"titulo": "Notebook usado",

"descricao": "Notebook funcionando perfeitamente",

"tipo": "VENDA",

"preco": 1120.00,

"status": "DISPONIVEL",

"createdAt": "2026-08-06T10:29:11",

"categoryId": 2,

"categoryName": "Eletrônicos",

"userId": 4,

"userName": "Usuário JWT",

"imagemPrincipalUrl": null

},

{

"id": 1,

"titulo": "Calculadora Científica Casio",

"descricao": "Pouco usada, funcionando perfeitamente.",

"tipo": "VENDA",

"preco": 120.00,

"status": "DISPONIVEL",

"createdAt": "2026-08-05T13:09:37",

"categoryId": 2,

"categoryName": "Eletrônicos",

"userId": 1,

"userName": "Martin",

"imagemPrincipalUrl": "https://images.unsplash.com/photo-1545239351-1141bd82e8a6"

}

],

"empty": false,

"first": true,

"last": true,

"number": 0,

"numberOfElements": 5,

"pageable": {

"offset": 0,

"pageNumber": 0,

"pageSize": 10,

"paged": true,

"sort": {

"empty": false,

"sorted": true,

"unsorted": false

},

"unpaged": false

},

"size": 10,

"sort": {

"empty": false,

"sorted": true,

"unsorted": false

},

"totalElements": 5,

"totalPages": 1

}

{
    "content": [],
    "empty": true,
    "first": true,
    "last": true,
    "number": 0,
    "numberOfElements": 0,
    "pageable": {
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 10,
        "paged": true,
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "unpaged": false
    },
    "size": 10,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "totalElements": 0,
    "totalPages": 0
}

{
    "content": [
        {
            "id": 6,
            "titulo": "Livros para doacao",
            "descricao": "Livros usados disponíveis gratuitamente.",
            "tipo": "DOACAO",
            "preco": null,
            "status": "DISPONIVEL",
            "createdAt": "2026-08-07T02:04:16",
            "categoryId": 2,
            "categoryName": "Eletrônicos",
            "userId": 9,
            "userName": "Teste Proprietario",
            "imagemPrincipalUrl": null
        },
        {
            "id": 5,
            "titulo": "Notebook Teste QA",
            "descricao": "Notebook utilizado para a rodada final de testes do backend.",
            "tipo": "VENDA",
            "preco": 2500.00,
            "status": "DISPONIVEL",
            "createdAt": "2026-08-07T02:02:38",
            "categoryId": 2,
            "categoryName": "Eletrônicos",
            "userId": 9,
            "userName": "Teste Proprietario",
            "imagemPrincipalUrl": null
        }
    ],
    "empty": false,
    "first": true,
    "last": false,
    "number": 0,
    "numberOfElements": 2,
    "pageable": {
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 2,
        "paged": true,
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "unpaged": false
    },
    "size": 2,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "totalElements": 6,
    "totalPages": 3
}

{
    "content": [
        {
            "id": 4,
            "titulo": "Cadeira de escritorio",
            "descricao": "Cadeira em otimo estado, com regulagem de altura.",
            "tipo": "VENDA",
            "preco": 250.00,
            "status": "DISPONIVEL",
            "createdAt": "2026-08-06T11:55:53",
            "categoryId": 2,
            "categoryName": "Eletrônicos",
            "userId": 4,
            "userName": "Usuário JWT",
            "imagemPrincipalUrl": null
        },
        {
            "id": 3,
            "titulo": "Notebook usado",
            "descricao": "Notebook funcionando perfeitamente",
            "tipo": "VENDA",
            "preco": 1800.00,
            "status": "RESERVADO",
            "createdAt": "2026-08-06T10:36:58",
            "categoryId": 2,
            "categoryName": "Eletrônicos",
            "userId": 7,
            "userName": "Segundo Usuário",
            "imagemPrincipalUrl": null
        }
    ],
    "empty": false,
    "first": false,
    "last": false,
    "number": 1,
    "numberOfElements": 2,
    "pageable": {
        "offset": 2,
        "pageNumber": 1,
        "pageSize": 2,
        "paged": true,
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "unpaged": false
    },
    "size": 2,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "totalElements": 6,
    "totalPages": 3
}

{

"content": [

{

"id": 6,

"titulo": "Livros para doacao",

"descricao": "Livros usados disponíveis gratuitamente.",

"tipo": "DOACAO",

"preco": null,

"status": "DISPONIVEL",

"createdAt": "2026-08-07T02:04:16",

"categoryId": 2,

"categoryName": "Eletrônicos",

"userId": 9,

"userName": "Teste Proprietario",

"imagemPrincipalUrl": null

},

{

"id": 1,

"titulo": "Calculadora Científica Casio",

"descricao": "Pouco usada, funcionando perfeitamente.",

"tipo": "VENDA",

"preco": 120.00,

"status": "DISPONIVEL",

"createdAt": "2026-08-05T13:09:37",

"categoryId": 2,

"categoryName": "Eletrônicos",

"userId": 1,

"userName": "Martin",

"imagemPrincipalUrl": "https://images.unsplash.com/photo-1545239351-1141bd82e8a6"

},

{

"id": 4,

"titulo": "Cadeira de escritorio",

"descricao": "Cadeira em otimo estado, com regulagem de altura.",

"tipo": "VENDA",

"preco": 250.00,

"status": "DISPONIVEL",

"createdAt": "2026-08-06T11:55:53",

"categoryId": 2,

"categoryName": "Eletrônicos",

"userId": 4,

"userName": "Usuário JWT",

"imagemPrincipalUrl": null

},

{

"id": 2,

"titulo": "Notebook usado",

"descricao": "Notebook funcionando perfeitamente",

"tipo": "VENDA",

"preco": 1120.00,

"status": "DISPONIVEL",

"createdAt": "2026-08-06T10:29:11",

"categoryId": 2,

"categoryName": "Eletrônicos",

"userId": 4,

"userName": "Usuário JWT",

"imagemPrincipalUrl": null

},

{

"id": 3,

"titulo": "Notebook usado",

"descricao": "Notebook funcionando perfeitamente",

"tipo": "VENDA",

"preco": 1800.00,

"status": "RESERVADO",

"createdAt": "2026-08-06T10:36:58",

"categoryId": 2,

"categoryName": "Eletrônicos",

"userId": 7,

"userName": "Segundo Usuário",

"imagemPrincipalUrl": null

},

{

"id": 5,

"titulo": "Notebook Teste QA",

"descricao": "Notebook utilizado para a rodada final de testes do backend.",

"tipo": "VENDA",

"preco": 2500.00,

"status": "DISPONIVEL",

"createdAt": "2026-08-07T02:02:38",

"categoryId": 2,

"categoryName": "Eletrônicos",

"userId": 9,

"userName": "Teste Proprietario",

"imagemPrincipalUrl": null

}

],

"empty": false,

"first": true,

"last": true,

"number": 0,

"numberOfElements": 6,

"pageable": {

"offset": 0,

"pageNumber": 0,

"pageSize": 10,

"paged": true,

"sort": {

"empty": false,

"sorted": true,

"unsorted": false

},

"unpaged": false

},

"size": 10,

"sort": {

"empty": false,

"sorted": true,

"unsorted": false

},

"totalElements": 6,

"totalPages": 1

}

{

"id": 3,

"url": "https://example.com/imagem-alterada.jpg",

"ordem": 1,

"anuncioId": 5,

"anuncioTitulo": "Notebook Teste QA Atualizado"

}

como eu git push origin main  

a organização de arquivos ficou assim. Está coerente com o edital?

essa é a situação atual

Markdown(1).md colado
Arquivo

spring:
  datasource:
    url: jdbc:mysql://localhost:3395/segundachance?serverTimezone=America/Fortaleza&useSSL=false&allowPublicKeyRetrieval=true
    username: doppler
    password: "@Nilton026"
    driver-class-name: com.mysql.cj.jdbc.Driver

  jpa:
    open-in-view: false
    hibernate:
      ddl-auto: validate
    show-sql: true
    properties:
      hibernate:
        format_sql: true

server:
  port: 8080

jwt:
  secret: ${JWT_SECRET}
  expiration: 86400000


ontem 23:08
porque não ta indo

Texto colado(6).txt
Documento


eu criei dois arquivos diferentes


consegui fazer rodar

Markdown(2).md colado
Arquivo


Texto colado(7).txt
Documento

What's next:
    Debug this Compose error with Gordon → docker ai "help me fix this compose error"
PS C:\Users\marti\OneDrive\Documents\trabalhos\autorais\Segunda-Chance-main> docker compose ps
NAME                     IMAGE                         COMMAND                  SERVICE   CREATED         STATUS                   PORTS
segunda_chance_adminer   adminer:latest                "entrypoint.sh docke…"   adminer   2 minutes ago   Up 2 minutes             0.0.0.0:8081->8080/tcp, [::]:8081->8080/tcp
segunda_chance_backend   segunda-chance-main-backend   "java -jar app.jar"      backend   2 minutes ago   Up 2 minutes             0.0.0.0:8080->8080/tcp, [::]:8080->8080/tcp
segunda_chance_db        mysql:8.0                     "docker-entrypoint.s…"   db        2 minutes ago   Up 2 minutes (healthy)   0.0.0.0:3396->3306/tcp, [::]:3396->3306/tcp
PS C:\Users\marti\OneDrive\Documents\trabalhos\autorais\Segunda-Chance-main>




{
    "status": 404,
    "error": "Not Found",
    "message": "Anúncio não encontrado",
    "timestamp": "2026-08-08T04:29:02.068677815"
}

{
    "status": 403,
    "error": "Forbidden",
    "message": "Você não tem permissão para alterar este anúncio",
    "timestamp": "2026-08-08T04:30:08.126956407"
}


{
    "id": 7,
    "name": "Teste Final"
}

{
    "status": 403,
    "error": "Forbidden",
    "message": "Somente o proprietário do anúncio pode gerenciar suas imagens",
    "timestamp": "2026-08-08T04:40:01.283408613"
}

quais são os comandos para eu executar o springboot direto no powershell e qual a real relevancia do docker? porque aqui aparece um server rodando mas ele aparentemente não está

Texto colado(8).txt
Documento

 assim?



eu nao tenho saldo suficiente no cartao de credito, mesmo o valor sendo estornado, o meu cartao esta sendo negado








spring:
  datasource:
    url: jdbc:mysql://localhost:3395/segundachance?serverTimezone=America/Fortaleza&useSSL=false&allowPublicKeyRetrieval=true
    username: doppler
    password: "@Nilton026"
    driver-class-name: com.mysql.cj.jdbc.Driver

  jpa:
    open-in-view: false
    hibernate:
      ddl-auto: ${SPRING_JPA_HIBERNATE_DDL_AUTO:validate}
    show-sql: true
    properties:
      hibernate:
        format_sql: true

server:
  port: 8080

jwt:
  secret: ${JWT_SECRET}
  expiration: 86400000


ta demorando bastandte pra sair de building

tá demorando

agora ta dando rebuild

estou começando a ficar sem tempo




me fale todas as coisas que devem ser alteradas

ta, e o que falta?

segunda_chance(1).sql
Arquivo
eu nao posso so importar isso n?

posso excluir o antigo?


posso fazer deploy agora?

onde eu volto pra fazer aquelas correções

onde

onde eu dou retry

Markdown(5).md colado
Arquivo

ONDE EU CONECTO O TiDB ao my sql


Claro. Abaixo está um relatório já escrito para você copiar e mandar diretamente para o chat responsável pelo frontend. Ele registra as decisões tomadas, o que já foi configurado e, principalmente, o que o frontend precisa fazer para concluir a integração.
RELATÓRIO DE DEPLOY — SEGUNDA CHANCE
1. Situação geral

O projeto Segunda Chance chegou à etapa final de deploy.

A arquitetura escolhida ficou:

Frontend Angular/PWA
        │
        │ HTTPS / REST
        ▼
Backend Spring Boot
Render
        │
        │ JDBC + TLS
        ▼
TiDB Cloud
MySQL-compatible

A divisão atual é:

Frontend  → já publicado
Backend   → Render
Banco     → TiDB Cloud

O objetivo agora é concluir o deploy do backend e substituir no frontend qualquer referência à API local pela URL pública do Render.
2. Frontend

O frontend Angular já foi colocado em produção.

Portanto, não precisamos refazer o deploy do frontend do zero.

A pendência do frontend é a integração definitiva com o backend publicado.

Durante o desenvolvimento local, provavelmente existem referências semelhantes a:

http://localhost:8080

Essas referências não poderão continuar sendo utilizadas na versão publicada.

O backend recebeu no Render o endereço:

https://segunda-chance-api.onrender.com

Portanto, quando o backend estiver confirmado como operacional, o frontend deverá consumir essa URL.

Exemplo:

export const environment = {
  production: true,
  apiUrl: 'https://segunda-chance-api.onrender.com'
};

A estrutura exata deve respeitar a configuração atual do Angular do projeto.
3. Backend escolhido

O backend Spring Boot foi configurado no Render como um:

Web Service

Nome:

segunda-chance-api

Plano:

Free

Runtime:

Docker

Branch:

main

Repositório:

jvcid/Segunda-Chance

URL pública atribuída:

https://segunda-chance-api.onrender.com

Como estamos utilizando a instância gratuita, o Render informa que o serviço pode entrar em modo de inatividade e demorar aproximadamente 50 segundos ou mais na primeira requisição depois de um período sem uso.

Isso é aceitável para o objetivo atual do projeto, que é acadêmico/portfólio.
4. Banco inicialmente considerado

Inicialmente tentamos utilizar Oracle Cloud Free Tier.

A ideia era utilizar:

Angular
   ↓
Render
   ↓
VM Oracle Cloud
   ↓
MySQL

A Oracle, entretanto, exige verificação de identidade/pagamento para criação da conta.

Embora o serviço informasse que não haveria cobrança pelo Free Tier, o cartão disponível estava sendo recusado durante a verificação.

Por esse motivo, a Oracle foi descartada para evitar continuar gastando tempo com cadastro e validação financeira.
5. Segunda tentativa: Aiven

Depois tentamos utilizar o Aiven.

Foi criado um serviço MySQL:

segunda-chance-db

Plano gratuito.

O serviço, entretanto, permaneceu por tempo excessivo nos estados:

Building

e posteriormente:

Rebuilding

Sem disponibilizar o banco de forma estável no tempo necessário.

Como o prazo para finalizar o projeto estava curto, decidimos abandonar essa solução.
6. Solução definitiva para o banco

Foi escolhido o TiDB Cloud.

O TiDB é compatível com o protocolo MySQL, permitindo que o Spring Boot continue utilizando:

com.mysql.cj.jdbc.Driver

sem necessidade de migrar a aplicação para PostgreSQL ou alterar as entidades/repositories.

Foi criada a instância:

segunda-chance-db

Região escolhida:

São Paulo / sa-east-1

O endpoint disponibilizado pelo TiDB é:

gateway01.sa-east-1.prod.aws.tidbcloud.com

Porta:

4000

A conexão pública exige TLS:

sslMode=REQUIRED

7. Banco de dados criado

Inicialmente o TiDB disponibilizava bancos administrativos como:

information_schema
mysql
performance_schema
sys

Foi criado manualmente o banco da aplicação:

CREATE DATABASE IF NOT EXISTS segundachance;

Depois foi selecionado:

USE segundachance;

E validado com:

SELECT DATABASE();

Resultado confirmado:

segundachance

Portanto, neste momento está confirmado que o banco:

segundachance

existe no TiDB Cloud.
8. Conexão local com o TiDB

Houve inicialmente um problema porque o comando:

mysql

não estava disponível diretamente no PATH do Windows.

Foram encontrados os executáveis:

C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe

e:

C:\Program Files\MySQL\MySQL Workbench 8.0\mysql.exe

A conexão correta passou a ser realizada utilizando diretamente o primeiro executável:

& "C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe" `
-h gateway01.sa-east-1.prod.aws.tidbcloud.com `
-P 4000 `
-u <USUARIO_TIDB> `
-p `
--ssl-mode=REQUIRED

A conexão funcionou.

Também foi confirmado dentro dessa conexão:

USE segundachance;
SELECT DATABASE();

com resultado:

segundachance

9. Configuração do Spring Boot

O application.yaml deixou de conter credenciais locais diretamente no código.

A configuração final utilizada ficou baseada em variáveis de ambiente:

spring:
  datasource:
    url: ${SPRING_DATASOURCE_URL}
    username: ${SPRING_DATASOURCE_USERNAME}
    password: ${SPRING_DATASOURCE_PASSWORD}
    driver-class-name: com.mysql.cj.jdbc.Driver

  jpa:
    open-in-view: false
    database-platform: org.hibernate.dialect.MySQLDialect
    hibernate:
      ddl-auto: ${SPRING_JPA_HIBERNATE_DDL_AUTO:validate}
    show-sql: false
    properties:
      hibernate:
        format_sql: true

server:
  port: ${PORT:8080}

jwt:
  secret: ${JWT_SECRET}
  expiration: 86400000

Isso permite utilizar configurações diferentes em desenvolvimento e produção sem colocar senhas no GitHub.
10. Porta do backend

A porta também foi adaptada para o Render.

Antes:

server:
  port: 8080

Agora:

server:
  port: ${PORT:8080}

Isso significa:

Localmente:
PORT não existe
→ Spring utiliza 8080

Render:
PORT é fornecida pela plataforma
→ Spring utiliza a porta determinada pelo Render

Isso não significa que o frontend precise conhecer a porta interna do Render.

O frontend utilizará simplesmente:

https://segunda-chance-api.onrender.com

O Render cuida do roteamento para a porta interna.
11. Variáveis de ambiente do Render

Foram configuradas no Web Service:

SPRING_DATASOURCE_URL
SPRING_DATASOURCE_USERNAME
SPRING_DATASOURCE_PASSWORD
JWT_SECRET
SPRING_JPA_HIBERNATE_DDL_AUTO

A URL do datasource aponta para:

jdbc:mysql://gateway01.sa-east-1.prod.aws.tidbcloud.com:4000/segundachance?sslMode=REQUIRED

O usuário e a senha do TiDB ficam exclusivamente nas variáveis de ambiente do Render.

A chave JWT também não fica versionada no GitHub.

Para criação automática das tabelas nesta fase do deploy:

SPRING_JPA_HIBERNATE_DDL_AUTO=update

12. Primeiro deploy do backend

O Docker build do backend funcionou corretamente.

O Maven conseguiu compilar os arquivos Java:

Compiling 57 source files

O JAR também foi criado:

segundachance-0.0.1-SNAPSHOT.jar

E o processo terminou com:

BUILD SUCCESS

Portanto, não havia problema de compilação do backend.

O container também foi construído e enviado corretamente.

O problema aconteceu somente quando o Spring começou a inicializar a aplicação.
13. Primeiro problema encontrado em produção

O Hibernate inicialmente apresentou:

Unable to determine Dialect without JDBC metadata

Por isso adicionamos explicitamente:

database-platform: org.hibernate.dialect.MySQLDialect

Isso informa ao Hibernate que a aplicação deve utilizar o dialeto SQL compatível com MySQL.
14. Segundo problema encontrado

Depois dessa alteração, o erro ficou mais específico:

Unknown database 'segundachance'

Isso causava posteriormente erros em cascata envolvendo:

EntityManagerFactory
UserRepository
CustomUserDetailsService
JwtAuthenticationFilter
Tomcat

Esses componentes não eram a causa original.

A cadeia era:

Banco não abre
       ↓
Hibernate não inicializa
       ↓
EntityManagerFactory não existe
       ↓
UserRepository não inicializa
       ↓
CustomUserDetailsService não inicializa
       ↓
JwtAuthenticationFilter não inicializa
       ↓
Spring/Tomcat aborta

15. Correção do banco

Depois disso conectamos diretamente ao endpoint do TiDB pelo cliente MySQL.

Foi confirmado:

USE segundachance;

Resultado:

Database changed

E:

SELECT DATABASE();

retornou:

segundachance

Portanto, o banco agora existe e está acessível através do endpoint utilizado.
16. Estado atual do deploy

Neste momento temos:

Frontend publicado                         OK
Render Web Service criado                  OK
Docker build                               OK
Maven build                                OK
Spring Boot compilando                     OK
TiDB Cloud criado                          OK
Conexão MySQL/TiDB                         OK
Banco segundachance criado                 OK
TLS obrigatório configurado                OK
Variáveis de ambiente configuradas         OK
JWT em variável de ambiente                OK
Hibernate MySQLDialect configurado          OK
Porta dinâmica do Render configurada        OK

A etapa imediata agora é realizar novamente:

Render
→ segunda-chance-api
→ Manual Deploy
→ Deploy latest commit

e verificar se o Spring inicializa completamente.
17. O que esperamos no log

O deploy estará efetivamente concluído quando aparecer algo equivalente a:

HikariPool-1 - Start completed

seguido da inicialização do Hibernate/JPA e finalmente algo semelhante a:

Tomcat started on port ...
Started SegundachanceApplication

Nesse ponto:

TiDB
  ↑
Render/Spring Boot

estará operacional.
18. Trabalho que resta para o frontend

Assim que o backend estiver Live, o frontend deve substituir a API local.

Onde atualmente houver algo como:

http://localhost:8080

deverá passar a utilizar:

https://segunda-chance-api.onrender.com

Idealmente centralizado em configuração de ambiente, e não espalhado pelos services.

Exemplo conceitual:

export const environment = {
  production: true,
  apiUrl: 'https://segunda-chance-api.onrender.com'
};

E nos services:

private readonly apiUrl = environment.apiUrl;

Então:

this.http.get(`${this.apiUrl}/api/...`);

19. CORS

Depois que frontend e backend estiverem em domínios diferentes, precisamos verificar o CORS do Spring Security.

A comunicação será aproximadamente:

Frontend publicado
        │
        │ HTTPS
        ▼
https://segunda-chance-api.onrender.com
        │
        ▼
Spring Security

O domínio público real do frontend deverá estar autorizado pelo backend.

Não devemos simplesmente liberar:

*

indiscriminadamente em produção se a aplicação utiliza autenticação/credenciais.

O ideal é autorizar explicitamente o domínio do frontend.
20. Testes finais obrigatórios

Quando o backend estiver online, precisamos testar o fluxo completo, não apenas verificar se a página abre:

    Frontend abre pela URL pública.

    Backend responde pela URL do Render.

    Endpoint público funciona.

    Cadastro de usuário funciona.

    Usuário é salvo no TiDB.

    Login funciona.

    Backend gera JWT.

    Frontend armazena/utiliza o token conforme implementação atual.

    Requisição autenticada funciona.

    Categorias/anúncios são recuperados do banco.

    Criação de anúncio funciona.

    Dados permanecem disponíveis após reiniciar/reacordar o backend.

    Nenhuma chamada do frontend continua apontando para localhost.

21. Arquitetura final desejada

Quando terminarmos, teremos:

                   INTERNET

                      │
                      ▼
          ┌─────────────────────┐
          │   Angular / PWA     │
          │ Frontend publicado  │
          └──────────┬──────────┘
                     │
                     │ HTTPS / REST / JWT
                     ▼
          ┌─────────────────────┐
          │       Render        │
          │                     │
          │ Spring Boot API     │
          │ segunda-chance-api  │
          └──────────┬──────────┘
                     │
                     │ JDBC + TLS
                     ▼
          ┌─────────────────────┐
          │     TiDB Cloud      │
          │                     │
          │   segundachance     │
          │ MySQL-compatible    │
          └─────────────────────┘

Nenhum componente depende do computador local para a aplicação publicada funcionar.
22. Prioridade daqui para frente

Não realizar novas alterações arquiteturais sem um erro concreto.

A sequência agora deve ser estritamente:

1. Redeploy do backend
        ↓
2. Confirmar "Started SegundachanceApplication"
        ↓
3. Testar endpoint do Render
        ↓
4. Configurar URL do Render no Angular
        ↓
5. Configurar/verificar CORS
        ↓
6. Redeploy do frontend
        ↓
7. Testar cadastro
        ↓
8. Testar login/JWT
        ↓
9. Testar CRUD real
        ↓
10. Validar aplicação ponta a ponta

Não devemos trocar novamente de banco, provedor ou arquitetura neste momento. O build já funciona, o TiDB está acessível e o banco segundachance foi confirmado. Agora a prioridade é terminar a integração Render ↔ TiDB e depois Frontend ↔ Render.

Esse é o ponto exato em que o projeto se encontra.

    Your service is live 🎉
    ==>
    ==> ///////////////////////////////////////////////////////////
    ==>
    ==> Available at your primary URL https://segunda-chance-api.onrender.com
    ==>
    ==> ///////////////////////////////////////////////////////////

so pra confirmar, eu posso fechar as abas do render e do tidb né?

Sim. Pode fechar as abas do Render e do TiDB tranquilamente. 🎉

O trecho:

Your service is live
Available at your primary URL
https://segunda-chance-api.onrender.com

confirma que o backend subiu com sucesso no Render.

Fechar o navegador não desliga nada, porque:

    Render executa o Spring Boot nos servidores deles.

    TiDB executa o banco nos servidores deles.

    Seu computador pode ser desligado.

    Docker local pode ficar desligado.

    MySQL local pode ficar desligado.

    Adminer local pode ficar desligado.

A arquitetura agora é realmente:

Frontend publicado
       ↓
Render — Spring Boot
       ↓
TiDB Cloud

Seu PC não faz mais parte da infraestrutura de produção.

Só existe uma característica importante do plano gratuito: o Render pode colocar o backend para dormir depois de ficar inativo. Quando alguém acessar novamente, a primeira requisição pode demorar um pouco enquanto ele acorda. Isso não exige que você abra o painel do Render.

Agora finalmente podemos partir para a última integração: apontar o Angular para https://segunda-chance-api.onrender.com, acertar CORS e testar cadastro/login/CRUD de ponta a ponta.

hoje 21:53
Markdown(8).md colado
Arquivo
quero que você me forneça um readme ESPECIFICAMENTE SOBRE O BACK, tudo que fizemos, aplicações, testes, tudo que o edital exige. voce deve mesclar com o readme do front, o diario de bordo será feito a parte  

Pronto. Montei um README geral do projeto, acrescentando uma documentação extensa do backend e mesclando com o README do frontend que você enviou, que já documentava Angular, autenticação, anúncios, solicitações, testes e deploy.

No backend, documentei especificamente:

    Java 21 + Spring Boot 4.1.0;

    arquitetura Controller → Service → Repository;

    DTO Request/Response;

    Spring Data JPA + Hibernate;

    Spring Security;

    JWT e fluxo de autenticação;

    usuários e perfil USER;

    categorias;

    anúncios de venda/doação;

    pesquisa, filtros e paginação;

    solicitações;

    imagens;

    MySQL local;

    tentativa com Aiven e motivo da substituição;

    migração para TiDB Cloud;

    criação do segundachance;

    MySQL CLI e Workbench;

    configuração JDBC/TLS;

    application.yml de produção;

    variáveis de ambiente do Render;

    configuração de PORT;

    Maven;

    Docker;

    build de produção;

    deploy no Render;

    erros Unable to determine Dialect, Unknown database, EntityManagerFactory e No open ports detected;

    causas e soluções desses erros;

    Postman;

    testes locais;

    testes de integração;

    testes em produção;

    validação direta no banco;

    requisitos funcionais;

    requisitos não funcionais;

    segurança;

    execução local;

    checklist final;

    evidências recomendadas;

    arquitetura completa Vercel → Render → TiDB.

Também deixei explicitamente o diário de bordo fora do README, como você pediu.

Um ponto importante: não inventei itens do edital que eu não tenho. O próprio README que você enviou dizia que a documentação geral deveria ser complementada por backend, banco, arquitetura, endpoints, diário de bordo, evidências, histórico, divisão de atividades, dificuldades e vídeo. O novo README cobre a parte técnica que temos evidência; campos formais específicos do edital ficaram sinalizados para preenchimento caso sejam exigidos.

A arquitetura documentada também está coerente com a que já constava no README do frontend: Angular/Vercel → API Spring Boot/Render → TiDB Cloud.

Próximo documento separado: o diário de bordo. Esse sim pode contar toda a saga cronologicamente — desenvolvimento, testes, problemas com deploy, Aiven, mudança para TiDB, erros do Render, correções e conclusão.


Biblioteca
/README_SEGUNDA_CHANCE_COMPLETO.md
Segunda Chance --- Documentação Técnica
1. Visão Geral

O Segunda Chance é uma plataforma web desenvolvida para a comunidade
acadêmica da Universidade de Fortaleza (Unifor), destinada à compra,
venda e doação de objetos usados. A proposta incentiva economia
circular, reutilização, sustentabilidade e redução de desperdício.

A solução final foi implantada de forma distribuída:

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

O frontend e o backend possuem deploy independente e a aplicação
publicada não depende da máquina de desenvolvimento permanecer ligada.
PARTE I --- BACKEND
2. Objetivo do Backend

O backend implementa a API REST do Segunda Chance. Ele concentra regras
de negócio, validação, autenticação, autorização, persistência e
integração com o banco.

Responsabilidades principais:

    cadastro e autenticação de usuários;

    autenticação JWT;

    proteção de rotas;

    usuários e perfis;

    categorias;

    anúncios de venda e doação;

    pesquisa, filtros e paginação;

    solicitações;

    imagens de anúncios;

    validação de dados;

    persistência JPA/Hibernate;

    comunicação com banco remoto;

    API pública para o frontend.

3. Stack do Backend

Tecnologia Uso

Java 21 Linguagem e runtime
Spring Boot 4.1.0 Framework principal
Spring Web MVC API REST
Spring Security Autenticação e autorização
Spring Data JPA Repositórios
Hibernate ORM
Bean Validation Validação de DTOs
JJWT Tokens JWT
MySQL Connector/J Driver JDBC
HikariCP Pool de conexões
Maven Dependências e build
Docker Containerização
Render Hospedagem da API
TiDB Cloud Banco remoto compatível com MySQL
MySQL Workbench Inspeção e validação do banco
Postman Testes manuais da API
4. Arquitetura em Camadas

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

O projeto utiliza DTOs para não expor diretamente as entidades JPA:

Request DTO -> Controller -> Service -> Entity/Repository -> Banco
Banco -> Service -> Response DTO -> Cliente

Estrutura conceitual:

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

5. Padrão DTO

Foi adotado o padrão RequestDTO e ResponseDTO. No CRUD de
categorias, por exemplo, foram implementados CategoryRequestDTO,
CategoryResponseDTO, CategoryService, CategoryController e
CategoryRepository.

Exemplo de validação aplicada:

@NotBlank(message = "O nome da categoria é obrigatório")
@Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
private String name;

Essa abordagem separa o contrato HTTP do modelo de persistência e
facilita validação e manutenção.
6. Autenticação e Segurança

A autenticação utiliza Spring Security e JWT. Os componentes
desenvolvidos para esse fluxo incluem:

SecurityConfig
JwtService
JwtAuthenticationFilter
CustomUserDetailsService
AuthController
UserRepository

Fluxo de login:

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

Requisições protegidas utilizam:

Authorization: Bearer <token>

A expiração configurada é de 86400000 ms, equivalente a 24 horas. O
segredo JWT é fornecido por variável de ambiente e não deve ser
versionado.

O cadastro também foi integrado ao frontend através de
/api/auth/register. Durante os testes foi identificado que o cadastro
dependia da existência do perfil padrão USER no banco; o dado
necessário foi incluído para permitir a conclusão do fluxo.
7. Categorias

As categorias são persistidas e fornecidas dinamicamente pela API:

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

Isso evita manter uma lista independente e divergente apenas no
frontend.
8. Anúncios

O recurso central da aplicação representa itens de VENDA ou DOACAO.
A integração utiliza informações como identificador, título, descrição,
categoria, tipo, preço, estado, proprietário e imagem principal.

A consulta de anúncios suporta parâmetros utilizados pelo frontend:

page
size
search
categoryId
tipo
status

A resposta paginada disponibiliza dados como content, number e
totalPages.

Foram integrados pesquisa por título, filtro por categoria, filtro por
tipo, estado e paginação. O frontend utiliza, entre outros, o estado
DISPONIVEL.
9. Solicitações e Imagens

O backend possui fluxo de solicitações para representar o interesse de
usuários nos itens anunciados. O módulo é consumido pela área de
solicitações do frontend.

Também existe integração para imagens de anúncios. No frontend,
AdImagesService consome os recursos correspondentes da API. A
correspondência entre a resposta da API e imagemPrincipalUrl foi
verificada durante os testes finais da listagem.
10. Banco de Dados
10.1 Desenvolvimento local

Durante o desenvolvimento foi utilizado MySQL local, inclusive com
configuração em localhost:3395. Essa URL não poderia ser utilizada
pelo backend hospedado.
10.2 Tentativa com Aiven

Foi inicialmente criado um MySQL remoto no Aiven. O serviço permaneceu
por tempo excessivo em Building e depois Rebuilding. Como isso
colocava o prazo da entrega em risco, a solução foi substituída.
10.3 Solução final: TiDB Cloud

Foi adotado o TiDB Cloud, compatível com o protocolo MySQL. A instância
final foi denominada:

segunda-chance-db

O endpoint utilizado foi:

gateway01.sa-east-1.prod.aws.tidbcloud.com

Porta:

4000

A conexão pública exige TLS.

O banco da aplicação foi criado com:

CREATE DATABASE IF NOT EXISTS segundachance;

e validado com:

SHOW DATABASES;
USE segundachance;
SELECT DATABASE();

O resultado de SELECT DATABASE() confirmou segundachance.
11. Cliente MySQL e Workbench

No Windows, mysql inicialmente não era reconhecido no PowerShell
porque o executável não estava no PATH. Foram localizados:

C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe
C:\Program Files\MySQL\MySQL Workbench 8.0\mysql.exe

Também foi corrigida uma confusão entre comandos de terminal e SQL:
mysql -h ... deve ser executado no PowerShell/CMD, enquanto dentro do
prompt mysql> são executados comandos como CREATE DATABASE, USE e
SELECT.

O MySQL Workbench foi utilizado para verificar a persistência no TiDB.
Exemplo:

SELECT user_id, name, email, role_id
FROM users
ORDER BY user_id DESC;

12. Configuração de Produção

O application.yml foi preparado para receber configurações por
variáveis de ambiente:

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

A URL JDBC de produção segue:

jdbc:mysql://gateway01.sa-east-1.prod.aws.tidbcloud.com:4000/segundachance?sslMode=REQUIRED

No Render foram configuradas:

SPRING_DATASOURCE_URL
SPRING_DATASOURCE_USERNAME
SPRING_DATASOURCE_PASSWORD
JWT_SECRET
SPRING_JPA_HIBERNATE_DDL_AUTO

Credenciais e segredos reais não devem constar no README nem no Git.
13. Porta em Produção

A configuração:

server:
  port: ${PORT:8080}

permite usar 8080 localmente e a porta fornecida pelo Render em
produção.
14. Docker, Maven e Build

O backend foi publicado no Render como Web Service baseado em Docker.

O build executou:

mvn clean package -DskipTests

O log confirmou compilação de 57 arquivos Java e geração de:

/app/target/segundachance-0.0.1-SNAPSHOT.jar

O Spring Boot Maven Plugin realizou o repackage. O log apresentou
BUILD SUCCESS, Spring Boot 4.1.0 e Java 21.0.11.

Fluxo:

GitHub -> Render -> Docker build -> Maven -> JAR -> Spring Boot

15. Deploy no Render

Serviço:

segunda-chance-api

URL pública:

https://segunda-chance-api.onrender.com

Durante as correções foram utilizadas opções de Manual Deploy como
Deploy latest commit, Clear build cache & deploy e reinicialização
do serviço.

Ao final, o Render confirmou:

Your service is live

O backend passou a funcionar independentemente da máquina local.
16. Problemas de Deploy e Soluções
16.1 Unable to determine Dialect without JDBC metadata

O Hibernate não conseguia obter metadados JDBC. A investigação levou à
revisão da SPRING_DATASOURCE_URL e da conexão remota.
16.2 Unknown database 'segundachance'

Depois de alcançar o TiDB, o backend falhou porque o database esperado
ainda não existia. Isso confirmou que host e conexão estavam chegando ao
servidor. A solução foi criar segundachance manualmente.
16.3 Falha encadeada do JPA

Com o EntityManagerFactory indisponível, falharam também dependências
como:

UserRepository
CustomUserDetailsService
JwtAuthenticationFilter

A causa raiz era a persistência, não o filtro JWT. A criação/correção do
banco permitiu a inicialização da cadeia.
16.4 No open ports detected

O Render exibiu essa mensagem enquanto o Spring encerrava antes de o
Tomcat permanecer ativo. A correção real foi resolver a falha JDBC; não
simplesmente trocar uma porta.
16.5 Provisionamento remoto

O Aiven foi abandonado após demora excessiva em Building/Rebuilding. O
TiDB foi adotado para reduzir o risco de atraso.
16.6 Importação pelo painel TiDB

A importação pelo painel não era o caminho mais rápido para o SQL local.
Foi preferida a conexão MySQL, criação do database e uso do
Hibernate/JPA para criar/atualizar a estrutura.
17. Testes do Backend

A validação ocorreu em três níveis:

I. Backend isolado
   Postman / requisições HTTP

II. Backend + banco
    API -> TiDB

III. Sistema completo
     Vercel -> Render -> TiDB

Foram realizados testes de:

    compilação Maven;

    inicialização Spring Boot;

    cadastro;

    persistência de usuário;

    login;

    JWT;

    rotas autenticadas;

    categorias;

    criação de anúncio;

    venda;

    doação;

    listagem;

    pesquisa;

    filtros;

    paginação;

    imagens;

    solicitações;

    persistência no banco;

    integração em produção.

Os testes de produção foram importantes porque o funcionamento local não
garante que URL, HTTPS, CORS, credenciais, JWT e banco remoto estejam
corretos.
18. Estratégia de Diagnóstico

Foram utilizados:

    logs do Spring Boot;

    logs de deploy do Render;

    Postman;

    MySQL CLI;

    MySQL Workbench;

    DevTools do navegador;

    aba Network/XHR;

    consultas SQL;

    testes pelo frontend publicado.

Essa combinação permitiu localizar falhas entre interface, API,
autenticação, configuração de ambiente, JDBC e persistência.
19. Segurança e Boas Práticas

Foram aplicados:

    Spring Security;

    JWT;

    autenticação stateless;

    filtro JWT;

    DTOs;

    Bean Validation;

    credenciais por variáveis de ambiente;

    JWT_SECRET fora do código;

    TLS obrigatório com o TiDB;

    separação entre ambientes;

    banco acessado somente pelo backend;

    separação Controller/Service/Repository;

    configuração de porta adequada ao ambiente.

20. Execução Local do Backend

Pré-requisitos:

Java 21
Maven
MySQL ou banco compatível

Variáveis necessárias:

SPRING_DATASOURCE_URL
SPRING_DATASOURCE_USERNAME
SPRING_DATASOURCE_PASSWORD
JWT_SECRET
SPRING_JPA_HIBERNATE_DDL_AUTO

Executar:

mvn spring-boot:run

Build:

mvn clean package

ou:

mvn clean package -DskipTests

Sem PORT, a aplicação usa 8080.
21. Requisitos Funcionais Atendidos pelo Backend

Requisito Implementação

Cadastro API + persistência
Login Spring Security
Autenticação JWT
Rotas protegidas Security + filtro JWT
Usuários Service/Repository
Categorias API e banco
Anúncios API de gerenciamento
Venda e doação Tipos de anúncio
Pesquisa API
Filtros Categoria, tipo e estado
Paginação Resposta paginada
Solicitações Fluxo integrado
Imagens Integração de anúncio
Validação Bean Validation
Persistência JPA/Hibernate
Banco remoto TiDB Cloud
Deploy Render
Integração Frontend Vercel
Configuração segura Variáveis de ambiente
22. Requisitos Não Funcionais

Segurança: JWT, Spring Security, TLS e segredos externos ao código.

Manutenibilidade: separação em controllers, services, repositories,
DTOs, entities e security.

Desacoplamento: frontend, backend e persistência são camadas
independentes.

Portabilidade: aplicação empacotada em JAR e executada em Docker.

Disponibilidade externa: API publicada no Render.

Escalabilidade de código: organização por responsabilidades e
recursos.
23. Evidências Recomendadas

Para a entrega, manter evidências de:

    BUILD SUCCESS;

    Render com serviço ativo;

    Your service is live;

    URL pública da API;

    banco segundachance no TiDB;

    conexão no Workbench;

    tabelas;

    usuário persistido;

    categorias;

    anúncio;

    solicitação;

    cadastro pelo frontend;

    login e JWT;

    requisição protegida;

    listagem e filtros;

    venda e doação;

    integração Vercel -> Render -> TiDB.

24. Infraestrutura Final

Camada Tecnologia Hospedagem

Frontend Angular / TypeScript / SCSS Vercel
Backend Java 21 / Spring Boot 4.1 Render
Persistência TiDB compatível com MySQL TiDB Cloud
Segurança Spring Security + JWT Backend
ORM JPA / Hibernate Backend
Build Maven + Docker Render
25. Status do Backend

Ao final do processo documentado:

    build concluído;

    JAR gerado;

    imagem Docker construída;

    Spring Boot inicializado;

    conexão Render -> TiDB configurada;

    database segundachance criado;

    variáveis de ambiente configuradas;

    API publicada;

    persistência validada;

    integração com o frontend disponível.

API:

https://segunda-chance-api.onrender.com

26. Checklist Final do Backend

    cadastrar novo usuário;

    confirmar persistência;

    realizar login;

    confirmar JWT;

    testar rota protegida;

    listar categorias;

    listar e pesquisar anúncios;

    testar filtros e paginação;

    criar venda;

    criar doação;

    testar imagens;

    testar solicitações;

    confirmar respostas para dados inválidos;

    confirmar ausência de segredos no Git;

    executar o fluxo usando apenas serviços publicados.

PARTE II --- FRONTEND
1. Visão Geral

O frontend do Segunda Chance corresponde à interface web da
plataforma desenvolvida para a comunidade acadêmica da Universidade de
Fortaleza (Unifor).

A aplicação tem como objetivo permitir que estudantes, professores e
colaboradores encontrem uma nova finalidade para objetos que não
utilizam mais, possibilitando a publicação de itens para venda ou
doação e incentivando princípios de economia circular, reutilização e
sustentabilidade dentro do ambiente acadêmico.

O frontend foi desenvolvido como uma aplicação web independente,
responsável pela interação entre o usuário e a API REST do Segunda
Chance.

A aplicação permite realizar operações como:

    cadastro de usuário;

    autenticação;

    navegação pela plataforma;

    consulta de anúncios;

    pesquisa de anúncios;

    filtragem por categoria;

    filtragem por tipo de anúncio;

    publicação de anúncios;

    publicação de itens para venda;

    publicação de itens para doação;

    visualização dos próprios anúncios;

    consulta aos detalhes de um anúncio;

    gerenciamento de solicitações;

    acesso às informações do usuário;

    encerramento da sessão.

O frontend foi desenvolvido utilizando Angular e posteriormente
publicado em ambiente de produção através da Vercel.
2. Objetivo do Frontend

O frontend foi desenvolvido com o objetivo de fornecer uma interface
simples, organizada e responsiva para utilização das funcionalidades
disponibilizadas pelo backend.

A aplicação atua como cliente da API REST do projeto.

Sua responsabilidade principal consiste em:

    receber as ações realizadas pelo usuário;

    validar os dados necessários na interface;

    realizar requisições HTTP para a API;

    processar as respostas retornadas pelo backend;

    apresentar as informações de forma compreensível;

    controlar o estado da sessão autenticada;

    organizar a navegação entre as funcionalidades da plataforma.

A arquitetura mantém o frontend desacoplado do backend.

Dessa forma, a aplicação Angular e a API Spring Boot podem ser
desenvolvidas, executadas e publicadas separadamente.
3. Tecnologias Utilizadas
3.1 Angular

O Angular foi utilizado como principal framework de desenvolvimento do
frontend.

A aplicação utiliza componentes para separar as diferentes áreas e
responsabilidades da interface.

Entre os recursos utilizados estão:

    componentes standalone;

    templates HTML;

    SCSS;

    serviços;

    injeção de dependências;

    roteamento;

    formulários;

    requisições HTTP;

    Signals;

    controle de estado local;

    diretivas e estruturas condicionais do Angular.

3.2 TypeScript

O TypeScript foi utilizado para implementação da lógica da aplicação.

Sua utilização permitiu definir modelos tipados para representar as
informações recebidas da API, reduzindo inconsistências entre frontend e
backend.

Entre os objetos representados no frontend encontram-se:

    usuários;

    anúncios;

    categorias;

    solicitações;

    autenticação;

    imagens de anúncios;

    respostas paginadas.

3.3 HTML

Os templates HTML são responsáveis pela estrutura visual das páginas.

Foram utilizadas estruturas de template do Angular para:

    renderização condicional;

    iteração de anúncios;

    iteração de categorias;

    estados de carregamento;

    estados de erro;

    estados sem resultados;

    paginação;

    exibição dinâmica de dados.

3.4 SCSS

O SCSS foi utilizado para construção da identidade visual e organização
dos estilos da aplicação.

A interface foi construída procurando manter consistência entre:

    cores;

    espaçamentos;

    tipografia;

    botões;

    campos;

    cards;

    filtros;

    cabeçalho;

    páginas de autenticação;

    páginas de gerenciamento;

    mensagens de estado.

3.5 Angular HttpClient

A comunicação com o backend é realizada através do cliente HTTP do
Angular.

O frontend consome os endpoints REST disponibilizados pela API para
operações relacionadas a:

    autenticação;

    usuários;

    anúncios;

    categorias;

    solicitações;

    imagens.

3.6 Vercel

A Vercel foi utilizada para publicação do frontend em ambiente de
produção.

O deploy separado do frontend demonstra a separação entre as camadas da
aplicação.

A aplicação publicada pode consumir a API hospedada externamente através
da URL configurada no ambiente de produção.
4. Arquitetura do Frontend

O projeto foi organizado procurando separar interface, regras de
apresentação, comunicação HTTP e modelos.

A estrutura geral segue o princípio:

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

Os componentes não realizam diretamente a implementação das chamadas
HTTP.

Essa responsabilidade é delegada aos serviços.

Exemplo:

AdsList
   |
   v
AdsService
   |
   v
API Segunda Chance

Esse modelo melhora:

    organização;

    manutenção;

    reutilização;

    separação de responsabilidades;

    legibilidade;

    possibilidade de evolução futura.

5. Organização do Projeto

A aplicação foi dividida conceitualmente em áreas responsáveis pelas
diferentes funcionalidades.

Uma estrutura simplificada pode ser representada por:

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

Dentro das funcionalidades relacionadas aos anúncios, foram utilizados
elementos como:

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

A estrutura permite manter próximas as classes relacionadas a uma mesma
funcionalidade.
6. Modelos

O frontend utiliza interfaces e modelos TypeScript para representar os
dados recebidos do backend.

Entre os principais modelos utilizados encontram-se:
6.1 Anúncio

Representa um item disponibilizado na plataforma.

Entre as informações utilizadas pela interface estão:

    identificador;

    título;

    descrição;

    tipo;

    preço;

    categoria;

    imagem;

    estado do anúncio.

O tipo pode representar, por exemplo:

VENDA
DOACAO

6.2 Categoria

As categorias são carregadas dinamicamente através da API.

Cada categoria possui um identificador utilizado internamente pelo
backend e um nome apresentado ao usuário.

Exemplo conceitual:

{
  "id": 1,
  "name": "Acessórios"
}

7. Categorias

As categorias disponíveis na interface são obtidas do banco de dados
através da API.

Isso evita manter uma lista fixa somente no frontend.

Foram cadastradas categorias destinadas a representar os principais
tipos de objetos utilizados dentro da comunidade acadêmica.

Entre elas:

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

A categoria Outros é mantida ao final da apresentação para
representar itens que não pertencem às categorias específicas.
8. Sistema de Autenticação

O frontend possui páginas destinadas ao cadastro e autenticação dos
usuários.
8.1 Cadastro

A tela de cadastro permite fornecer as informações necessárias para
criação da conta.

O fluxo realizado é:

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

A interface apresenta ao usuário informações sobre o estado da operação.

Entre os estados previstos estão:

Criar conta
Criando conta...
Conta criada
Erro ao criar conta

8.2 Login

Após possuir uma conta, o usuário pode realizar autenticação através da
página de login.

O frontend envia as credenciais para a API.

Após autenticação válida, o token recebido é utilizado para identificar
as requisições que exigem autenticação.

Fluxo conceitual:

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

9. Integração com JWT

A autenticação entre frontend e backend utiliza JSON Web Token.

Após o login, as requisições para recursos protegidos devem enviar o
token através do cabeçalho HTTP:

Authorization: Bearer <token>

Isso permite que a API identifique o usuário responsável pela operação.

Esse mecanismo é utilizado em operações que dependem da identidade do
usuário, como gerenciamento de anúncios e solicitações.
10. Página Inicial

A página inicial foi desenvolvida para apresentar o conceito do Segunda
Chance e facilitar o acesso às principais funcionalidades.

A identidade da aplicação foi construída em torno da mensagem:

    Dar uma nova vida aos objetos.

A página comunica os três principais comportamentos esperados na
plataforma:
10.1 Compre

Permite encontrar itens disponibilizados por outros integrantes da
comunidade.
10.2 Venda

Permite dar um novo destino a objetos que o usuário não utiliza mais.
10.3 Doe

Permite disponibilizar gratuitamente um objeto para outro integrante da
comunidade.

A interface foi construída para não apresentar o Segunda Chance
simplesmente como uma loja virtual tradicional, mas como uma plataforma
de circulação e reaproveitamento de objetos.
11. Identidade Visual

A identidade visual do frontend foi desenvolvida especificamente para o
projeto Segunda Chance.

Foram considerados os seguintes conceitos:

    sustentabilidade;

    confiança;

    comunidade;

    colaboração;

    simplicidade;

    organização;

    inovação;

    reutilização;

    continuidade.

A aplicação utiliza uma identidade visual consistente nas páginas e
componentes.

Também foram produzidos elementos visuais específicos para:

    marca Segunda Chance;

    símbolo utilizado no cabeçalho;

    representação de compra;

    representação de venda;

    representação de doação.

12. Cabeçalho e Navegação

Após a autenticação, o usuário possui acesso ao cabeçalho principal da
aplicação.

Entre as opções de navegação encontram-se:

Início
Explorar
Meus anúncios
Solicitações
Perfil
Anunciar item
Sair

O cabeçalho foi implementado como componente compartilhado para evitar
repetição entre as páginas.
13. Exploração de Anúncios

A página de exploração é responsável por apresentar os anúncios
disponíveis na plataforma.

O frontend consulta a API e utiliza a resposta paginada para construir
dinamicamente os cards.

O carregamento é realizado através do serviço responsável pelos
anúncios.

Exemplo conceitual:

this.adsService.getAds(...)

A resposta é utilizada para atualizar:

content
number
totalPages

Assim, o frontend consegue controlar:

    anúncios da página atual;

    página selecionada;

    quantidade de páginas;

    navegação entre páginas.

14. Pesquisa de Anúncios

A página de exploração possui um campo de pesquisa por título.

O usuário pode informar um termo e executar a busca.

A interface envia o valor para o serviço de anúncios, que realiza a
consulta correspondente na API.

Exemplo de interface:

Buscar por título...
[ Buscar ]

A pesquisa pode ser utilizada em conjunto com os filtros.
15. Filtro por Categoria

O frontend carrega as categorias através do serviço de categorias.

A categoria selecionada é armazenada utilizando um Signal:

selectedCategoryId

O identificador é posteriormente enviado na consulta de anúncios.

A interface oferece também a opção:

Todas

que remove o filtro de categoria.
16. Filtro por Tipo

Os anúncios podem ser filtrados pelo tipo da publicação.

A interface disponibiliza:

Todos
Venda
Doação

Internamente, os valores utilizados são compatíveis com os valores
esperados pela API:

VENDA
DOACAO

17. Combinação de Filtros

A tela foi construída para permitir a combinação entre:

    termo de pesquisa;

    categoria;

    tipo.

Assim, o usuário pode realizar uma consulta mais específica.

Exemplo:

Pesquisa: notebook
Categoria: Computação
Tipo: Venda

A aplicação também possui a opção:

Limpar filtros

responsável por restaurar o estado inicial da consulta.
18. Compatibilidade com Parâmetros da URL

A página de anúncios também considera parâmetros recebidos através da
URL.

Por exemplo:

/ads?category=Livros

Quando uma categoria é recebida pelo nome, o frontend procura a
categoria correspondente entre aquelas retornadas pela API.

Em seguida, utiliza seu identificador real para executar a consulta.

Essa solução permite integrar links da página inicial com o sistema de
categorias baseado em IDs utilizado pelo backend.
19. Paginação

A listagem de anúncios possui suporte à paginação retornada pelo
backend.

O frontend mantém os Signals:

currentPage
totalPages

A interface disponibiliza controles:

Anterior
Página X de Y
Próxima

Os botões são automaticamente desabilitados quando não existe uma página
anterior ou posterior.
20. Estados da Interface

Uma preocupação durante o desenvolvimento foi não deixar a interface sem
retorno durante operações assíncronas.

Por isso, foram implementados diferentes estados visuais.
20.1 Carregamento

Exemplo:

Carregando anúncios...

Também foram utilizados indicadores visuais de carregamento.
20.2 Erro

Caso uma requisição falhe, a interface apresenta uma mensagem ao
usuário.

Exemplo:

Não foi possível carregar os anúncios.
Tente novamente em alguns instantes.

A página também pode disponibilizar:

Tentar novamente

20.3 Resultado vazio

Caso a consulta seja concluída corretamente, mas nenhum anúncio
corresponda aos filtros, é apresentado:

Nenhum anúncio encontrado.
Tente alterar os filtros ou realizar uma nova busca.

20.4 Resultado disponível

Quando existem anúncios, os dados retornados são convertidos em cards.
21. Cards de Anúncios

Cada anúncio é apresentado em um card contendo as informações
necessárias para identificação rápida do item.

Entre os dados apresentados estão:

    imagem;

    categoria;

    tipo;

    título;

    descrição;

    preço.

Quando o anúncio corresponde a uma doação, a interface apresenta:

Doação

em vez de um valor monetário.

Para anúncios de venda, o valor é formatado utilizando o padrão
brasileiro:

ad.preco.toLocaleString('pt-BR', {
  style: 'currency',
  currency: 'BRL',
});

22. Tratamento de Imagens

Os anúncios possuem suporte à exibição de uma imagem principal.

Quando existe uma URL de imagem válida, o frontend utiliza:

<img [src]="ad.imagemPrincipalUrl" [alt]="ad.titulo" />

Caso uma imagem não esteja disponível, a interface pode utilizar um
placeholder contendo a categoria do item.

Essa abordagem evita que o layout do card seja quebrado quando um
anúncio não possuir imagem disponível.
23. Publicação de Anúncios

A plataforma possui uma tela específica para publicação de novos itens.

O usuário pode informar dados referentes ao anúncio e selecionar a
finalidade do item.

As opções principais são:

Vender
Doar

Para uma venda, o usuário pode informar um preço.

Para uma doação, o item é disponibilizado sem cobrança.
24. Seleção de Categoria no Cadastro de Anúncio

O formulário de publicação consulta as categorias existentes no backend.

O usuário pode selecionar uma categoria através de um campo de seleção.

Exemplo:

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

O identificador da categoria selecionada é enviado para a API durante a
criação do anúncio.
25. Meus Anúncios

A aplicação possui uma página destinada aos anúncios pertencentes ao
usuário autenticado.

Essa página permite separar a consulta geral da plataforma dos itens
publicados pelo próprio usuário.

A interface apresenta a quantidade de anúncios publicados e a listagem
correspondente.

Exemplo:

Meus anúncios

Gerencie os itens que você colocou em circulação na comunidade.

3 anúncios publicados

Essa área funciona como espaço de gerenciamento dos itens publicados
pelo usuário.
26. Detalhes do Anúncio

Ao selecionar um anúncio, a aplicação pode navegar para sua página
específica.

A navegação é realizada utilizando o identificador do anúncio.

Exemplo:

this.router.navigate(['/ads', id]);

Isso permite manter URLs individuais para os anúncios.
27. Solicitações

O frontend também possui integração com o fluxo de solicitações.

Esse fluxo permite representar o interesse de outro usuário em um item
disponibilizado na plataforma.

A área de solicitações faz parte da navegação autenticada e comunica-se
com os endpoints correspondentes da API.

O objetivo é permitir acompanhar as interações relacionadas aos itens
anunciados.
28. Perfil

A aplicação possui uma área destinada às informações do usuário
autenticado.

A página de perfil integra o conjunto de funcionalidades privadas da
aplicação e pode ser acessada através do cabeçalho.
29. Logout

O cabeçalho possui a opção:

Sair

Essa funcionalidade encerra a sessão do usuário no frontend e impede que
o token de autenticação continue sendo utilizado pela sessão encerrada.
30. Serviços

A comunicação com a API foi organizada em serviços específicos.

Entre os serviços utilizados durante o desenvolvimento encontram-se:

AuthService
AdsService
CategoriesService
AdImagesService

Outros serviços podem existir de acordo com a funcionalidade
correspondente.

Essa divisão evita centralizar todas as chamadas HTTP em uma única
classe.
31. AdsService

O serviço de anúncios centraliza as operações HTTP relacionadas aos
anúncios.

A página de exploração utiliza esse serviço para solicitar anúncios
considerando parâmetros como:

page
size
search
categoryId
tipo
status

Exemplo conceitual:

this.adsService.getAds(
  page,
  8,
  search,
  categoryId,
  tipo,
  'DISPONIVEL'
);

32. CategoriesService

O serviço de categorias consulta as categorias disponibilizadas pela
API.

A resposta é utilizada em diferentes partes da aplicação, incluindo:

    filtros;

    formulário de criação de anúncio;

    identificação da categoria dos itens.

Isso evita duplicação da lista de categorias no frontend.
33. Signals

O projeto utiliza Signals do Angular para gerenciamento de estado local
dos componentes.

Na listagem de anúncios, por exemplo, são mantidos estados semelhantes
a:

readonly ads = signal<Ad[]>([]);
readonly categories = signal<Category[]>([]);

readonly isLoading = signal(true);
readonly hasError = signal(false);

readonly currentPage = signal(0);
readonly totalPages = signal(0);

readonly searchTerm = signal('');

readonly selectedCategoryId = signal<number | null>(null);
readonly selectedType = signal<'' | 'VENDA' | 'DOACAO'>('');

Essa estratégia facilita a atualização reativa da interface.
34. Tratamento de Operações Assíncronas

As chamadas HTTP são tratadas considerando sucesso e erro.

Exemplo conceitual:

service.getData().subscribe({
  next: (response) => {
    // processamento da resposta
  },

  error: (error) => {
    // tratamento da falha
  },
});

Essa estrutura foi utilizada para evitar que falhas de comunicação
deixassem a aplicação sem feedback.
35. Integração Frontend e Backend

Durante o desenvolvimento, o frontend foi inicialmente executado
localmente.

Exemplo:

http://localhost:4200

O backend também pode ser executado localmente durante o
desenvolvimento.

Posteriormente, a aplicação passou a utilizar a API publicada
externamente.

A arquitetura final pode ser representada por:

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

Essa configuração demonstra uma aplicação distribuída em múltiplos
serviços.
36. Configuração de Ambientes

O frontend utiliza arquivos de ambiente para evitar manter URLs
específicas diretamente nos serviços.

Conceitualmente:

environment.apiUrl

Isso permite diferenciar a API utilizada durante desenvolvimento da API
utilizada em produção.

Exemplo:

Desenvolvimento
Angular -> API local

Produção
Vercel -> API publicada no Render

Durante o processo de deploy, as configurações dos serviços do frontend
foram ajustadas para utilizar corretamente o ambiente de produção.
37. Deploy

O frontend foi publicado utilizando a Vercel.

A API foi publicada separadamente no Render.

Essa estratégia atende ao objetivo de disponibilizar uma aplicação real
acessível através da Internet.
37.1 Frontend

Hospedagem:

Vercel

Aplicação:

https://segunda-chance-flax.vercel.app

37.2 Backend

API REST hospedada externamente no Render.

O endereço da API utilizado durante os testes de produção foi:

https://segunda-chance-api.onrender.com

A documentação geral do projeto deverá manter os links atualizados caso
os endereços de produção sejam alterados.
38. Testes Realizados no Frontend

O frontend foi validado durante o desenvolvimento através de testes
manuais e testes de integração com a API.

Os testes não se limitaram à execução isolada das páginas.

Também foram realizados testes utilizando o frontend publicado e o
backend publicado.
39. Teste de Cadastro

Foi testado o fluxo:

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

A persistência foi verificada diretamente no banco de dados durante os
testes.
40. Validação do Banco Durante os Testes

Durante os testes de integração, o banco TiDB foi acessado através do
MySQL Workbench.

Foi possível verificar diretamente os registros persistidos.

Por exemplo:

SELECT user_id, name, email, role_id
FROM users
ORDER BY user_id DESC;

Esse procedimento permitiu confirmar que operações realizadas através do
frontend estavam efetivamente chegando ao backend e sendo persistidas no
banco.
41. Teste das Categorias

As categorias foram cadastradas no banco e posteriormente carregadas
pelo frontend.

Foram verificados:

    carregamento das categorias;

    apresentação no formulário;

    apresentação nos filtros;

    utilização do identificador correto;

    ordenação visual;

    utilização da categoria durante a criação do anúncio.

42. Teste de Criação de Anúncio

O fluxo de criação de anúncios foi testado através da interface.

Foram verificados:

Título
Descrição
Categoria
Tipo
Preço
Imagem

Também foram testadas as diferenças entre:

Venda
Doação

43. Teste da Listagem de Anúncios

A listagem foi testada utilizando os registros existentes na API.

Foram verificados:

    quantidade retornada;

    conteúdo da página;

    categorias;

    filtros;

    pesquisa;

    tipo;

    paginação;

    apresentação dos cards;

    imagens;

    informações monetárias.

44. Teste de Solicitações

O fluxo de solicitações foi validado integrado ao backend.

Foram verificadas as operações necessárias para que usuários possam
interagir com anúncios disponibilizados na plataforma.
45. Testes em Ambiente Local

Durante o desenvolvimento, o frontend foi executado através do servidor
de desenvolvimento do Angular.

Comando:

ng serve

Endereço padrão:

http://localhost:4200

Esse ambiente foi utilizado principalmente durante implementação e
diagnóstico.
46. Testes em Produção

Após o deploy, os fluxos foram novamente testados utilizando a aplicação
hospedada na Vercel.

Isso foi necessário porque o funcionamento local não garante
automaticamente o funcionamento em produção.

Foram analisados fatores como:

    URL da API;

    comunicação HTTPS;

    autenticação;

    persistência;

    tempo de resposta;

    carregamento da instância do backend;

    requisições HTTP;

    respostas de erro;

    integração com o banco remoto.

47. Ferramentas de Diagnóstico

Durante o desenvolvimento foram utilizadas as ferramentas de
desenvolvedor do navegador.

Principalmente:

Console
Network
XHR

A aba de rede foi utilizada para identificar:

    endpoint chamado;

    método HTTP;

    código de resposta;

    tempo da requisição;

    falhas de comunicação;

    requisições que não estavam sendo executadas;

    respostas da API.

Também foram utilizados logs temporários no frontend durante a
investigação de problemas.
48. Problemas Encontrados Durante a Integração

O desenvolvimento do frontend exigiu diagnóstico de diferentes problemas
de integração.

Entre os principais estiveram:
48.1 Endpoint de cadastro

Durante os primeiros testes em produção, a interface não conseguia
concluir corretamente o cadastro.

A investigação envolveu:

    inspeção da aba Network;

    verificação do endpoint;

    comparação entre ambiente local e produção;

    análise da API publicada;

    análise dos dados necessários no banco.

48.2 Perfil padrão inexistente

O cadastro dependia da existência do perfil:

USER

no banco de dados.

A ausência desse registro impedia a conclusão correta do cadastro.

Após a configuração adequada do banco, o fluxo pôde ser concluído.
48.3 Tempo de inicialização do backend

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
48.4 Configuração de ambiente

Foi necessário garantir que os serviços Angular utilizassem a URL
correta da API publicada em produção.

A configuração inadequada dos ambientes poderia fazer a aplicação
publicada continuar tentando acessar um backend local ou um endpoint
incorreto.
48.5 Listagem sem cards

Durante a etapa final de integração, foi identificado um cenário no qual
a API indicava a existência de anúncios, mas os cards não eram
apresentados corretamente.

O diagnóstico envolveu:

    AdsList;

    AdsService;

    resposta paginada;

    template HTML;

    propriedades do modelo Ad;

    categorias;

    imagens;

    estados isLoading e hasError.

Esse processo fez parte da validação final da integração entre os dados
retornados pela API e a renderização Angular.
49. Testes de Build

Antes da publicação definitiva, o frontend deve ser validado através do
processo de build.

Comando:

ng build

O objetivo é confirmar que:

    os componentes compilam;

    os templates são válidos;

    as dependências estão disponíveis;

    não existem erros TypeScript impeditivos;

    a versão de produção pode ser gerada.

50. Fluxo Funcional Validado

O fluxo principal da aplicação pode ser representado por:

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

Esse fluxo representa o núcleo funcional do frontend.
51. Integração Completa da Aplicação

A solução final não depende de um único processo local.

Os componentes são executados em serviços independentes:

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

Essa arquitetura permite demonstrar o funcionamento completo de uma
aplicação web utilizando frontend, backend e banco de dados publicados.
52. Requisitos Funcionais Atendidos pelo Frontend

Considerando as funcionalidades implementadas durante o desenvolvimento,
o frontend oferece suporte aos seguintes requisitos funcionais:

Requisito Implementação no frontend

Cadastro de usuário Tela e integração com API
Login Tela e integração com autenticação
Sessão autenticada Integração utilizando token
Navegação Angular Router
Visualização de anúncios Página de exploração
Pesquisa Busca por título
Filtro por categoria Implementado
Filtro por tipo Venda e doação
Categorias dinâmicas Obtidas através da API
Publicação de item Formulário de anúncio
Venda Suportada
Doação Suportada
Preço Tratado para anúncios de venda
Imagens Suporte a imagem principal
Meus anúncios Área específica
Detalhes de anúncio Rota individual
Solicitações Interface integrada ao fluxo
Perfil Área autenticada
Logout Disponível no cabeçalho
Paginação Implementada na exploração
Estados de carregamento Implementados
Tratamento de erro Implementado
Resultado vazio Implementado
Deploy Vercel
Integração com API publicada Implementada
53. Requisitos Não Funcionais

Além das funcionalidades, o desenvolvimento procurou atender
características importantes para a qualidade da aplicação.
53.1 Usabilidade

A interface utiliza navegação direta, identificação clara das ações e
feedback visual durante operações assíncronas.
53.2 Organização

Componentes, modelos e serviços foram separados de acordo com suas
responsabilidades.
53.3 Manutenibilidade

A separação entre componentes e serviços reduz o acoplamento entre
interface e comunicação HTTP.
53.4 Escalabilidade de código

A organização por funcionalidades permite adicionar novos módulos sem
concentrar toda a aplicação em poucos arquivos.
53.5 Integração

O frontend foi desenvolvido para operar com uma API REST independente.
53.6 Disponibilidade externa

A publicação na Vercel permite acesso ao frontend sem necessidade de
executar o Angular localmente.
54. Execução Local
54.1 Pré-requisitos

É necessário possuir:

Node.js
npm
Angular CLI

54.2 Instalar dependências

Dentro da pasta do frontend:

npm install

54.3 Executar

ng serve

ou:

npm start

quando o script correspondente estiver configurado no package.json.

A aplicação estará normalmente disponível em:

http://localhost:4200

55. Build de Produção

Para gerar uma versão de produção:

ng build

Os arquivos gerados podem então ser utilizados pela plataforma de
hospedagem.
56. Deploy do Frontend

O deploy foi realizado utilizando integração entre o repositório do
projeto e a Vercel.

Fluxo:

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

Isso facilita a atualização da versão publicada conforme novas
alterações são integradas ao repositório.
57. Validação Final Recomendada

Antes da entrega definitiva, deve ser realizado um teste completo
utilizando exclusivamente os serviços publicados.

O fluxo recomendado é:

    abrir o frontend publicado;

    criar uma nova conta;

    realizar login;

    acessar a página inicial;

    acessar a página de exploração;

    testar pesquisa;

    testar filtro por categoria;

    testar filtro por tipo;

    criar um anúncio de venda;

    criar um anúncio de doação;

    verificar a exibição dos anúncios;

    acessar os detalhes de um anúncio;

    verificar Meus Anúncios;

    realizar o fluxo de solicitação utilizando outro usuário, quando
    necessário;

    verificar a área de solicitações;

    verificar o perfil;

    realizar logout;

    atualizar a página e verificar o comportamento da sessão;

    testar a aplicação em uma largura de tela reduzida;

    verificar o console do navegador em busca de erros inesperados.

58. Evidências Recomendadas para o Relatório

Para documentar o funcionamento do frontend no relatório ou diário de
bordo, recomenda-se registrar capturas das seguintes telas:

    página inicial;

    cadastro;

    login;

    exploração de anúncios;

    filtros;

    formulário de criação de anúncio;

    anúncio publicado;

    Meus Anúncios;

    detalhes do anúncio;

    solicitações;

    perfil;

    frontend publicado na Vercel;

    requisição bem-sucedida na aba Network;

    registro persistido no banco após operação realizada pelo frontend.

Essas evidências demonstram não apenas a existência das telas, mas a
integração real entre as diferentes camadas da aplicação.
59. Critérios Técnicos Demonstrados

O desenvolvimento do frontend demonstra a aplicação prática de conceitos
de desenvolvimento web moderno, incluindo:

    desenvolvimento baseado em componentes;

    TypeScript;

    Angular;

    roteamento SPA;

    comunicação HTTP;

    consumo de API REST;

    autenticação JWT;

    gerenciamento de estado;

    programação reativa;

    formulários;

    validação;

    paginação;

    filtros;

    tratamento de erros;

    integração frontend/backend;

    integração com banco remoto;

    configuração de ambientes;

    deploy em nuvem;

    diagnóstico através das ferramentas do navegador;

    versionamento com Git.

60. Diferencial de Deploy

Além da execução local, o frontend foi disponibilizado em um serviço
real de hospedagem.

A utilização conjunta de:

Vercel
Render
TiDB Cloud

permite que a aplicação funcione através da Internet sem depender do
ambiente de desenvolvimento dos integrantes do projeto.

Essa característica demonstra o funcionamento integrado das três
principais camadas:

Interface
API
Persistência

61. Status do Frontend

O frontend encontra-se em etapa final de integração e validação.

As principais funcionalidades da aplicação foram implementadas,
incluindo:

    autenticação;

    cadastro;

    navegação;

    categorias;

    anúncios;

    venda;

    doação;

    filtros;

    pesquisa;

    paginação;

    gerenciamento dos próprios anúncios;

    solicitações;

    perfil;

    comunicação com a API;

    deploy.

A etapa final consiste principalmente na validação completa da
apresentação dos anúncios e imagens, revisão visual, execução dos testes
finais e registro das evidências para entrega.
62. Considerações Finais

O frontend do Segunda Chance foi desenvolvido não apenas como uma
coleção de telas, mas como uma aplicação Angular integrada a uma
infraestrutura real.

O sistema conecta:

Angular
   |
   v
API REST Spring Boot
   |
   v
TiDB

e possui implantação independente através de serviços de nuvem.

A interface procura representar a proposta central do projeto: facilitar
a circulação de objetos dentro da comunidade acadêmica, permitindo que
itens ainda úteis recebam uma segunda chance através da venda ou da
doação.

O resultado demonstra conhecimentos de desenvolvimento frontend,
integração de sistemas, consumo de APIs, autenticação, tratamento de
dados, deploy e diagnóstico de aplicações web.
63. Links
Frontend

https://segunda-chance-flax.vercel.app

API

https://segunda-chance-api.onrender.com

Repositório

Adicionar o endereço definitivo do repositório GitHub utilizado para a
entrega.
64. Observação sobre a Documentação Geral

Este documento descreve especificamente a camada de frontend do
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
PARTE III --- INTEGRAÇÃO E ENTREGA
Arquitetura Final

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

Links de Produção

Frontend:

https://segunda-chance-flax.vercel.app

Backend:

https://segunda-chance-api.onrender.com

Banco:

TiDB Cloud
Instância: segunda-chance-db
Database: segundachance

Adicionar ao documento final a URL definitiva do repositório GitHub.
Escopo deste README

Este README reúne a documentação técnica do produto: objetivo,
arquitetura, frontend, backend, banco, autenticação, funcionalidades,
tecnologias, testes, execução, Docker, deploy, integração, dificuldades,
soluções e evidências.

O diário de bordo deve permanecer separado, conforme solicitado, e
deve registrar a evolução cronológica, datas, atividades, divisão do
trabalho e histórico de desenvolvimento.
Observação sobre o edital

Este documento consolida os requisitos técnicos que estão sustentados
pelo material e pelo desenvolvimento realizado. Caso o edital oficial
exija campos formais adicionais --- como identificação completa da
equipe, turma, professor, vídeo demonstrativo, licença ou algum formato
específico --- esses itens devem ser adicionados conforme o texto
oficial. Nenhum requisito não fornecido foi inventado.
Considerações Finais

O Segunda Chance foi levado de um ambiente local a uma arquitetura
publicada e distribuída. O Angular fornece a interface; o Spring Boot
centraliza regras, segurança e API; o TiDB mantém a persistência; Vercel
e Render disponibilizam as aplicações na Internet.

A solução demonstra integração real entre frontend, API REST,
autenticação JWT, banco remoto, Docker, configuração de ambientes,
versionamento e deploy em nuvem.
Biblioteca
/README_SEGUNDA_CHANCE_COMPLETO.md
Segunda Chance --- Documentação Técnica
1. Visão Geral

O Segunda Chance é uma plataforma web desenvolvida para a comunidade
acadêmica da Universidade de Fortaleza (Unifor), destinada à compra,
venda e doação de objetos usados. A proposta incentiva economia
circular, reutilização, sustentabilidade e redução de desperdício.

A solução final foi implantada de forma distribuída:

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

O frontend e o backend possuem deploy independente e a aplicação
publicada não depende da máquina de desenvolvimento permanecer ligada.
PARTE I --- BACKEND
2. Objetivo do Backend

O backend implementa a API REST do Segunda Chance. Ele concentra regras
de negócio, validação, autenticação, autorização, persistência e
integração com o banco.

Responsabilidades principais:

    cadastro e autenticação de usuários;

    autenticação JWT;

    proteção de rotas;

    usuários e perfis;

    categorias;

    anúncios de venda e doação;

    pesquisa, filtros e paginação;

    solicitações;

    imagens de anúncios;

    validação de dados;

    persistência JPA/Hibernate;

    comunicação com banco remoto;

    API pública para o frontend.

3. Stack do Backend

Tecnologia Uso

Java 21 Linguagem e runtime
Spring Boot 4.1.0 Framework principal
Spring Web MVC API REST
Spring Security Autenticação e autorização
Spring Data JPA Repositórios
Hibernate ORM
Bean Validation Validação de DTOs
JJWT Tokens JWT
MySQL Connector/J Driver JDBC
HikariCP Pool de conexões
Maven Dependências e build
Docker Containerização
Render Hospedagem da API
TiDB Cloud Banco remoto compatível com MySQL
MySQL Workbench Inspeção e validação do banco
Postman Testes manuais da API
4. Arquitetura em Camadas

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

O projeto utiliza DTOs para não expor diretamente as entidades JPA:

Request DTO -> Controller -> Service -> Entity/Repository -> Banco
Banco -> Service -> Response DTO -> Cliente

Estrutura conceitual:

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

5. Padrão DTO

Foi adotado o padrão RequestDTO e ResponseDTO. No CRUD de
categorias, por exemplo, foram implementados CategoryRequestDTO,
CategoryResponseDTO, CategoryService, CategoryController e
CategoryRepository.

Exemplo de validação aplicada:

@NotBlank(message = "O nome da categoria é obrigatório")
@Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
private String name;

Essa abordagem separa o contrato HTTP do modelo de persistência e
facilita validação e manutenção.
6. Autenticação e Segurança

A autenticação utiliza Spring Security e JWT. Os componentes
desenvolvidos para esse fluxo incluem:

SecurityConfig
JwtService
JwtAuthenticationFilter
CustomUserDetailsService
AuthController
UserRepository

Fluxo de login:

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

Requisições protegidas utilizam:

Authorization: Bearer <token>

A expiração configurada é de 86400000 ms, equivalente a 24 horas. O
segredo JWT é fornecido por variável de ambiente e não deve ser
versionado.

O cadastro também foi integrado ao frontend através de
/api/auth/register. Durante os testes foi identificado que o cadastro
dependia da existência do perfil padrão USER no banco; o dado
necessário foi incluído para permitir a conclusão do fluxo.
7. Categorias

As categorias são persistidas e fornecidas dinamicamente pela API:

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

Isso evita manter uma lista independente e divergente apenas no
frontend.
8. Anúncios

O recurso central da aplicação representa itens de VENDA ou DOACAO.
A integração utiliza informações como identificador, título, descrição,
categoria, tipo, preço, estado, proprietário e imagem principal.

A consulta de anúncios suporta parâmetros utilizados pelo frontend:

page
size
search
categoryId
tipo
status

A resposta paginada disponibiliza dados como content, number e
totalPages.

Foram integrados pesquisa por título, filtro por categoria, filtro por
tipo, estado e paginação. O frontend utiliza, entre outros, o estado
DISPONIVEL.
9. Solicitações e Imagens

O backend possui fluxo de solicitações para representar o interesse de
usuários nos itens anunciados. O módulo é consumido pela área de
solicitações do frontend.

Também existe integração para imagens de anúncios. No frontend,
AdImagesService consome os recursos correspondentes da API. A
correspondência entre a resposta da API e imagemPrincipalUrl foi
verificada durante os testes finais da listagem.
10. Banco de Dados
10.1 Desenvolvimento local

Durante o desenvolvimento foi utilizado MySQL local, inclusive com
configuração em localhost:3395. Essa URL não poderia ser utilizada
pelo backend hospedado.
10.2 Tentativa com Aiven

Foi inicialmente criado um MySQL remoto no Aiven. O serviço permaneceu
por tempo excessivo em Building e depois Rebuilding. Como isso
colocava o prazo da entrega em risco, a solução foi substituída.
10.3 Solução final: TiDB Cloud

Foi adotado o TiDB Cloud, compatível com o protocolo MySQL. A instância
final foi denominada:

segunda-chance-db

O endpoint utilizado foi:

gateway01.sa-east-1.prod.aws.tidbcloud.com

Porta:

4000

A conexão pública exige TLS.

O banco da aplicação foi criado com:

CREATE DATABASE IF NOT EXISTS segundachance;

e validado com:

SHOW DATABASES;
USE segundachance;
SELECT DATABASE();

O resultado de SELECT DATABASE() confirmou segundachance.
11. Cliente MySQL e Workbench

No Windows, mysql inicialmente não era reconhecido no PowerShell
porque o executável não estava no PATH. Foram localizados:

C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe
C:\Program Files\MySQL\MySQL Workbench 8.0\mysql.exe

Também foi corrigida uma confusão entre comandos de terminal e SQL:
mysql -h ... deve ser executado no PowerShell/CMD, enquanto dentro do
prompt mysql> são executados comandos como CREATE DATABASE, USE e
SELECT.

O MySQL Workbench foi utilizado para verificar a persistência no TiDB.
Exemplo:

SELECT user_id, name, email, role_id
FROM users
ORDER BY user_id DESC;

12. Configuração de Produção

O application.yml foi preparado para receber configurações por
variáveis de ambiente:

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

A URL JDBC de produção segue:

jdbc:mysql://gateway01.sa-east-1.prod.aws.tidbcloud.com:4000/segundachance?sslMode=REQUIRED

No Render foram configuradas:

SPRING_DATASOURCE_URL
SPRING_DATASOURCE_USERNAME
SPRING_DATASOURCE_PASSWORD
JWT_SECRET
SPRING_JPA_HIBERNATE_DDL_AUTO

Credenciais e segredos reais não devem constar no README nem no Git.
13. Porta em Produção

A configuração:

server:
  port: ${PORT:8080}

permite usar 8080 localmente e a porta fornecida pelo Render em
produção.
14. Docker, Maven e Build

O backend foi publicado no Render como Web Service baseado em Docker.

O build executou:

mvn clean package -DskipTests

O log confirmou compilação de 57 arquivos Java e geração de:

/app/target/segundachance-0.0.1-SNAPSHOT.jar

O Spring Boot Maven Plugin realizou o repackage. O log apresentou
BUILD SUCCESS, Spring Boot 4.1.0 e Java 21.0.11.

Fluxo:

GitHub -> Render -> Docker build -> Maven -> JAR -> Spring Boot

15. Deploy no Render

Serviço:

segunda-chance-api

URL pública:

https://segunda-chance-api.onrender.com

Durante as correções foram utilizadas opções de Manual Deploy como
Deploy latest commit, Clear build cache & deploy e reinicialização
do serviço.

Ao final, o Render confirmou:

Your service is live

O backend passou a funcionar independentemente da máquina local.
16. Problemas de Deploy e Soluções
16.1 Unable to determine Dialect without JDBC metadata

O Hibernate não conseguia obter metadados JDBC. A investigação levou à
revisão da SPRING_DATASOURCE_URL e da conexão remota.
16.2 Unknown database 'segundachance'

Depois de alcançar o TiDB, o backend falhou porque o database esperado
ainda não existia. Isso confirmou que host e conexão estavam chegando ao
servidor. A solução foi criar segundachance manualmente.
16.3 Falha encadeada do JPA

Com o EntityManagerFactory indisponível, falharam também dependências
como:

UserRepository
CustomUserDetailsService
JwtAuthenticationFilter

A causa raiz era a persistência, não o filtro JWT. A criação/correção do
banco permitiu a inicialização da cadeia.
16.4 No open ports detected

O Render exibiu essa mensagem enquanto o Spring encerrava antes de o
Tomcat permanecer ativo. A correção real foi resolver a falha JDBC; não
simplesmente trocar uma porta.
16.5 Provisionamento remoto

O Aiven foi abandonado após demora excessiva em Building/Rebuilding. O
TiDB foi adotado para reduzir o risco de atraso.
16.6 Importação pelo painel TiDB

A importação pelo painel não era o caminho mais rápido para o SQL local.
Foi preferida a conexão MySQL, criação do database e uso do
Hibernate/JPA para criar/atualizar a estrutura.
17. Testes do Backend

A validação ocorreu em três níveis:

I. Backend isolado
   Postman / requisições HTTP

II. Backend + banco
    API -> TiDB

III. Sistema completo
     Vercel -> Render -> TiDB

Foram realizados testes de:

    compilação Maven;

    inicialização Spring Boot;

    cadastro;

    persistência de usuário;

    login;

    JWT;

    rotas autenticadas;

    categorias;

    criação de anúncio;

    venda;

    doação;

    listagem;

    pesquisa;

    filtros;

    paginação;

    imagens;

    solicitações;

    persistência no banco;

    integração em produção.

Os testes de produção foram importantes porque o funcionamento local não
garante que URL, HTTPS, CORS, credenciais, JWT e banco remoto estejam
corretos.
18. Estratégia de Diagnóstico

Foram utilizados:

    logs do Spring Boot;

    logs de deploy do Render;

    Postman;

    MySQL CLI;

    MySQL Workbench;

    DevTools do navegador;

    aba Network/XHR;

    consultas SQL;

    testes pelo frontend publicado.

Essa combinação permitiu localizar falhas entre interface, API,
autenticação, configuração de ambiente, JDBC e persistência.
19. Segurança e Boas Práticas

Foram aplicados:

    Spring Security;

    JWT;

    autenticação stateless;

    filtro JWT;

    DTOs;

    Bean Validation;

    credenciais por variáveis de ambiente;

    JWT_SECRET fora do código;

    TLS obrigatório com o TiDB;

    separação entre ambientes;

    banco acessado somente pelo backend;

    separação Controller/Service/Repository;

    configuração de porta adequada ao ambiente.

20. Execução Local do Backend

Pré-requisitos:

Java 21
Maven
MySQL ou banco compatível

Variáveis necessárias:

SPRING_DATASOURCE_URL
SPRING_DATASOURCE_USERNAME
SPRING_DATASOURCE_PASSWORD
JWT_SECRET
SPRING_JPA_HIBERNATE_DDL_AUTO

Executar:

mvn spring-boot:run

Build:

mvn clean package

ou:

mvn clean package -DskipTests

Sem PORT, a aplicação usa 8080.
21. Requisitos Funcionais Atendidos pelo Backend

Requisito Implementação

Cadastro API + persistência
Login Spring Security
Autenticação JWT
Rotas protegidas Security + filtro JWT
Usuários Service/Repository
Categorias API e banco
Anúncios API de gerenciamento
Venda e doação Tipos de anúncio
Pesquisa API
Filtros Categoria, tipo e estado
Paginação Resposta paginada
Solicitações Fluxo integrado
Imagens Integração de anúncio
Validação Bean Validation
Persistência JPA/Hibernate
Banco remoto TiDB Cloud
Deploy Render
Integração Frontend Vercel
Configuração segura Variáveis de ambiente
22. Requisitos Não Funcionais

Segurança: JWT, Spring Security, TLS e segredos externos ao código.

Manutenibilidade: separação em controllers, services, repositories,
DTOs, entities e security.

Desacoplamento: frontend, backend e persistência são camadas
independentes.

Portabilidade: aplicação empacotada em JAR e executada em Docker.

Disponibilidade externa: API publicada no Render.

Escalabilidade de código: organização por responsabilidades e
recursos.
23. Evidências Recomendadas

Para a entrega, manter evidências de:

    BUILD SUCCESS;

    Render com serviço ativo;

    Your service is live;

    URL pública da API;

    banco segundachance no TiDB;

    conexão no Workbench;

    tabelas;

    usuário persistido;

    categorias;

    anúncio;

    solicitação;

    cadastro pelo frontend;

    login e JWT;

    requisição protegida;

    listagem e filtros;

    venda e doação;

    integração Vercel -> Render -> TiDB.

24. Infraestrutura Final

Camada Tecnologia Hospedagem

Frontend Angular / TypeScript / SCSS Vercel
Backend Java 21 / Spring Boot 4.1 Render
Persistência TiDB compatível com MySQL TiDB Cloud
Segurança Spring Security + JWT Backend
ORM JPA / Hibernate Backend
Build Maven + Docker Render
25. Status do Backend

Ao final do processo documentado:

    build concluído;

    JAR gerado;

    imagem Docker construída;

    Spring Boot inicializado;

    conexão Render -> TiDB configurada;

    database segundachance criado;

    variáveis de ambiente configuradas;

    API publicada;

    persistência validada;

    integração com o frontend disponível.

API:

https://segunda-chance-api.onrender.com

26. Checklist Final do Backend

    cadastrar novo usuário;

    confirmar persistência;

    realizar login;

    confirmar JWT;

    testar rota protegida;

    listar categorias;

    listar e pesquisar anúncios;

    testar filtros e paginação;

    criar venda;

    criar doação;

    testar imagens;

    testar solicitações;

    confirmar respostas para dados inválidos;

    confirmar ausência de segredos no Git;

    executar o fluxo usando apenas serviços publicados.

PARTE II --- FRONTEND
1. Visão Geral

O frontend do Segunda Chance corresponde à interface web da
plataforma desenvolvida para a comunidade acadêmica da Universidade de
Fortaleza (Unifor).

A aplicação tem como objetivo permitir que estudantes, professores e
colaboradores encontrem uma nova finalidade para objetos que não
utilizam mais, possibilitando a publicação de itens para venda ou
doação e incentivando princípios de economia circular, reutilização e
sustentabilidade dentro do ambiente acadêmico.

O frontend foi desenvolvido como uma aplicação web independente,
responsável pela interação entre o usuário e a API REST do Segunda
Chance.

A aplicação permite realizar operações como:

    cadastro de usuário;

    autenticação;

    navegação pela plataforma;

    consulta de anúncios;

    pesquisa de anúncios;

    filtragem por categoria;

    filtragem por tipo de anúncio;

    publicação de anúncios;

    publicação de itens para venda;

    publicação de itens para doação;

    visualização dos próprios anúncios;

    consulta aos detalhes de um anúncio;

    gerenciamento de solicitações;

    acesso às informações do usuário;

    encerramento da sessão.

O frontend foi desenvolvido utilizando Angular e posteriormente
publicado em ambiente de produção através da Vercel.
2. Objetivo do Frontend

O frontend foi desenvolvido com o objetivo de fornecer uma interface
simples, organizada e responsiva para utilização das funcionalidades
disponibilizadas pelo backend.

A aplicação atua como cliente da API REST do projeto.

Sua responsabilidade principal consiste em:

    receber as ações realizadas pelo usuário;

    validar os dados necessários na interface;

    realizar requisições HTTP para a API;

    processar as respostas retornadas pelo backend;

    apresentar as informações de forma compreensível;

    controlar o estado da sessão autenticada;

    organizar a navegação entre as funcionalidades da plataforma.

A arquitetura mantém o frontend desacoplado do backend.

Dessa forma, a aplicação Angular e a API Spring Boot podem ser
desenvolvidas, executadas e publicadas separadamente.
3. Tecnologias Utilizadas
3.1 Angular

O Angular foi utilizado como principal framework de desenvolvimento do
frontend.

A aplicação utiliza componentes para separar as diferentes áreas e
responsabilidades da interface.

Entre os recursos utilizados estão:

    componentes standalone;

    templates HTML;

    SCSS;

    serviços;

    injeção de dependências;

    roteamento;

    formulários;

    requisições HTTP;

    Signals;

    controle de estado local;

    diretivas e estruturas condicionais do Angular.

3.2 TypeScript

O TypeScript foi utilizado para implementação da lógica da aplicação.

Sua utilização permitiu definir modelos tipados para representar as
informações recebidas da API, reduzindo inconsistências entre frontend e
backend.

Entre os objetos representados no frontend encontram-se:

    usuários;

    anúncios;

    categorias;

    solicitações;

    autenticação;

    imagens de anúncios;

    respostas paginadas.

3.3 HTML

Os templates HTML são responsáveis pela estrutura visual das páginas.

Foram utilizadas estruturas de template do Angular para:

    renderização condicional;

    iteração de anúncios;

    iteração de categorias;

    estados de carregamento;

    estados de erro;

    estados sem resultados;

    paginação;

    exibição dinâmica de dados.

3.4 SCSS

O SCSS foi utilizado para construção da identidade visual e organização
dos estilos da aplicação.

A interface foi construída procurando manter consistência entre:

    cores;

    espaçamentos;

    tipografia;

    botões;

    campos;

    cards;

    filtros;

    cabeçalho;

    páginas de autenticação;

    páginas de gerenciamento;

    mensagens de estado.

3.5 Angular HttpClient

A comunicação com o backend é realizada através do cliente HTTP do
Angular.

O frontend consome os endpoints REST disponibilizados pela API para
operações relacionadas a:

    autenticação;

    usuários;

    anúncios;

    categorias;

    solicitações;

    imagens.

3.6 Vercel

A Vercel foi utilizada para publicação do frontend em ambiente de
produção.

O deploy separado do frontend demonstra a separação entre as camadas da
aplicação.

A aplicação publicada pode consumir a API hospedada externamente através
da URL configurada no ambiente de produção.
4. Arquitetura do Frontend

O projeto foi organizado procurando separar interface, regras de
apresentação, comunicação HTTP e modelos.

A estrutura geral segue o princípio:

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

Os componentes não realizam diretamente a implementação das chamadas
HTTP.

Essa responsabilidade é delegada aos serviços.

Exemplo:

AdsList
   |
   v
AdsService
   |
   v
API Segunda Chance

Esse modelo melhora:

    organização;

    manutenção;

    reutilização;

    separação de responsabilidades;

    legibilidade;

    possibilidade de evolução futura.

5. Organização do Projeto

A aplicação foi dividida conceitualmente em áreas responsáveis pelas
diferentes funcionalidades.

Uma estrutura simplificada pode ser representada por:

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

Dentro das funcionalidades relacionadas aos anúncios, foram utilizados
elementos como:

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

A estrutura permite manter próximas as classes relacionadas a uma mesma
funcionalidade.
6. Modelos

O frontend utiliza interfaces e modelos TypeScript para representar os
dados recebidos do backend.

Entre os principais modelos utilizados encontram-se:
6.1 Anúncio

Representa um item disponibilizado na plataforma.

Entre as informações utilizadas pela interface estão:

    identificador;

    título;

    descrição;

    tipo;

    preço;

    categoria;

    imagem;

    estado do anúncio.

O tipo pode representar, por exemplo:

VENDA
DOACAO

6.2 Categoria

As categorias são carregadas dinamicamente através da API.

Cada categoria possui um identificador utilizado internamente pelo
backend e um nome apresentado ao usuário.

Exemplo conceitual:

{
  "id": 1,
  "name": "Acessórios"
}

7. Categorias

As categorias disponíveis na interface são obtidas do banco de dados
através da API.

Isso evita manter uma lista fixa somente no frontend.

Foram cadastradas categorias destinadas a representar os principais
tipos de objetos utilizados dentro da comunidade acadêmica.

Entre elas:

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

A categoria Outros é mantida ao final da apresentação para
representar itens que não pertencem às categorias específicas.
8. Sistema de Autenticação

O frontend possui páginas destinadas ao cadastro e autenticação dos
usuários.
8.1 Cadastro

A tela de cadastro permite fornecer as informações necessárias para
criação da conta.

O fluxo realizado é:

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

A interface apresenta ao usuário informações sobre o estado da operação.

Entre os estados previstos estão:

Criar conta
Criando conta...
Conta criada
Erro ao criar conta

8.2 Login

Após possuir uma conta, o usuário pode realizar autenticação através da
página de login.

O frontend envia as credenciais para a API.

Após autenticação válida, o token recebido é utilizado para identificar
as requisições que exigem autenticação.

Fluxo conceitual:

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

9. Integração com JWT

A autenticação entre frontend e backend utiliza JSON Web Token.

Após o login, as requisições para recursos protegidos devem enviar o
token através do cabeçalho HTTP:

Authorization: Bearer <token>

Isso permite que a API identifique o usuário responsável pela operação.

Esse mecanismo é utilizado em operações que dependem da identidade do
usuário, como gerenciamento de anúncios e solicitações.
10. Página Inicial

A página inicial foi desenvolvida para apresentar o conceito do Segunda
Chance e facilitar o acesso às principais funcionalidades.

A identidade da aplicação foi construída em torno da mensagem:

    Dar uma nova vida aos objetos.

A página comunica os três principais comportamentos esperados na
plataforma:
10.1 Compre

Permite encontrar itens disponibilizados por outros integrantes da
comunidade.
10.2 Venda

Permite dar um novo destino a objetos que o usuário não utiliza mais.
10.3 Doe

Permite disponibilizar gratuitamente um objeto para outro integrante da
comunidade.

A interface foi construída para não apresentar o Segunda Chance
simplesmente como uma loja virtual tradicional, mas como uma plataforma
de circulação e reaproveitamento de objetos.
11. Identidade Visual

A identidade visual do frontend foi desenvolvida especificamente para o
projeto Segunda Chance.

Foram considerados os seguintes conceitos:

    sustentabilidade;

    confiança;

    comunidade;

    colaboração;

    simplicidade;

    organização;

    inovação;

    reutilização;

    continuidade.

A aplicação utiliza uma identidade visual consistente nas páginas e
componentes.

Também foram produzidos elementos visuais específicos para:

    marca Segunda Chance;

    símbolo utilizado no cabeçalho;

    representação de compra;

    representação de venda;

    representação de doação.

12. Cabeçalho e Navegação

Após a autenticação, o usuário possui acesso ao cabeçalho principal da
aplicação.

Entre as opções de navegação encontram-se:

Início
Explorar
Meus anúncios
Solicitações
Perfil
Anunciar item
Sair

O cabeçalho foi implementado como componente compartilhado para evitar
repetição entre as páginas.
13. Exploração de Anúncios

A página de exploração é responsável por apresentar os anúncios
disponíveis na plataforma.

O frontend consulta a API e utiliza a resposta paginada para construir
dinamicamente os cards.

O carregamento é realizado através do serviço responsável pelos
anúncios.

Exemplo conceitual:

this.adsService.getAds(...)

A resposta é utilizada para atualizar:

content
number
totalPages

Assim, o frontend consegue controlar:

    anúncios da página atual;

    página selecionada;

    quantidade de páginas;

    navegação entre páginas.

14. Pesquisa de Anúncios

A página de exploração possui um campo de pesquisa por título.

O usuário pode informar um termo e executar a busca.

A interface envia o valor para o serviço de anúncios, que realiza a
consulta correspondente na API.

Exemplo de interface:

Buscar por título...
[ Buscar ]

A pesquisa pode ser utilizada em conjunto com os filtros.
15. Filtro por Categoria

O frontend carrega as categorias através do serviço de categorias.

A categoria selecionada é armazenada utilizando um Signal:

selectedCategoryId

O identificador é posteriormente enviado na consulta de anúncios.

A interface oferece também a opção:

Todas

que remove o filtro de categoria.
16. Filtro por Tipo

Os anúncios podem ser filtrados pelo tipo da publicação.

A interface disponibiliza:

Todos
Venda
Doação

Internamente, os valores utilizados são compatíveis com os valores
esperados pela API:

VENDA
DOACAO

17. Combinação de Filtros

A tela foi construída para permitir a combinação entre:

    termo de pesquisa;

    categoria;

    tipo.

Assim, o usuário pode realizar uma consulta mais específica.

Exemplo:

Pesquisa: notebook
Categoria: Computação
Tipo: Venda

A aplicação também possui a opção:

Limpar filtros

responsável por restaurar o estado inicial da consulta.
18. Compatibilidade com Parâmetros da URL

A página de anúncios também considera parâmetros recebidos através da
URL.

Por exemplo:

/ads?category=Livros

Quando uma categoria é recebida pelo nome, o frontend procura a
categoria correspondente entre aquelas retornadas pela API.

Em seguida, utiliza seu identificador real para executar a consulta.

Essa solução permite integrar links da página inicial com o sistema de
categorias baseado em IDs utilizado pelo backend.
19. Paginação

A listagem de anúncios possui suporte à paginação retornada pelo
backend.

O frontend mantém os Signals:

currentPage
totalPages

A interface disponibiliza controles:

Anterior
Página X de Y
Próxima

Os botões são automaticamente desabilitados quando não existe uma página
anterior ou posterior.
20. Estados da Interface

Uma preocupação durante o desenvolvimento foi não deixar a interface sem
retorno durante operações assíncronas.

Por isso, foram implementados diferentes estados visuais.
20.1 Carregamento

Exemplo:

Carregando anúncios...

Também foram utilizados indicadores visuais de carregamento.
20.2 Erro

Caso uma requisição falhe, a interface apresenta uma mensagem ao
usuário.

Exemplo:

Não foi possível carregar os anúncios.
Tente novamente em alguns instantes.

A página também pode disponibilizar:

Tentar novamente

20.3 Resultado vazio

Caso a consulta seja concluída corretamente, mas nenhum anúncio
corresponda aos filtros, é apresentado:

Nenhum anúncio encontrado.
Tente alterar os filtros ou realizar uma nova busca.

20.4 Resultado disponível

Quando existem anúncios, os dados retornados são convertidos em cards.
21. Cards de Anúncios

Cada anúncio é apresentado em um card contendo as informações
necessárias para identificação rápida do item.

Entre os dados apresentados estão:

    imagem;

    categoria;

    tipo;

    título;

    descrição;

    preço.

Quando o anúncio corresponde a uma doação, a interface apresenta:

Doação

em vez de um valor monetário.

Para anúncios de venda, o valor é formatado utilizando o padrão
brasileiro:

ad.preco.toLocaleString('pt-BR', {
  style: 'currency',
  currency: 'BRL',
});

22. Tratamento de Imagens

Os anúncios possuem suporte à exibição de uma imagem principal.

Quando existe uma URL de imagem válida, o frontend utiliza:

<img [src]="ad.imagemPrincipalUrl" [alt]="ad.titulo" />

Caso uma imagem não esteja disponível, a interface pode utilizar um
placeholder contendo a categoria do item.

Essa abordagem evita que o layout do card seja quebrado quando um
anúncio não possuir imagem disponível.
23. Publicação de Anúncios

A plataforma possui uma tela específica para publicação de novos itens.

O usuário pode informar dados referentes ao anúncio e selecionar a
finalidade do item.

As opções principais são:

Vender
Doar

Para uma venda, o usuário pode informar um preço.

Para uma doação, o item é disponibilizado sem cobrança.
24. Seleção de Categoria no Cadastro de Anúncio

O formulário de publicação consulta as categorias existentes no backend.

O usuário pode selecionar uma categoria através de um campo de seleção.

Exemplo:

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

O identificador da categoria selecionada é enviado para a API durante a
criação do anúncio.
25. Meus Anúncios

A aplicação possui uma página destinada aos anúncios pertencentes ao
usuário autenticado.

Essa página permite separar a consulta geral da plataforma dos itens
publicados pelo próprio usuário.

A interface apresenta a quantidade de anúncios publicados e a listagem
correspondente.

Exemplo:

Meus anúncios

Gerencie os itens que você colocou em circulação na comunidade.

3 anúncios publicados

Essa área funciona como espaço de gerenciamento dos itens publicados
pelo usuário.
26. Detalhes do Anúncio

Ao selecionar um anúncio, a aplicação pode navegar para sua página
específica.

A navegação é realizada utilizando o identificador do anúncio.

Exemplo:

this.router.navigate(['/ads', id]);

Isso permite manter URLs individuais para os anúncios.
27. Solicitações

O frontend também possui integração com o fluxo de solicitações.

Esse fluxo permite representar o interesse de outro usuário em um item
disponibilizado na plataforma.

A área de solicitações faz parte da navegação autenticada e comunica-se
com os endpoints correspondentes da API.

O objetivo é permitir acompanhar as interações relacionadas aos itens
anunciados.
28. Perfil

A aplicação possui uma área destinada às informações do usuário
autenticado.

A página de perfil integra o conjunto de funcionalidades privadas da
aplicação e pode ser acessada através do cabeçalho.
29. Logout

O cabeçalho possui a opção:

Sair

Essa funcionalidade encerra a sessão do usuário no frontend e impede que
o token de autenticação continue sendo utilizado pela sessão encerrada.
30. Serviços

A comunicação com a API foi organizada em serviços específicos.

Entre os serviços utilizados durante o desenvolvimento encontram-se:

AuthService
AdsService
CategoriesService
AdImagesService

Outros serviços podem existir de acordo com a funcionalidade
correspondente.

Essa divisão evita centralizar todas as chamadas HTTP em uma única
classe.
31. AdsService

O serviço de anúncios centraliza as operações HTTP relacionadas aos
anúncios.

A página de exploração utiliza esse serviço para solicitar anúncios
considerando parâmetros como:

page
size
search
categoryId
tipo
status

Exemplo conceitual:

this.adsService.getAds(
  page,
  8,
  search,
  categoryId,
  tipo,
  'DISPONIVEL'
);

32. CategoriesService

O serviço de categorias consulta as categorias disponibilizadas pela
API.

A resposta é utilizada em diferentes partes da aplicação, incluindo:

    filtros;

    formulário de criação de anúncio;

    identificação da categoria dos itens.

Isso evita duplicação da lista de categorias no frontend.
33. Signals

O projeto utiliza Signals do Angular para gerenciamento de estado local
dos componentes.

Na listagem de anúncios, por exemplo, são mantidos estados semelhantes
a:

readonly ads = signal<Ad[]>([]);
readonly categories = signal<Category[]>([]);

readonly isLoading = signal(true);
readonly hasError = signal(false);

readonly currentPage = signal(0);
readonly totalPages = signal(0);

readonly searchTerm = signal('');

readonly selectedCategoryId = signal<number | null>(null);
readonly selectedType = signal<'' | 'VENDA' | 'DOACAO'>('');

Essa estratégia facilita a atualização reativa da interface.
34. Tratamento de Operações Assíncronas

As chamadas HTTP são tratadas considerando sucesso e erro.

Exemplo conceitual:

service.getData().subscribe({
  next: (response) => {
    // processamento da resposta
  },

  error: (error) => {
    // tratamento da falha
  },
});

Essa estrutura foi utilizada para evitar que falhas de comunicação
deixassem a aplicação sem feedback.
35. Integração Frontend e Backend

Durante o desenvolvimento, o frontend foi inicialmente executado
localmente.

Exemplo:

http://localhost:4200

O backend também pode ser executado localmente durante o
desenvolvimento.

Posteriormente, a aplicação passou a utilizar a API publicada
externamente.

A arquitetura final pode ser representada por:

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

Essa configuração demonstra uma aplicação distribuída em múltiplos
serviços.
36. Configuração de Ambientes

O frontend utiliza arquivos de ambiente para evitar manter URLs
específicas diretamente nos serviços.

Conceitualmente:

environment.apiUrl

Isso permite diferenciar a API utilizada durante desenvolvimento da API
utilizada em produção.

Exemplo:

Desenvolvimento
Angular -> API local

Produção
Vercel -> API publicada no Render

Durante o processo de deploy, as configurações dos serviços do frontend
foram ajustadas para utilizar corretamente o ambiente de produção.
37. Deploy

O frontend foi publicado utilizando a Vercel.

A API foi publicada separadamente no Render.

Essa estratégia atende ao objetivo de disponibilizar uma aplicação real
acessível através da Internet.
37.1 Frontend

Hospedagem:

Vercel

Aplicação:

https://segunda-chance-flax.vercel.app

37.2 Backend

API REST hospedada externamente no Render.

O endereço da API utilizado durante os testes de produção foi:

https://segunda-chance-api.onrender.com

A documentação geral do projeto deverá manter os links atualizados caso
os endereços de produção sejam alterados.
38. Testes Realizados no Frontend

O frontend foi validado durante o desenvolvimento através de testes
manuais e testes de integração com a API.

Os testes não se limitaram à execução isolada das páginas.

Também foram realizados testes utilizando o frontend publicado e o
backend publicado.
39. Teste de Cadastro

Foi testado o fluxo:

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

A persistência foi verificada diretamente no banco de dados durante os
testes.
40. Validação do Banco Durante os Testes

Durante os testes de integração, o banco TiDB foi acessado através do
MySQL Workbench.

Foi possível verificar diretamente os registros persistidos.

Por exemplo:

SELECT user_id, name, email, role_id
FROM users
ORDER BY user_id DESC;

Esse procedimento permitiu confirmar que operações realizadas através do
frontend estavam efetivamente chegando ao backend e sendo persistidas no
banco.
41. Teste das Categorias

As categorias foram cadastradas no banco e posteriormente carregadas
pelo frontend.

Foram verificados:

    carregamento das categorias;

    apresentação no formulário;

    apresentação nos filtros;

    utilização do identificador correto;

    ordenação visual;

    utilização da categoria durante a criação do anúncio.

42. Teste de Criação de Anúncio

O fluxo de criação de anúncios foi testado através da interface.

Foram verificados:

Título
Descrição
Categoria
Tipo
Preço
Imagem

Também foram testadas as diferenças entre:

Venda
Doação

43. Teste da Listagem de Anúncios

A listagem foi testada utilizando os registros existentes na API.

Foram verificados:

    quantidade retornada;

    conteúdo da página;

    categorias;

    filtros;

    pesquisa;

    tipo;

    paginação;

    apresentação dos cards;

    imagens;

    informações monetárias.

44. Teste de Solicitações

O fluxo de solicitações foi validado integrado ao backend.

Foram verificadas as operações necessárias para que usuários possam
interagir com anúncios disponibilizados na plataforma.
45. Testes em Ambiente Local

Durante o desenvolvimento, o frontend foi executado através do servidor
de desenvolvimento do Angular.

Comando:

ng serve

Endereço padrão:

http://localhost:4200

Esse ambiente foi utilizado principalmente durante implementação e
diagnóstico.
46. Testes em Produção

Após o deploy, os fluxos foram novamente testados utilizando a aplicação
hospedada na Vercel.

Isso foi necessário porque o funcionamento local não garante
automaticamente o funcionamento em produção.

Foram analisados fatores como:

    URL da API;

    comunicação HTTPS;

    autenticação;

    persistência;

    tempo de resposta;

    carregamento da instância do backend;

    requisições HTTP;

    respostas de erro;

    integração com o banco remoto.

47. Ferramentas de Diagnóstico

Durante o desenvolvimento foram utilizadas as ferramentas de
desenvolvedor do navegador.

Principalmente:

Console
Network
XHR

A aba de rede foi utilizada para identificar:

    endpoint chamado;

    método HTTP;

    código de resposta;

    tempo da requisição;

    falhas de comunicação;

    requisições que não estavam sendo executadas;

    respostas da API.

Também foram utilizados logs temporários no frontend durante a
investigação de problemas.
48. Problemas Encontrados Durante a Integração

O desenvolvimento do frontend exigiu diagnóstico de diferentes problemas
de integração.

Entre os principais estiveram:
48.1 Endpoint de cadastro

Durante os primeiros testes em produção, a interface não conseguia
concluir corretamente o cadastro.

A investigação envolveu:

    inspeção da aba Network;

    verificação do endpoint;

    comparação entre ambiente local e produção;

    análise da API publicada;

    análise dos dados necessários no banco.

48.2 Perfil padrão inexistente

O cadastro dependia da existência do perfil:

USER

no banco de dados.

A ausência desse registro impedia a conclusão correta do cadastro.

Após a configuração adequada do banco, o fluxo pôde ser concluído.
48.3 Tempo de inicialização do backend

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
48.4 Configuração de ambiente

Foi necessário garantir que os serviços Angular utilizassem a URL
correta da API publicada em produção.

A configuração inadequada dos ambientes poderia fazer a aplicação
publicada continuar tentando acessar um backend local ou um endpoint
incorreto.
48.5 Listagem sem cards

Durante a etapa final de integração, foi identificado um cenário no qual
a API indicava a existência de anúncios, mas os cards não eram
apresentados corretamente.

O diagnóstico envolveu:

    AdsList;

    AdsService;

    resposta paginada;

    template HTML;

    propriedades do modelo Ad;

    categorias;

    imagens;

    estados isLoading e hasError.

Esse processo fez parte da validação final da integração entre os dados
retornados pela API e a renderização Angular.
49. Testes de Build

Antes da publicação definitiva, o frontend deve ser validado através do
processo de build.

Comando:

ng build

O objetivo é confirmar que:

    os componentes compilam;

    os templates são válidos;

    as dependências estão disponíveis;

    não existem erros TypeScript impeditivos;

    a versão de produção pode ser gerada.

50. Fluxo Funcional Validado

O fluxo principal da aplicação pode ser representado por:

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

Esse fluxo representa o núcleo funcional do frontend.
51. Integração Completa da Aplicação

A solução final não depende de um único processo local.

Os componentes são executados em serviços independentes:

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

Essa arquitetura permite demonstrar o funcionamento completo de uma
aplicação web utilizando frontend, backend e banco de dados publicados.
52. Requisitos Funcionais Atendidos pelo Frontend

Considerando as funcionalidades implementadas durante o desenvolvimento,
o frontend oferece suporte aos seguintes requisitos funcionais:

Requisito Implementação no frontend

Cadastro de usuário Tela e integração com API
Login Tela e integração com autenticação
Sessão autenticada Integração utilizando token
Navegação Angular Router
Visualização de anúncios Página de exploração
Pesquisa Busca por título
Filtro por categoria Implementado
Filtro por tipo Venda e doação
Categorias dinâmicas Obtidas através da API
Publicação de item Formulário de anúncio
Venda Suportada
Doação Suportada
Preço Tratado para anúncios de venda
Imagens Suporte a imagem principal
Meus anúncios Área específica
Detalhes de anúncio Rota individual
Solicitações Interface integrada ao fluxo
Perfil Área autenticada
Logout Disponível no cabeçalho
Paginação Implementada na exploração
Estados de carregamento Implementados
Tratamento de erro Implementado
Resultado vazio Implementado
Deploy Vercel
Integração com API publicada Implementada
53. Requisitos Não Funcionais

Além das funcionalidades, o desenvolvimento procurou atender
características importantes para a qualidade da aplicação.
53.1 Usabilidade

A interface utiliza navegação direta, identificação clara das ações e
feedback visual durante operações assíncronas.
53.2 Organização

Componentes, modelos e serviços foram separados de acordo com suas
responsabilidades.
53.3 Manutenibilidade

A separação entre componentes e serviços reduz o acoplamento entre
interface e comunicação HTTP.
53.4 Escalabilidade de código

A organização por funcionalidades permite adicionar novos módulos sem
concentrar toda a aplicação em poucos arquivos.
53.5 Integração

O frontend foi desenvolvido para operar com uma API REST independente.
53.6 Disponibilidade externa

A publicação na Vercel permite acesso ao frontend sem necessidade de
executar o Angular localmente.
54. Execução Local
54.1 Pré-requisitos

É necessário possuir:

Node.js
npm
Angular CLI

54.2 Instalar dependências

Dentro da pasta do frontend:

npm install

54.3 Executar

ng serve

ou:

npm start

quando o script correspondente estiver configurado no package.json.

A aplicação estará normalmente disponível em:

http://localhost:4200

55. Build de Produção

Para gerar uma versão de produção:

ng build

Os arquivos gerados podem então ser utilizados pela plataforma de
hospedagem.
56. Deploy do Frontend

O deploy foi realizado utilizando integração entre o repositório do
projeto e a Vercel.

Fluxo:

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

Isso facilita a atualização da versão publicada conforme novas
alterações são integradas ao repositório.
57. Validação Final Recomendada

Antes da entrega definitiva, deve ser realizado um teste completo
utilizando exclusivamente os serviços publicados.

O fluxo recomendado é:

    abrir o frontend publicado;

    criar uma nova conta;

    realizar login;

    acessar a página inicial;

    acessar a página de exploração;

    testar pesquisa;

    testar filtro por categoria;

    testar filtro por tipo;

    criar um anúncio de venda;

    criar um anúncio de doação;

    verificar a exibição dos anúncios;

    acessar os detalhes de um anúncio;

    verificar Meus Anúncios;

    realizar o fluxo de solicitação utilizando outro usuário, quando
    necessário;

    verificar a área de solicitações;

    verificar o perfil;

    realizar logout;

    atualizar a página e verificar o comportamento da sessão;

    testar a aplicação em uma largura de tela reduzida;

    verificar o console do navegador em busca de erros inesperados.

58. Evidências Recomendadas para o Relatório

Para documentar o funcionamento do frontend no relatório ou diário de
bordo, recomenda-se registrar capturas das seguintes telas:

    página inicial;

    cadastro;

    login;

    exploração de anúncios;

    filtros;

    formulário de criação de anúncio;

    anúncio publicado;

    Meus Anúncios;

    detalhes do anúncio;

    solicitações;

    perfil;

    frontend publicado na Vercel;

    requisição bem-sucedida na aba Network;

    registro persistido no banco após operação realizada pelo frontend.

Essas evidências demonstram não apenas a existência das telas, mas a
integração real entre as diferentes camadas da aplicação.
59. Critérios Técnicos Demonstrados

O desenvolvimento do frontend demonstra a aplicação prática de conceitos
de desenvolvimento web moderno, incluindo:

    desenvolvimento baseado em componentes;

    TypeScript;

    Angular;

    roteamento SPA;

    comunicação HTTP;

    consumo de API REST;

    autenticação JWT;

    gerenciamento de estado;

    programação reativa;

    formulários;

    validação;

    paginação;

    filtros;

    tratamento de erros;

    integração frontend/backend;

    integração com banco remoto;

    configuração de ambientes;

    deploy em nuvem;

    diagnóstico através das ferramentas do navegador;

    versionamento com Git.

60. Diferencial de Deploy

Além da execução local, o frontend foi disponibilizado em um serviço
real de hospedagem.

A utilização conjunta de:

Vercel
Render
TiDB Cloud

permite que a aplicação funcione através da Internet sem depender do
ambiente de desenvolvimento dos integrantes do projeto.

Essa característica demonstra o funcionamento integrado das três
principais camadas:

Interface
API
Persistência

61. Status do Frontend

O frontend encontra-se em etapa final de integração e validação.

As principais funcionalidades da aplicação foram implementadas,
incluindo:

    autenticação;

    cadastro;

    navegação;

    categorias;

    anúncios;

    venda;

    doação;

    filtros;

    pesquisa;

    paginação;

    gerenciamento dos próprios anúncios;

    solicitações;

    perfil;

    comunicação com a API;

    deploy.

A etapa final consiste principalmente na validação completa da
apresentação dos anúncios e imagens, revisão visual, execução dos testes
finais e registro das evidências para entrega.
62. Considerações Finais

O frontend do Segunda Chance foi desenvolvido não apenas como uma
coleção de telas, mas como uma aplicação Angular integrada a uma
infraestrutura real.

O sistema conecta:

Angular
   |
   v
API REST Spring Boot
   |
   v
TiDB

e possui implantação independente através de serviços de nuvem.

A interface procura representar a proposta central do projeto: facilitar
a circulação de objetos dentro da comunidade acadêmica, permitindo que
itens ainda úteis recebam uma segunda chance através da venda ou da
doação.

O resultado demonstra conhecimentos de desenvolvimento frontend,
integração de sistemas, consumo de APIs, autenticação, tratamento de
dados, deploy e diagnóstico de aplicações web.
63. Links
Frontend

https://segunda-chance-flax.vercel.app

API

https://segunda-chance-api.onrender.com

Repositório

Adicionar o endereço definitivo do repositório GitHub utilizado para a
entrega.
64. Observação sobre a Documentação Geral

Este documento descreve especificamente a camada de frontend do
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
PARTE III --- INTEGRAÇÃO E ENTREGA
Arquitetura Final

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

Links de Produção

Frontend:

https://segunda-chance-flax.vercel.app

Backend:

https://segunda-chance-api.onrender.com

Banco:

TiDB Cloud
Instância: segunda-chance-db
Database: segundachance

Adicionar ao documento final a URL definitiva do repositório GitHub.
Escopo deste README

Este README reúne a documentação técnica do produto: objetivo,
arquitetura, frontend, backend, banco, autenticação, funcionalidades,
tecnologias, testes, execução, Docker, deploy, integração, dificuldades,
soluções e evidências.

O diário de bordo deve permanecer separado, conforme solicitado, e
deve registrar a evolução cronológica, datas, atividades, divisão do
trabalho e histórico de desenvolvimento.
Observação sobre o edital

Este documento consolida os requisitos técnicos que estão sustentados
pelo material e pelo desenvolvimento realizado. Caso o edital oficial
exija campos formais adicionais --- como identificação completa da
equipe, turma, professor, vídeo demonstrativo, licença ou algum formato
específico --- esses itens devem ser adicionados conforme o texto
oficial. Nenhum requisito não fornecido foi inventado.
Considerações Finais

O Segunda Chance foi levado de um ambiente local a uma arquitetura
publicada e distribuída. O Angular fornece a interface; o Spring Boot
centraliza regras, segurança e API; o TiDB mantém a persistência; Vercel
e Render disponibilizam as aplicações na Internet.

A solução demonstra integração real entre frontend, API REST,
autenticação JWT, banco remoto, Docker, configuração de ambientes,
versionamento e deploy em nuvem.
