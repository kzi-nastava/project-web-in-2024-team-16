package com.webshop.error;

import org.apache.coyote.BadRequestException;

public class NoAdministratorException extends BadRequestException {
    public NoAdministratorException(String massage) {
        super(massage);
    }
}
