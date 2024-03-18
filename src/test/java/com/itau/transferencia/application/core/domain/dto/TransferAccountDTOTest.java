package com.itau.transferencia.application.core.domain.dto;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferAccountDTOTest {
    @Test
    public void testTransferAccountDTOConstructorAndGetters() {

        String idEsperado = "123";
        String cpfClienteEsperado = "12345678900";
        String valorTransferenciaEsperado = "100.00";
        Date dataTransferenciaEsperada = new Date();
        String identificadorBacenTransferenciaEsperado = "123ABC";
        String numeroContaDestinoEsperado = "987654321";
        String sucessoEsperado = "sucesso";
        Long dataHoraExpiracaoDadoEsperado = System.currentTimeMillis();
        double toBeTranferedEsperado = 100.0;
        String destinationEsperado = "destination";

        TransferAccountDTO transferAccountDTO = new TransferAccountDTO();
        transferAccountDTO.setId(idEsperado);
        transferAccountDTO.setCpfCliente(cpfClienteEsperado);
        transferAccountDTO.setValorTransferencia(valorTransferenciaEsperado);
        transferAccountDTO.setDataTransferencia(dataTransferenciaEsperada);
        transferAccountDTO.setIdentificadorBacenTransferencia(identificadorBacenTransferenciaEsperado);
        transferAccountDTO.setNumeroContaDestino(numeroContaDestinoEsperado);
        transferAccountDTO.setSucesso(sucessoEsperado);
        transferAccountDTO.setDataHoraExpiracaoDado(dataHoraExpiracaoDadoEsperado);
        transferAccountDTO.setToBeTranfered(toBeTranferedEsperado);
        transferAccountDTO.setDestination(destinationEsperado);

        assertEquals(idEsperado, transferAccountDTO.getId());
        assertEquals(cpfClienteEsperado, transferAccountDTO.getCpfCliente());
        assertEquals(valorTransferenciaEsperado, transferAccountDTO.getValorTransferencia());
        assertEquals(dataTransferenciaEsperada, transferAccountDTO.getDataTransferencia());
        assertEquals(identificadorBacenTransferenciaEsperado, transferAccountDTO.getIdentificadorBacenTransferencia());
        assertEquals(numeroContaDestinoEsperado, transferAccountDTO.getNumeroContaDestino());
        assertEquals(sucessoEsperado, transferAccountDTO.getSucesso());
        assertEquals(dataHoraExpiracaoDadoEsperado, transferAccountDTO.getDataHoraExpiracaoDado());
        assertEquals(toBeTranferedEsperado, transferAccountDTO.getToBeTranfered());
        assertEquals(destinationEsperado, transferAccountDTO.getDestination());
    }
}
