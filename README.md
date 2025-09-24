*API PAGAMENTOS*
Sobre o projeto
API RESTful para gerenciar pagamentos de débitos de pessoas físicas e jurídicas.

Tecnologias utilizadas e Ferramentas
Intellij
Java 17
Spring boot
Postman
Docker
Banco de dados h2

*Funcionalidades*

Criar pagamento POST /api/payments

Campos: debitCode, payerDocument, method (boleto, pix, cartao_credito, cartao_debito), cardNumber (para cartão), paymentAmount.
Pagamentos novos iniciam com status = PENDENTE.

Atualizar status PUT /api/payments/{id}/status

Transições permitidas:

PENDENTE → PROCESSADO_SUCESSO | PROCESSADO_FALHA
PROCESSADO_FALHA → PENDENTE
PROCESSADO_SUCESSO não pode ser alterado.

Listar pagamentos (GET /api/payments)

Filtros de busca:

?debitCode=xxx
?payerDocument=xxx
?status=xxx

Retorna apenas pagamentos ativos (active = true).

Exclusão lógica (DELETE /api/payments/{id})

Só permite deletar pagamentos com status = PENDENTE.
Marca active = false sem remover do banco

*Observações*

Todos os payloads de entrada e saída são em JSON.
Mensagens de erro claras são retornadas quando não existem resultados ou quando há falha na transição de status.
API implementa soft delete mantendo histórico de pagamentos.
