### Transferencia Contas

Projeto desenvolvido para o desafio do Itaú.

Nele esta contido uma solução tecnica e arquitetural, que permiti os clientes do Itau realizar operações de ***Consulta de Saldo e Transfência entre contas***. A solução é resiliente, tem alta disponilibidade e suporta o volumo de 6 mil transações por segundo, com uma latência abaixo de 100ms. Abaixo na documentação, deixarei o teste de performance usando o K6 realizado na aplicação.
>Obs: Adicionei duas funcionalidades, para facilitar os testes.

#### Instalação
- Java 17 Correto (https://aws.amazon.com/pt/corretto/)
- Gradle
- IDE (de sua preferência)

#### Execução
Para executar a aplicação localmente é necessário:
- Clonar o repositório: ``` git clone {endereço}```
- Executar a instação das dependências: ``` gradle build``` (todos os testes devem passar)
- Executar a aplicação Spring Boot: ``` gradle spring-boot:root```

Obs: Para rodar a infra da aplicação, para a simulação dos recursos da AWS, estamos usando o LocalStack, simulando os seguintes serviços:

- DynamoDB

Para a execução do Docker, rode o seguinte comando:
- Via bash: ``` docker-compose up```

#### Projeto
O projeto foi desenvolvido com as seguintes tecnologias:

- Java 17
- Spring Boot 3
- WebFlux (Módulo Spring Webflux que permite trabalhar com programação reativa em aplicações Java com Spring)
- DynamoDB
- K6
- Docker
- Junit

Para o case, é necessario controlar bem o fluxo, para evitar gargalos e interferências bloqueantes das requisições. Utilizei o Webflux, para trabalhar com a programação reativa do Java, assim podemos usar a propria JVM e recursos da propria plataforma, para o controle do TPS, sem a necessidade de aumentar o Scaling horizontal ou vertical da nossa instância EC2, pois utilizaremos apenas para hospedar a nossa aplicação, o controle de theads e tps, vamos controlar via app, assim podemos organizar melhor as responsabilidades e evitar custos e ter uma escalabilidade mais assertiva.

Para abordar as necessidades do case, é possível criar uma arquitetura na AWS que atenda a esses requisitos. Abaixo está uma proposta geral para cada um dos pontos mencionados:

1. **Proposta de Escalonamento para Oscilação de Carga**:
   - Utilize serviços como o Amazon EC2 Auto Scaling para dimensionar automaticamente a capacidade dos seus servidores de acordo com a carga de trabalho. Você pode configurar políticas de escalonamento para aumentar ou diminuir o número de instâncias EC2 com base em métricas de uso, como CPU, memória ou tráfego de rede.

2. **Proposta de Observabilidade**:
   - Use o Amazon CloudWatch para monitorar a saúde do sistema, coletar métricas e registrar logs. Configure alertas no CloudWatch para notificar sobre quaisquer problemas ou eventos importantes.
   - Considere o uso do AWS X-Ray para rastreamento de solicitações e identificação de gargalos de desempenho em aplicativos distribuídos.

3. **Justificativa da Escolha do Banco de Dados**:
   - Escolha um banco de dados que seja altamente escalável e ofereça baixa latência, como o Amazon DynamoDB ou o Amazon Aurora. Esses bancos de dados são totalmente gerenciados pela AWS e podem escalar automaticamente para atender às demandas de tráfego.

4. **Justificativa para o Uso de Caching**:
   - Use um serviço de cache como o Amazon ElastiCache para armazenar em cache consultas frequentes ao banco de dados. Isso reduzirá a carga no banco de dados e melhorará o desempenho geral do sistema, garantindo que as respostas sejam entregues rapidamente aos clientes.

5. **Tempo Total da Requisição até a Resposta ao Cliente**:
   - Ao projetar a arquitetura, otimize cada camada do sistema para garantir que o tempo total da requisição não exceda 100ms. Isso pode incluir o uso de serviços de cache, dimensionamento automático e otimização do código da aplicação.

6. **Capacidade para Suportar Alto Throughput (6 mil tps)**:
   - Use serviços altamente escaláveis e totalmente gerenciados, como o Amazon API Gateway para gerenciar o tráfego de entrada e distribuir as solicitações para várias instâncias do Amazon EC2 ou serviços serverless, como AWS Lambda, conforme necessário. Isso garantirá que o sistema possa lidar com o alto throughput sem problemas.

7. **Estratégia em Caso de Falha de Dependência**:
   - Implemente práticas de resiliência, como a implementação de circuit breakers e retries automáticos em caso de falha de dependências. Além disso, use AWS Step Functions para orquestrar fluxos de trabalho e lidar com situações de erro de forma robusta e escalável.

8. **Estratégia em Caso de Throttling (HTTP Status 429) do BACEN**:
   - Implemente um mecanismo de backoff exponencial para lidar com respostas de throttling do BACEN. Isso envolve esperar um período de tempo crescente antes de reenviar a solicitação após receber uma resposta 429. Além disso, configure alertas no CloudWatch para monitorar o número de respostas 429 e ajustar dinamicamente a taxa de solicitações enviadas ao BACEN conforme necessário.

#### Composição da arquitetura (Utilização de Patterns)

- Hexagonal Arquitect
- Algorithm Bucket

#### Composição da arquitetura Infra
<h1 align="center">
  <img src="https://github.com/MateusMaceedo/Desafio-Transferencia-Contas/blob/develop/docs/Diagrama%20sem%20nome.drawio.png?raw=true" alt="CaseITUBE.drawio.png">
</h1>

- Performance:
Temos um script de teste de carga que cria uma quantidade variável de usuários virtuais que executam uma série de operações:

   1. Criação de uma conta.
   2. Consulta de saldo.
   3. Transferência entre contas.

Ele utiliza os endpoints definidos no seu código para simular o comportamento dos clientes do Itaú.

Para executar este script, salve-o com a extensão .js e execute o k6 apontando para o arquivo:

``` k6 run /src/test/java/com/itau/transferencia/performance/script.js```

>Para as operações da API, disponibilizo o curl das chamadas, a fim de facilitar os testes.

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
    --endpoint-url=http://localhost:8000
```

Para listar as tabelas existentes:
``` bash
sudo aws dynamodb list-tables --endpoint-url http://localhost:8000
```

Para listar os dados da tabela:
``` bash
sudo aws dynamodb scan  --endpoint-url http://localhost:8000 --table-name tbt-transferencia-contas

```
#### Referências
- Tom Hombergs - <b>Get your hands Dirty on Clean Architecture </b>
- Robert Martin - <b> Clean Architect </b>
