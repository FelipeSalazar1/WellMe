# Guia de Importação - Insomnia Collection

## 📥 Como Importar a Collection

1. Abra o **Insomnia**
2. Clique em **Application** → **Preferences** (ou `Ctrl+,`)
3. Vá na aba **Data** → **Import Data**
4. Selecione **From File**
5. Escolha o arquivo `Insomnia_WellMe_Collection.json`
6. A collection será importada automaticamente

## 📋 Estrutura da Collection

A collection está organizada em pastas:

### 📁 **Pacientes**

- `GET` Listar Todos os Pacientes
- `GET` Buscar Paciente por ID
- `POST` Criar Novo Paciente
- `PUT` Atualizar Paciente
- `DELETE` Deletar Paciente

### 📁 **Consultas**

- `GET` Listar Todas as Consultas
- `GET` Buscar Consulta por ID
- `GET` Listar Consultas por Paciente
- `POST` Criar Nova Consulta
- `PUT` Atualizar Consulta
- `DELETE` Deletar Consulta

### 📁 **Exames**

- `GET` Listar Todos os Exames
- `GET` Buscar Exame por ID
- `GET` Listar Exames por Paciente
- `POST` Criar Novo Exame
- `PUT` Atualizar Exame
- `DELETE` Deletar Exame

### 📁 **Lembretes**

- `GET` Listar Todos os Lembretes
- `GET` Buscar Lembrete por ID
- `GET` Listar Lembretes por Paciente
- `POST` Criar Novo Lembrete
- `PUT` Atualizar Lembrete
- `DELETE` Deletar Lembrete

### 📁 **Utilidades**

- `GET` Health Check

## 🔧 Variáveis de Ambiente

A collection inclui variáveis pré-configuradas:

- `base_url`: `http://localhost:8080`
- `paciente_id`: `1`
- `consulta_id`: `1`
- `exame_id`: `1`
- `lembrete_id`: `1`

### Como Alterar as Variáveis

1. Clique no menu **Environments** (canto superior direito)
2. Selecione **Base Environment**
3. Edite os valores conforme necessário

## 🚀 Como Usar

1. Certifique-se de que a aplicação está rodando (`http://localhost:8080`)
2. Selecione a requisição desejada
3. Ajuste os parâmetros se necessário (IDs, body, etc.)
4. Clique em **Send** para executar a requisição

## 💡 Dicas

- **IDs Dinâmicos**: Use as variáveis `{{ _.paciente_id }}` nos endpoints
- **Body Examples**: Cada requisição POST/PUT já vem com exemplos de body
- **Headers**: Todas as requisições já incluem `Content-Type: application/json`

## 📝 Exemplo de Fluxo

1. **Criar um Paciente** → Use "Criar Novo Paciente"
2. **Copiar o ID retornado** → Atualize a variável `paciente_id`
3. **Criar uma Consulta** → Use "Criar Nova Consulta" (já usará o `paciente_id`)
4. **Listar Consultas do Paciente** → Use "Listar Consultas por Paciente"

---

**Pronto para testar!** 🎉
