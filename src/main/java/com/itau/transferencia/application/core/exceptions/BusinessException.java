package com.itau.transferencia.application.core.exceptions;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

public class BusinessException extends Exception {

    @Getter
    List<String> listOfErrors;
    private List<String> listOfError = new ArrayList<>();

    public BusinessException(String errorMessage){
        super(errorMessage);
    }

    public void addError(String s) {
        listOfError.add(s);
    }
}
