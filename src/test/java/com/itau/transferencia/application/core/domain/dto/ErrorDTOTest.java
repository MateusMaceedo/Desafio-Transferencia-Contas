package com.itau.transferencia.application.core.domain.dto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ErrorDTOTest {
    @Test
    public void testErrorDTOConstructorAndGetters() {
        List<String> listOfErrors = new ArrayList<>();
        listOfErrors.add("Erro 1");
        listOfErrors.add("Erro 2");

        String errorMessage = "Erro geral";
        ErrorDTO errorDTO = new ErrorDTO(listOfErrors, errorMessage);

        assertEquals(listOfErrors, errorDTO.getListOfError());
        assertEquals(errorMessage, errorDTO.getError());
    }
}
