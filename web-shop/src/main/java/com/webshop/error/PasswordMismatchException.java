package com.webshop.error;

public class PasswordMismatchException extends Throwable {
    public PasswordMismatchException(String message)  {
        super(message);
    }
}
