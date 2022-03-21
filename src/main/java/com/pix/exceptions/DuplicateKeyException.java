package com.pix.exceptions;

import java.sql.SQLException;

public class DuplicateKeyException extends SQLException {

    public DuplicateKeyException(String error, String description) {
        super(error, description);
    }

}
