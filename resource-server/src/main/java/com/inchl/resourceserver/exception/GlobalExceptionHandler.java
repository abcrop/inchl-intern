package com.inchl.resourceserver.exception;

import com.inchl.resourceserver.util.Constants;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.inchl.resourceserver.util.Constants.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDetails> nonUniqueConstraintException(ConstraintViolationException e) {
        Constants.LOGGER.error("getErrorMessage: "+e.getMessage());
        Constants.LOGGER.error("code for exception: "+e.getErrorCode());
        Constants.LOGGER.error("contraint name: "+e.getConstraintName());

        ErrorDetails errorModel = null;

        if(e.getConstraintName().equalsIgnoreCase(USER_EMAIL_UNIQUE_CONSTRAINT)){
            LOGGER.error("email contraint violation");
            errorModel = new ErrorDetails(EMAIL_CONSTRAINT_FAILED, "Email must be unique for each user.");
        }
        if(e.getConstraintName().equalsIgnoreCase(USER_USERNAME_UNIQUE_CONSTRAINT)){
            LOGGER.error("Username contraint violation");
            errorModel = new ErrorDetails(EMAIL_CONSTRAINT_FAILED, "Username must be unique for each user.");
        }

        return new ResponseEntity <ErrorDetails> (errorModel, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(NotFoundExceptions.UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> userNotFoundException(NotFoundExceptions.UserNotFoundException e) {
        Constants.LOGGER.error("user not found print "+e.getMessage());
        ErrorDetails errorModel = new ErrorDetails(USER_NOT_FOUND, "User with id " + e.getUserId() + " not found .");
        return new ResponseEntity <ErrorDetails> (errorModel, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(NotFoundExceptions.IdNotFoundException.class)
    public ResponseEntity<ErrorDetails> userNotFoundException(NotFoundExceptions.IdNotFoundException e) {
        Constants.LOGGER.error("Data not found print "+e.getMessage());
        ErrorDetails errorModel = new ErrorDetails(USER_NOT_FOUND, "Data with id " + e.getDataId() + " not found .");
        return new ResponseEntity <ErrorDetails> (errorModel, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDetails> userNotFoundException(BadRequestException e) {
        Constants.LOGGER.error("Data not found print "+e.getMessage());
        ErrorDetails errorModel = new ErrorDetails(USER_NOT_FOUND, e.getMessage());
        return new ResponseEntity <ErrorDetails> (errorModel, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
