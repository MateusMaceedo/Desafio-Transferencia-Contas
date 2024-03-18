package com.itau.transferencia.adapters.inbound.mapper;

import com.itau.transferencia.adapters.inbound.entity.TransferAccountEntity;
import com.itau.transferencia.adapters.inbound.mapper.impl.TransferToTransferEntityMapper;
import com.itau.transferencia.application.core.domain.dto.TransferAccountDTO;
import org.junit.jupiter.api.Test;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TransferToTransferEntityMapperTest {
    private final TransferToTransferEntityMapper mapper = new TransferToTransferEntityMapper();

    @Test
    public void testToDomain() {
        TransferAccountEntity entity = new TransferAccountEntity();
        entity.setId("12345");
        entity.setToBeTranfered(1000);
        entity.setDestination("98765");
        entity.setSucesso("true");
        entity.setDataHoraExpiracaoDado(new Date(1639420800000L).getTime());

        TransferAccountDTO dto = mapper.toDomain(entity);

        assertEquals("12345", dto.getId());
        assertEquals("98765", dto.getCpfCliente());
        assertEquals("1000.0", dto.getValorTransferencia());
        assertEquals("2024-03-18", formatDate(dto.getDataTransferencia()));
        assertNull(dto.getIdentificadorBacenTransferencia());
        assertNull(dto.getNumeroContaDestino());
        assertEquals("true", dto.getSucesso());
        assertEquals(1639420800000L, dto.getDataHoraExpiracaoDado().longValue());
    }

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    @Test
    public void testToDomainWithNullEntity() {
        assertNull(mapper.toDomain(null));
    }

    @Test
    public void testToEntity() {
        TransferAccountDTO dto = new TransferAccountDTO();
        dto.setId("12345");
        dto.setValorTransferencia("1000.0");
        dto.setCpfCliente("98765");
        dto.setSucesso("true");
        dto.setDataHoraExpiracaoDado(new Date(1639420800000L).getTime());

        TransferAccountEntity entity = mapper.toEntity(dto);

        assertEquals("12345", entity.getId());
        assertEquals(1000.0, entity.getToBeTranfered());
        assertEquals("98765", entity.getDestination());
        assertEquals("true", entity.getSucesso());
        assertEquals(1639420800000L, entity.getDataHoraExpiracaoDado().longValue());
    }

    @Test
    public void testToEntityWithNullDomain() {
        assertNull(mapper.toEntity(null));
    }
}
