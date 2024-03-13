//package com.itau.transferencia.adapters.inbound.in;
//
//import com.itau.transferencia.adapters.outbound.gateway.BacenGatewayImpl;
//import com.itau.transferencia.adapters.outbound.gateway.UserGatewayImpl;
////import com.itau.transferencia.adapters.outbound.repository.AccountRepository;
//import com.itau.transferencia.application.core.domain.AccountModel;
//import com.itau.transferencia.application.core.domain.dto.TransferAccountDTO;
//import com.itau.transferencia.application.core.usecases.AccountUseCaseImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//@RestController
//@RequestMapping(value = "/account")
//@RequiredArgsConstructor
//public class AccountController {
////
////    @Autowired
////    public AccountRepository accountRepository;
//
//    @Autowired
//    public AccountUseCaseImpl accountUseCase;
//
//    @Autowired
//    public UserGatewayImpl userGateway;
//
//    @Autowired
//    public BacenGatewayImpl bacenGateway;
//
////    @GetMapping
////    public Flux<AccountModel> all() {
////        return this.accountRepository.findAll();
////    }
//
////    @PostMapping
////    public Mono<AccountModel> create(@RequestBody AccountModel account) {
////        return this.accountRepository.save(account);
////    }
//
////    @PostMapping("/transfer")
////    public Mono<String> transfer(@RequestBody TransferAccountDTO account) {
////        return accountUseCase.transferBetweenAccounts(account);
////    }
//
////    @GetMapping("/{id}")
////    public Mono<AccountModel> get(@PathVariable("id") Integer id) {
////        return this.accountRepository.findById(id);
////    }
//
////    @PutMapping("/{id}")
////    public Mono<AccountModel> update(@PathVariable("id") Integer id, @RequestBody AccountModel account) {
////        return this.accountRepository.findById(id)
////                .map(p -> {
////                    p.setCurrency(account.getCurrency());
////                    return p;
////                })
////                .flatMap(p -> this.accountRepository.save(p));
////    }
//
////    @DeleteMapping("/{id}")
////    public Mono<Void> delete(@PathVariable("id") Integer id) {
////        return this.accountRepository.deleteById(id);
////    }
//}
