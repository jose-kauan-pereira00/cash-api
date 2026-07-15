# Cash API

## Overview

Cash API is a backend service for managing financial accounts, transactions, and OAuth2-secured access. The project provides REST endpoints to handle account records, transaction entries, and user authentication. This README describes how to install, configure, and use the application.

## Features

- OAuth2 authentication and authorization
- CRUD operations for accounts and transactions
- Protected API endpoints with bearer tokens
- JSON request/response support
- Local development and production build scripts

## Getting Started

### Prerequisites

- Node.js 20 or later
- npm 10 or later
- A supported relational database configured in the project

### Installation

1. Clone the repository.
2. Install dependencies:
   npm install
3. Create a `.env` file or copy the environment example file with required settings.
4. Configure the database connection and OAuth2 credentials.

### Running the Application

Start the application in development mode:

npm run dev

Build and run for production:

npm run build
npm start

## API Documentation

The API exposes several endpoints for account and transaction management. Typical routes include:

- `POST /oauth/token` - obtain an OAuth2 access token
- `GET /accounts` - list accounts
- `POST /accounts` - create a new account
- `GET /transactions` - list transactions
- `POST /transactions` - create a new transaction
- `PUT /transactions/:id` - update an existing transaction
- `DELETE /transactions/:id` - delete a transaction

Use a valid access token for protected requests.

## Authentication

This project uses OAuth2 for authentication. Request an access token from the `/oauth/token` endpoint and include it in the `Authorization` header for protected routes:

Authorization: Bearer <access_token>

## Testing

Use an API client such as Thunder Client, Postman, or a similar tool to test the endpoints. Verify that OAuth2 token issuance works and that protected routes reject unauthorized access.

## Contributing

To contribute, create an issue or pull request describing the change. Keep code style consistent and update documentation as needed.

## References




## References
All of this source code was based on a course from the company [Algaworks](https://www.algaworks.com/) and all credits are reserved for them. If you want to know more, you can check your organization [GitHub Algawoks](https://github.com/algaworks).
