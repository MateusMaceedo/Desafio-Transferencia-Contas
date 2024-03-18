package com.itau.transferencia.adapters.inbound.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TransferAccountEntityTest {

    @Test
    public void testTransferAccountEntityInitialization() {
        TransferAccountEntity transferEntity = new TransferAccountEntity();

        assertNull(transferEntity.getId());
        assertEquals(0.0, transferEntity.getToBeTranfered());
        assertNull(transferEntity.getDestination());
        assertNull(transferEntity.getSucesso());
        assertNull(transferEntity.getDataHoraExpiracaoDado());
    }

    @Test
    public void testTransferAccountEntitySettersAndGetters() {
        TransferAccountEntity transferEntity = new TransferAccountEntity();

        transferEntity.setId("12345");
        transferEntity.setToBeTranfered(1000.0);
        transferEntity.setDestination("destination");
        transferEntity.setSucesso("true");
        transferEntity.setDataHoraExpiracaoDado(System.currentTimeMillis());

        assertEquals("12345", transferEntity.getId());
        assertEquals(1000.0, transferEntity.getToBeTranfered());
        assertEquals("destination", transferEntity.getDestination());
        assertEquals("true", transferEntity.getSucesso());
        assertNotNull(transferEntity.getDataHoraExpiracaoDado());
    }
}
