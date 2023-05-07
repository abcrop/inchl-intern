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

    /* Bug Statuses */
    public static final String ONGOING  = "ONGOING";
    public static final String DONE  = "DONE";
    public static final String PENDING  = "PENDING";
    public static final String CANCEL  = "CANCEL";

    /* Bug Flags */
    public static final String CRITICAL  = "CRITICAL";
    public static final String MAJOR  = "MAJOR";
    public static final String MEDIUM  = "MEDIUM";
    public static final String MINOR  = "MINOR";
    public static final String TRIVIAL  = "TRIVIAL";

    /* User Types */
    public static final String ADMIN  = "ADMIN";
    public static final String DEVELOPER  = "DEVELOPER";
    public static final String TESTER  = "TESTER";
    public static final String ENDUSER  = "ENDUSER";
}
