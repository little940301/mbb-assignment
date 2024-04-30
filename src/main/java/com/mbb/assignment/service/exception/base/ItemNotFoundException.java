package com.mbb.assignment.service.exception.base;

/**
 * The Item Not Found Exception class will be used to show messages on occurrence
 * of exception
 *
 * @author little940301@gmail.com
 * @version 1.0
 * @since 1.0
 */
public class ItemNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -3098669988509514800L;

    public ItemNotFoundException(String message) {
        super(message);
    }

    public ItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
