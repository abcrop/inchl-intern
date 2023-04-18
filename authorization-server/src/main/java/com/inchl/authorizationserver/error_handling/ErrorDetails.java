package com.inchl.authorizationserver.error_handling;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDetails {
    private Integer status;
    private String message;

}
