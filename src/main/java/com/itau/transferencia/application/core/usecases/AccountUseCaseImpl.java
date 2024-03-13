//package com.itau.transferencia.application.core.usecases;
//
//import com.itau.transferencia.adapters.outbound.gateway.BacenGatewayImpl;
//import com.itau.transferencia.adapters.outbound.gateway.UserGatewayImpl;
////import com.itau.transferencia.adapters.outbound.repository.AccountRepository;
//import com.itau.transferencia.application.core.domain.AccountModel;
//import com.itau.transferencia.application.core.domain.dto.TransferAccountDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.server.ResponseStatusException;
//import reactor.core.publisher.Mono;
//
//@Service
//public class AccountUseCaseImpl {
//
////    @Autowired
////    private AccountRepository accountRepository;
//
//    @Autowired
//    private UserGatewayImpl userGateway;
//
//    @Autowired
//    private BacenGatewayImpl bacenGateway;
//
//    @Autowired
//    private final KafkaTemplate<String, String> kafkaTemplate;
//
//    public AccountUseCaseImpl(@Qualifier("kafkaTemplate") KafkaTemplate<String, String> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
////    private Mono<AccountModel> subtract(final Mono<AccountModel> accountModel, final double value) {
////        return this.accountRepository.findById(accountModel.map(AccountModel::getId))
////                .map(p -> {
////                    if (p.getCurrency() - value < 0) {
////                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Saldo insuficiente na conta");
////                    }
////                    p.setCurrency(p.getCurrency() - value);
////                    return p;
////                })
////                .flatMap(p -> this.accountRepository.save(p));
////    }
//
////    private Mono<AccountModel> add(final Mono<AccountModel> accountModel, final double value) {
////        return this.accountRepository.findById(accountModel.map(AccountModel::getId))
////                .map(p -> {
////                    p.setCurrency(p.getCurrency() + value);
////                    return p;
////                })
////                .flatMap(p -> this.accountRepository.save(p));
////    }
//
////    @Transactional
////    public Mono<AccountModel> transfer(final Mono<AccountModel> fromAccount, final Mono<AccountModel> toAccount, final double amount) {
////        return subtract(fromAccount, amount).then(add(toAccount, amount));
////    }
//
////    public Mono<String> transferBetweenAccounts(TransferAccountDTO account) {
////        Mono<AccountModel> fromUser = accountRepository.findById(account.getId()).map(accountModel -> {
////            userGateway.getUserName(Mono.just(accountModel));
////                    return accountModel;
////                }
////        );
////        Mono<AccountModel> toAccount = accountRepository.findById(account.getDestination()).map(accountModel -> {
////            userGateway.getUserName(Mono.just(accountModel));
////                    return accountModel;
////                }
////        );
////        return transfer(fromUser, toAccount, account.getToBeTranfered())
////                .then(bacenGateway.notify(fromUser, toAccount, account.getToBeTranfered()))
////                .switchIfEmpty(Mono.just(""/*enviar msg para fila de contingencia*/));
////    }
//}
