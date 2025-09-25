# API PAGAMENTOS
## Sobre o projeto
API RESTful para gerenciar pagamentos de débitos de pessoas físicas e jurídicas.

### Tecnologias utilizadas e Ferramentas
- Intellij
- Java 17
- Spring boot
- Banco de dados H2
- Postman

## Funcionalidades

### 1- Criar pagamento **POST /api/payments**

**Campos**: debitCode, payerDocument, method (boleto, pix, cartao_credito, cartao_debito), cardNumber (apenas se o pagamento for em cartão), paymentAmount.

### 2- Atualizar status **PUT /api/payments/{id}/status**

**Campo**: newStatus (PENDENTE, PROCESSADO_SUCESSO, PROCESSADO_FALHA)

### 3- Listar todos os pagamentos **GET /api/payments**

**Filtros de busca:**

- GET /api/payments?status=PENDENTE
- GET /api/payments?payerDocument=12345678900
- GET /api/payments?debitCode=101



### 4- Exclusão lógica **DELETE /api/payments/{id}**

Só permite deletar pagamentos com status = PENDENTE. Altera o status do pagamento para inativo e mantem os dados no banco **(active muda para false).**

## Observações

- Todos os payloads de entrada e saída são em JSON.
- Mensagens de erro claras são retornadas.
- API implementa soft delete mantendo histórico de pagamentos.
