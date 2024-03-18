package com.itau.transferencia.adapters.inbound.mapper;

import com.itau.transferencia.adapters.inbound.entity.AccountEntity;
import com.itau.transferencia.adapters.inbound.mapper.impl.AccountToAccountEntityMapper;
import com.itau.transferencia.application.core.domain.dto.AccountDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AccountToAccountEntityMapperTest {
    private final AccountToAccountEntityMapper mapper = new AccountToAccountEntityMapper();

    @Test
    public void testToDomain() {
        AccountEntity entity = new AccountEntity();
        entity.setCpfCliente("12345678900");
        entity.setNumeroContaCorrente(1234567890);
        entity.setSaldoAtual("1000.00");
        entity.setSucesso("true");

        AccountDTO dto = mapper.toDomain(entity);

        assertEquals("12345678900", dto.getCpfCliente());
        assertEquals("1.23456789E9", String.valueOf(dto.getNumeroContaCorrente()));
        assertEquals("1000.00", dto.getSaldoAtual());
        assertEquals("true", dto.getSucesso());
    }

    @Test
    public void testToEntity() {
        AccountDTO dto = new AccountDTO();
        dto.setCpfCliente("12345678900");
        dto.setNumeroContaCorrente("1234567890");
        dto.setSaldoAtual("1000.00");
        dto.setSucesso("true");

        AccountEntity entity = mapper.toEntity(dto);

        assertEquals("12345678900", entity.getCpfCliente());
        assertEquals(1234567890, entity.getNumeroContaCorrente());
        assertEquals("1000.00", entity.getSaldoAtual());
        assertEquals("true", entity.getSucesso());
    }

    @Test
    public void testToEntityWithNullDomain() {
        assertNull(mapper.toEntity(null));
    }
}
