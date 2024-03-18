package com.itau.transferencia.adapters.inbound.in;

import com.itau.transferencia.application.core.domain.dto.ErrorDTO;
import com.itau.transferencia.application.core.exceptions.BusinessException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HandleErrorControllerTest {
    @Test
    public void testHandleValidTransferError() {
        HandleErrorController controller = new HandleErrorController();

        BusinessException businessException = new BusinessException("Erro ao realizar a transferência.");
        businessException.addError("Conta de destino inválida.");

        ResponseEntity<ErrorDTO> responseEntity = controller.handleValidTransferError(businessException);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        HttpHeaders headers = responseEntity.getHeaders();
        assertEquals(0, headers.size());

        ErrorDTO errorDTO = responseEntity.getBody();
        assertEquals("Erro ao realizar a transferência.", errorDTO.getError());
    }
}
