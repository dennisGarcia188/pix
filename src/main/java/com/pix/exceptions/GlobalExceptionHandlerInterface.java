package com.pix.exceptions;

import com.pix.exceptions.model.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface GlobalExceptionHandlerInterface {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    ResponseEntity<ErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException ex);
}
