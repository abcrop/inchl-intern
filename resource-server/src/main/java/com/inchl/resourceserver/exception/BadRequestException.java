package com.inchl.resourceserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

public  class BadRequestException extends ResponseStatusException {
    public BadRequestException(String badParams) {
        super(HttpStatus.BAD_REQUEST, "Bad Request " + badParams);
    }
}