package com.inchl.resourceserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundExceptions {
    public static class UserNotFoundException extends ResponseStatusException {
        private Long userId;

        public UserNotFoundException(Long id) {
            super(HttpStatus.NOT_FOUND, "User with id " + id + " not found.");
            userId = id;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }
    }

    public static class IdNotFoundException extends ResponseStatusException {
        private Long dataId;

        public IdNotFoundException(Long id) {
            super(HttpStatus.NOT_FOUND, "Data with id " + id + " not found.");
            dataId = id;
        }

        public Long getDataId() {
            return dataId;
        }

        public void setDataId(Long dataId) {
            this.dataId = dataId;
        }
    }

}
