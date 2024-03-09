package com.itau.transferencia.adapters.outbound.repository;

import com.itau.transferencia.application.core.domain.AccountModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AccountRepository extends ReactiveCrudRepository<AccountModel, Integer> { }
