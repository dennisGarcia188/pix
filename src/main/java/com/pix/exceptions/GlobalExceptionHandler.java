package com.pix.exceptions;

import com.pix.exceptions.model.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler implements GlobalExceptionHandlerInterface {

    @ExceptionHandler(value = {DuplicateKeyException.class})
    public ResponseEntity<ErrorMessage> duplicateKeyException(DuplicateKeyException ex) {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                ex.getSQLState()
        );
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {KeyNotFoundException.class})
    public ResponseEntity<ErrorMessage> keyNotFoundException(KeyNotFoundException ex) {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                null
        );
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {InactivateKeyException.class})
    public ResponseEntity<ErrorMessage> inactivateKeyException(InactivateKeyException ex) {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                null
        );
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @Override
    public ResponseEntity<ErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                fieldErrors.get(0).getDefaultMessage(),
                null
        );
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {UpdatedInactiveKeyException.class})
    public ResponseEntity<ErrorMessage> updatedInactiveKeyException(UpdatedInactiveKeyException ex) {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                null
        );
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.CONFLICT);
    }




}
