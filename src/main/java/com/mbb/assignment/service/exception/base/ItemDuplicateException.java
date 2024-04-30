package com.mbb.assignment.service.exception.base;

/**
 * The Item Duplicate Exception class will be used to show messages on occurrence
 * of exception
 *
 * @author little940301@gmail.com
 * @version 1.0
 * @since 1.0
 */
public class ItemDuplicateException extends RuntimeException {

    private static final long serialVersionUID = -4751688413571097526L;

    public ItemDuplicateException(String message) {
        super(message);
    }

    public ItemDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }
}
