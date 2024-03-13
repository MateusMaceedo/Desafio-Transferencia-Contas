package com.itau.transferencia.application.core.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ErrorDTO {
    public ErrorDTO(List<String> listOfError, String error) {
        this.listOfError = listOfError;
        this.error = error;
        this.isTransfer = false;
    }

    @JsonProperty("list_of_errors")
    List<String> listOfError;

    @JsonProperty("error_message")
    String error;

    @JsonProperty("isTransfer")
    private boolean isTransfer;
}
