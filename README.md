## Desafio-Transferencia-Contas

Projeto desenvolvido para o desafio do Itaú.

#### Criar uma nova conta:
``` bash

curl -X POST \
  http://localhost:5001/account \
  -H 'Content-Type: application/json' \
  -d '{
    "cpfCliente": "12345678900",
    "numeroContaCorrente": "123456",
    "saldoAtual": "1000.00",
    "sucesso": "true",
    "ativa": true,
    "limiteDisponivel": 5000.00,
    "currency": 1000,
    "id": "1"
}'

```

#### Transferir dinheiro entre contas:
``` bash

curl -X POST \
http://localhost:5001/account/transfer \
-H 'Content-Type: application/json' \
-d '{
"id": "1",
"destination": "2",
"toBeTranfered": 500
}'
```

#### Obter detalhes de uma conta por ID:
``` bash

curl -X GET \
http://localhost:5001/account/1
```

#### Atualizar detalhes de uma conta por ID:

``` bash

curl -X PUT \
http://localhost:5001/account/1 \
-H 'Content-Type: application/json' \
-d '{
"id": "1",
"name": "João Silva",
"currency": 1500
}'
```

#### Banco de dados dados

Para criar a tabela no dynamo, rode o seguinte comando:

``` bash
aws dynamodb create-table \
    --table-name tbt-transferencia-contas \
    --attribute-definitions AttributeName=cpf_cliente,AttributeType=S \
    --key-schema AttributeName=cpf_cliente,KeyType=HASH \
    --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 \
    --endpoint-url=http://localhost:4566
```