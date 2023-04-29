package com.uni.auth.exceptions;

import org.springframework.http.HttpStatus;

public class ServerException {

    private final HttpStatus status;
    private final ErrorCodes code;

    public ServerException(HttpStatus status, String message, ErrorCodes code) {
        super();
        this.status = status;
        this.code = code;
    }
}
