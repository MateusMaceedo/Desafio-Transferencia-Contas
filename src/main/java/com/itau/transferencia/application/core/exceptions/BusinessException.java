package com.itau.transferencia.application.core.exceptions;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

public class BusinessException extends Exception {

    @Getter
    private final List<String> listOfErrors;

    public BusinessException(String errorMessage){
        super(errorMessage);
        this.listOfErrors = new ArrayList<>(); // Inicialização da lista de erros
    }

    public void addError(String s) {
        listOfErrors.add(s);
    }
}
