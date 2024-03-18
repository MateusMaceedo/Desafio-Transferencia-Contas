import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
  stages: [
    { duration: '30s', target: 200 }, // Aumento gradual até 200 usuários virtuais
    { duration: '1m', target: 200 },  // Mantenha 200 usuários virtuais por 1 minuto
    { duration: '30s', target: 0 },    // Redução gradual até 0 usuários virtuais
  ],
  thresholds: {
    'http_req_duration': ['p(95)<100'], // Garante que a maioria das solicitações tenha uma latência inferior a 100ms
    'http_req_duration{path:/account/transfer}': ['p(95)<100'], // Garante que a maioria das solicitações de transferência tenha uma latência inferior a 100ms
  },
};

export default function () {
  let accountId = Math.floor(Math.random() * 1000) + 1;
  let destinationAccountId = Math.floor(Math.random() * 1000) + 1;

  // Criar conta
  let createAccountResponse = http.post('http://localhost:8080/account', JSON.stringify({ id: accountId, currency: 'USD' }));
  check(createAccountResponse, { 'status is 200': (r) => r.status === 200 });

  // Consultar saldo
  let getAccountResponse = http.get(`http://localhost:8080/account/${accountId}`);
  check(getAccountResponse, { 'status is 200': (r) => r.status === 200 });

  // Transferência entre contas
  let transferAccountResponse = http.post('http://localhost:8080/account/transfer', JSON.stringify({ id: accountId, destination: destinationAccountId, toBeTranfered: 100 }));
  check(transferAccountResponse, { 'status is 200': (r) => r.status === 200 });

  // Aguardar um curto intervalo entre as solicitações
  sleep(1);
}
