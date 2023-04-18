package com.inchl.resourceserver.util;

import com.inchl.resourceserver.exception.GlobalExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {
    public static final Logger LOGGER = LoggerFactory.getLogger((GlobalExceptionHandler.class));

    /* Column Based Unique Constraints */
    public static final String USER_EMAIL_UNIQUE_CONSTRAINT= "USER_EMAIL_UNIQUE_CONSTRAINT";
    public static final String USER_USERNAME_UNIQUE_CONSTRAINT= "USER_USERNAME_UNIQUE_CONSTRAINT";

    /* Error For Unique Constraints */
    public static final int EMAIL_CONSTRAINT_FAILED = 1001;
    public static final int USERNAME_CONSTRAINT_FAILED = 1002;
    public static final int USER_NOT_FOUND = 1003;
    public static final int DATA_NOT_FOUND = 1004;
}
