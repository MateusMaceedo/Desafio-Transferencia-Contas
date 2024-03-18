package com.itau.transferencia.application.core.exceptions;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class BusinessExceptionTest {
    @Test
    public void testBusinessExceptionConstructorAndGetters() {
        String errorMessage = "Erro de negócio ocorreu.";

        BusinessException businessException = new BusinessException(errorMessage);

        assertEquals(errorMessage, businessException.getMessage());
    }

    @Test
    public void testAddError() {
        BusinessException businessException = new BusinessException("Erro de negócio ocorreu.");

        businessException.addError("Erro 1");
        businessException.addError("Erro 2");

        List<String> listOfErrors = businessException.getListOfErrors();
        assertNotNull(listOfErrors);
        assertFalse(listOfErrors.isEmpty());
        assertEquals(2, listOfErrors.size());
        assertTrue(listOfErrors.contains("Erro 1"));
        assertTrue(listOfErrors.contains("Erro 2"));
    }
}
