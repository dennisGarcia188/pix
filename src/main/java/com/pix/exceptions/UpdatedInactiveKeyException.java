package com.pix.exceptions;

public class UpdatedInactiveKeyException extends RuntimeException{

    public UpdatedInactiveKeyException(String error){
        super(error);
    }

}
