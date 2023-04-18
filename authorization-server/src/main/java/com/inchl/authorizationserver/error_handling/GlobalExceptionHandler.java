package com.inchl.authorizationserver.error_handling;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.inchl.authorizationserver.util.Constants.EMAIL_CONSTRAINT_FAILED;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger((GlobalExceptionHandler.class));

    private final static int EMAIL_INVALID = 1;
    private final static int PASSWORD_INVALID = 2;
    private final static int USERTYPE_INVALID = 3;
    private final static int EMAIL_UNIQUE_CONSTRAINT = 4;

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDetails> nonUniqueConstraintError(ConstraintViolationException e) {
        LOGGER.error("getErrorMessage: " + e.getMessage());
        LOGGER.error("code for exception: " + e.getErrorCode());
        LOGGER.error("contraint name: " + e.getConstraintName());

        ErrorDetails errorModel = null;

        LOGGER.error("email contraint violation");
        errorModel = new ErrorDetails(EMAIL_CONSTRAINT_FAILED, "Email must be unique for each user.");

        return new ResponseEntity<ErrorDetails>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
        LOGGER.error("Global exception handler " + ex.getLocalizedMessage());

        ErrorDetails errorModel = new ErrorDetails(EMAIL_UNIQUE_CONSTRAINT, ex.getMessage());

        return new ResponseEntity<ErrorDetails>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
