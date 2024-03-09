package com.itau.transferencia.application.core.exceptions;

import java.util.List;

public class BuisnessException extends Exception {
    List<String> listOfErrors;

    public BuisnessException(String errorMessage, List<String> listOfErrors){
        super(errorMessage);
        this.listOfErrors = listOfErrors;
    }

    public List<String> getListOfErrors(){
        return listOfErrors;
    }
}
