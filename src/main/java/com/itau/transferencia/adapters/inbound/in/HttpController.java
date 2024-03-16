package com.itau.transferencia.adapters.inbound.in;

import com.itau.transferencia.adapters.outbound.repository.impl.AccountRepository;
import com.itau.transferencia.application.core.domain.dto.AccountDTO;
import com.itau.transferencia.application.core.domain.dto.TransferAccountDTO;
import com.itau.transferencia.application.core.usecases.rules.AbstractRules.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/account")
@RequiredArgsConstructor
public class HttpController {

    @Autowired
    private final TransferService transferService;

    @Autowired
    private final AccountRepository accountRepository;

    @PostMapping
    public Mono<AccountDTO> create(@RequestBody AccountDTO account) {
        return this.accountRepository.save(account);
    }

    @PostMapping("/transfer")
    public Mono<AccountDTO> transfer(@RequestBody TransferAccountDTO account) {
        return transferService.transfer(
                accountRepository.findById(account.getId()),
                accountRepository.findById(account.getDestination()),
                account.getToBeTranfered()
        );
    }

    @GetMapping("/{id}")
    public Mono<AccountDTO> get(@PathVariable("id") String id) {
        return this.accountRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Mono<AccountDTO> update(@PathVariable("id") String id, @RequestBody AccountDTO account) {
        return this.accountRepository.findById(id)
                .map(p -> {
                    p.setCurrency(account.getCurrency());
                    return p;
                })
                .flatMap(this.accountRepository::save);
    }
}
