>Aula 10

# Cash API

## Visão Geral

Cash API é um serviço backend para gerenciar contas financeiras, transações e acesso protegido por OAuth2. O projeto fornece endpoints REST para manipular registros de contas, lançamentos de transações e autenticação de usuários. Este README descreve como instalar, configurar e usar a aplicação.

## Funcionalidades

- Autenticação e autorização OAuth2
- Operações CRUD para contas e transações
- Endpoints de API protegidos com tokens bearer
- Suporte a requisições/respostas JSON
- Scripts para desenvolvimento local e build de produção

## Começando

### Pré-requisitos

- Node.js 20 ou superior
- npm 10 ou superior
- Um banco de dados relacional suportado configurado no projeto

### Instalação

1. Clone o repositório.
2. Instale as dependências:
   npm install
3. Crie um arquivo `.env` ou copie o arquivo de exemplo de ambiente com as configurações necessárias.
4. Configure a conexão do banco de dados e as credenciais OAuth2.

### Executando a Aplicação

Start the application in development mode:

npm run dev

Build and run for production:

npm run build
npm start

## Documentação da API

A API expõe vários endpoints para gerenciamento de contas e transações. Rotas típicas incluem:

- `POST /oauth/token` - obter um token de acesso OAuth2
- `GET /accounts` - listar contas
- `POST /accounts` - criar uma nova conta
- `GET /transactions` - listar transações
- `POST /transactions` - criar uma nova transação
- `PUT /transactions/:id` - atualizar uma transação existente
- `DELETE /transactions/:id` - excluir uma transação

Use um token de acesso válido para requisições protegidas.

## Autenticação

This project uses OAuth2 for authentication. Request an access token from the `/oauth/token` endpoint and include it in the `Authorization` header for protected routes:

Authorization: Bearer <access_token>

## Testes

Use um cliente de API como Thunder Client, Postman ou ferramenta similar para testar os endpoints. Verifique se a emissão de token OAuth2 funciona e se as rotas protegidas rejeitam acessos não autorizados.

## Contribuindo

To contribute, create an issue or pull request describing the change. Keep code style consistent and update documentation as needed.

## References




## References
All of this source code was based on a course from the company [Algaworks](https://www.algaworks.com/) and all credits are reserved for them. If you want to know more, you can check your organization [GitHub Algawoks](https://github.com/algaworks).
