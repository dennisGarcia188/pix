package com.pix.exceptions;

import org.springframework.dao.DuplicateKeyException;

public class InactivateKeyException extends DuplicateKeyException {

    public InactivateKeyException(String error){
        super(error);
    }

}
