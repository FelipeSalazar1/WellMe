# WellMe - Assistente de Saúde Preventiva

Sistema de gerenciamento de saúde preventiva desenvolvido com arquitetura SOA (Service-Oriented Architecture) e APIs RESTful.

## 📋 Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Tecnologias](#tecnologias)
- [Arquitetura](#arquitetura)
- [Requisitos](#requisitos)
- [Instalação e Execução](#instalação-e-execução)
- [Documentação da API](#documentação-da-api)
- [Exemplos de Uso](#exemplos-de-uso)

## 🎯 Sobre o Projeto

O WellMe é um sistema completo para gerenciamento de saúde preventiva que permite:

- Cadastro e gerenciamento de pacientes
- Agendamento e controle de consultas médicas
- Registro e acompanhamento de exames
- Sistema de lembretes para cuidados de saúde

## 🛠 Tecnologias

- **Java 21**
- **Spring Boot 4.0.0**
- **Spring Data JPA**
- **H2 Database** (banco em memória)
- **Flyway** (controle de migrações)
- **Lombok** (redução de boilerplate)
- **Bean Validation** (validação de dados)

## 🏗 Arquitetura

O projeto segue os princípios de **Arquitetura Orientada a Serviços (SOA)** com separação clara de responsabilidades:

### Camadas da Aplicação

1. **Controller (Apresentação)**: Endpoints RESTful que recebem requisições HTTP
2. **Service (Negócio)**: Lógica de negócio e orquestração de serviços
3. **Repository (Dados)**: Acesso aos dados através do Spring Data JPA
4. **Domain (Domínio)**: Entidades e modelos de domínio

### Padrões Implementados

- **RESTful API**: Uso adequado de métodos HTTP (GET, POST, PUT, DELETE)
- **DTO Pattern**: Separação entre entidades de domínio e objetos de transferência
- **Exception Handling**: Tratamento centralizado de exceções
- **Validation**: Validação de entrada para prevenir injeções e erros

## ✅ Requisitos Atendidos

### Integração por Web Services (20%)
- ✅ Implementação de APIs RESTful para comunicação entre sistemas
- ✅ Uso adequado de métodos HTTP (GET, POST, PUT, DELETE)
- ✅ Documentação das APIs com README

### Arquitetura Orientada a Serviços (SOA) (20%)
- ✅ Organização modular baseada em serviços independentes e reutilizáveis
- ✅ Separação clara entre camadas de apresentação, serviço e dados

### Padrões e Boas Práticas (15%)
- ✅ Adoção de padrões REST, JSON
- ✅ Tratamento adequado de erros e exceções nos serviços

### Segurança em Web Services (15%)
- ✅ Validação de entrada para evitar injeções e outros ataques

### Interoperabilidade e Escalabilidade (15%)
- ✅ Capacidade dos serviços de se comunicarem com diferentes plataformas
- ✅ Design escalável e preparado para aumento de carga ou novas integrações

### Conexão com Banco de Dados (15%)
- ✅ Dependências e configurações para conexão
- ✅ Controle de migrações com Flyway

## 🚀 Instalação e Execução

### Pré-requisitos

- Java 21 ou superior
- Maven 3.6 ou superior

### Executando a Aplicação

1. Clone o repositório:
```bash
git clone <repository-url>
cd wellme
```

2. Execute a aplicação:
```bash
./mvnw spring-boot:run
```

Ou no Windows:
```bash
mvnw.cmd spring-boot:run
```

3. A aplicação estará disponível em: `http://localhost:8080`

4. Console H2 (para visualizar o banco): `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:wellme`
   - Username: `sa`
   - Password: (vazio)

## 📚 Documentação da API

### Base URL
```
http://localhost:8080/api/v1
```

### Endpoints

#### Pacientes

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/pacientes` | Lista todos os pacientes |
| GET | `/pacientes/{id}` | Busca paciente por ID |
| POST | `/pacientes` | Cria um novo paciente |
| PUT | `/pacientes/{id}` | Atualiza um paciente |
| DELETE | `/pacientes/{id}` | Remove um paciente |

#### Consultas

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/consultas` | Lista todas as consultas |
| GET | `/consultas/{id}` | Busca consulta por ID |
| GET | `/consultas/paciente/{pacienteId}` | Lista consultas de um paciente |
| POST | `/consultas` | Cria uma nova consulta |
| PUT | `/consultas/{id}` | Atualiza uma consulta |
| DELETE | `/consultas/{id}` | Remove uma consulta |

#### Exames

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/exames` | Lista todos os exames |
| GET | `/exames/{id}` | Busca exame por ID |
| GET | `/exames/paciente/{pacienteId}` | Lista exames de um paciente |
| POST | `/exames` | Cria um novo exame |
| PUT | `/exames/{id}` | Atualiza um exame |
| DELETE | `/exames/{id}` | Remove um exame |

#### Lembretes

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/lembretes` | Lista todos os lembretes |
| GET | `/lembretes/{id}` | Busca lembrete por ID |
| GET | `/lembretes/paciente/{pacienteId}` | Lista lembretes de um paciente |
| POST | `/lembretes` | Cria um novo lembrete |
| PUT | `/lembretes/{id}` | Atualiza um lembrete |
| DELETE | `/lembretes/{id}` | Remove um lembrete |

## 💡 Exemplos de Uso

### Criar um Paciente

**Request:**
```http
POST /api/v1/pacientes
Content-Type: application/json

{
  "nome": "João Silva",
  "cpf": "12345678901",
  "email": "joao.silva@email.com",
  "telefone": "11999999999",
  "dataNascimento": "1990-05-15",
  "sexo": "MASCULINO",
  "endereco": "Rua das Flores, 123",
  "observacoes": "Paciente com histórico de hipertensão"
}
```

**Response:**
```json
{
  "id": 1,
  "nome": "João Silva",
  "cpf": "12345678901",
  "email": "joao.silva@email.com",
  "telefone": "11999999999",
  "dataNascimento": "1990-05-15",
  "sexo": "MASCULINO",
  "endereco": "Rua das Flores, 123",
  "observacoes": "Paciente com histórico de hipertensão",
  "dataCadastro": "2024-01-15T10:30:00"
}
```

### Criar uma Consulta

**Request:**
```http
POST /api/v1/consultas
Content-Type: application/json

{
  "pacienteId": 1,
  "dataHora": "2024-02-01T14:00:00",
  "tipoConsulta": "Consulta de rotina",
  "medico": "Dr. Carlos Mendes",
  "observacoes": "Check-up anual"
}
```

### Criar um Exame

**Request:**
```http
POST /api/v1/exames
Content-Type: application/json

{
  "pacienteId": 1,
  "tipoExame": "Hemograma completo",
  "descricao": "Exame de sangue completo",
  "dataExame": "2024-01-20",
  "laboratorio": "Laboratório Central"
}
```

### Criar um Lembrete

**Request:**
```http
POST /api/v1/lembretes
Content-Type: application/json

{
  "pacienteId": 1,
  "titulo": "Tomar vacina da gripe",
  "descricao": "Lembrete para tomar a vacina anual da gripe",
  "dataLembrete": "2024-02-15T09:00:00",
  "tipo": "VACINA"
}
```

### Tratamento de Erros

A API retorna erros padronizados:

**Exemplo - Recurso não encontrado:**
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 404,
  "error": "Recurso não encontrado",
  "message": "Paciente não encontrado com ID: 999",
  "path": "/api/v1/pacientes/999"
}
```

**Exemplo - Erro de validação:**
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 400,
  "error": "Erro de validação",
  "message": "Dados de entrada inválidos",
  "path": "/api/v1/pacientes",
  "fieldErrors": [
    {
      "field": "nome",
      "message": "Nome é obrigatório",
      "rejectedValue": null
    },
    {
      "field": "cpf",
      "message": "CPF é obrigatório",
      "rejectedValue": null
    }
  ]
}
```

## 🔒 Segurança

- Validação de entrada em todos os endpoints
- Prevenção contra injeção SQL através do uso de JPA
- Validação de dados com Bean Validation
- Tratamento de exceções para evitar vazamento de informações

## 📝 Status dos Enums

### Status de Consulta
- `AGENDADA`
- `REALIZADA`
- `CANCELADA`
- `REMARCADA`

### Status de Exame
- `AGENDADO`
- `REALIZADO`
- `CANCELADO`
- `PENDENTE`

### Status de Lembrete
- `PENDENTE`
- `CONCLUIDO`
- `CANCELADO`

### Tipo de Lembrete
- `CONSULTA`
- `EXAME`
- `MEDICAMENTO`
- `VACINA`
- `OUTRO`

## 🧪 Testes

Para executar os testes:
```bash
./mvnw test
```

## 📄 Licença

Este projeto é um exemplo acadêmico desenvolvido para demonstrar conceitos de SOA e Web Services.

## 👥 Autor

Desenvolvido como projeto acadêmico seguindo os requisitos de Arquitetura Orientada a Serviços e Web Services.

