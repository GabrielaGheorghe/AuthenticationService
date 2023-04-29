package com.uni.auth.exceptions;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends ServerException {

    public UserAlreadyExistsException(String message, ErrorCodes code) {
        super(HttpStatus.CONFLICT, message, code);
    }
}
