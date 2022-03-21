package com.pix.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class KeyNotFoundException extends ClassNotFoundException {

    public KeyNotFoundException(String error){
     super(error);
    }

}
