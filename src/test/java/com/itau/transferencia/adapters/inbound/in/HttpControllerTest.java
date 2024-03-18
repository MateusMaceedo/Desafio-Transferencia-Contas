package com.itau.transferencia.adapters.inbound.in;

import com.itau.transferencia.adapters.outbound.repository.impl.AccountRepository;
import com.itau.transferencia.application.core.domain.dto.AccountDTO;
import com.itau.transferencia.application.core.domain.dto.TransferAccountDTO;
import com.itau.transferencia.application.core.usecases.rules.AbstractRules.TransferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import static org.mockito.Mockito.*;

public class HttpControllerTest {
    @Mock
    private TransferService transferService;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private HttpController httpController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_shouldSaveAccount() {
        AccountDTO accountDTO = new AccountDTO();
        when(accountRepository.save(accountDTO)).thenReturn(Mono.just(accountDTO));

        httpController.create(accountDTO);

        verify(accountRepository, times(1)).save(accountDTO);
    }

    @Test
    void transfer_shouldTransferAmount() {
        TransferAccountDTO transferAccountDTO = new TransferAccountDTO("sourceId", "destinationId", 100.0);
        when(accountRepository.findById(transferAccountDTO.getId())).thenReturn(Mono.just(new AccountDTO()));
        when(accountRepository.findById(transferAccountDTO.getDestination())).thenReturn(Mono.just(new AccountDTO()));
        when(transferService.transfer(any(), any(), anyDouble())).thenReturn(Mono.just(new AccountDTO()));

        httpController.transfer(transferAccountDTO);

        verify(transferService, times(1)).transfer(any(), any(), anyDouble());
    }

    @Test
    void get_shouldReturnAccount() {
        String accountId = "accountId";
        when(accountRepository.findById(accountId)).thenReturn(Mono.just(new AccountDTO()));

        httpController.get(accountId);

        verify(accountRepository, times(1)).findById(accountId);
    }

    @Test
    void update_shouldUpdateAccount() {
        String accountId = "accountId";
        AccountDTO updatedAccountDTO = new AccountDTO();
        when(accountRepository.findById(accountId)).thenReturn(Mono.just(new AccountDTO()));
        when(accountRepository.save(any())).thenReturn(Mono.just(updatedAccountDTO));

        httpController.update(accountId, updatedAccountDTO);

        verify(accountRepository, times(1)).findById(accountId);
    }
}
