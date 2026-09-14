# Sistema Acadêmico — Semana 04

Evolução incremental do Projeto Integrador da disciplina Desenvolvimento Web 2.

## Novidades desta semana
- RequestDTO
- ResponseDTO
- Jakarta Validation
- @Valid
- @NotBlank
- @NotNull
- @Size
- @Email
- @Positive
- conversão DTO <-> Model

## Arquitetura
Cliente -> RequestDTO -> @Valid -> Controller -> Service -> Model -> memória

Na resposta:
Model -> ResponseDTO -> Cliente

## Swagger
http://localhost:8080/swagger-ui.html

## Próxima evolução
Semana 05: Spring Data JPA + Hibernate + PostgreSQL + Flyway.


