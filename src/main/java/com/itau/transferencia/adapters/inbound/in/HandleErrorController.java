package com.itau.transferencia.adapters.inbound.in;

import com.itau.transferencia.application.core.domain.dto.ErrorDTO;
import com.itau.transferencia.application.core.exceptions.BuisnessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class HandleErrorController {
    @ExceptionHandler(value = BuisnessException.class)
    ResponseEntity<ErrorDTO> handleValidPasswordError (BuisnessException buisEx) {
        ErrorDTO error = new ErrorDTO(buisEx.getListOfErrors(), buisEx.getMessage());
        return new ResponseEntity<ErrorDTO>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
