package com.mbb.assignment.service.dto.response.base;

import lombok.Data;
import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * This class is used as the base error response dto to return the result.
 *
 * @author little940301@gmail.com
 * @version 1.0
 * @since 1.0
 */
@Data
public class BaseErrorResponse<T> extends BaseResponse<T> {

    /**
     * represents error code.
     */
    private int returnCode;

    /*** represents any message. */
    private String message;

    /**
     * Initializes BaseErrorResponse with MessageSource, moduleCode and errorCode.
     *
     * @param messageSource instance of MessageSource
     * @param moduleCode    module code
     * @param errorCode     error code.
     * @author little940301@gmail.com
     * @since 1.0
     */
    public BaseErrorResponse(MessageSource messageSource, String moduleCode, String errorCode) {
        this(messageSource, moduleCode, errorCode, new Object[]{});
    }

    /**
     * Initializes BaseErrorResponse with MessageSource, moduleCode and errorCode.
     *
     * @param messageSource instance of MessageSource
     * @param moduleCode    module code
     * @param errorCode     error code.
     * @param parameters    list of parameters to be replaced in message.
     * @author little940301@gmail.com
     * @since 1.0
     */
    public BaseErrorResponse(
            MessageSource messageSource, String moduleCode, String errorCode, Object[] parameters) {
        this(messageSource, moduleCode, errorCode, parameters, null);
    }

    /**
     * Initializes BaseErrorResponse with MessageSource, moduleCode and errorCode.
     *
     * @param messageSource instance of MessageSource
     * @param moduleCode    module code
     * @param errorCode     error code.
     * @param parameters    list of parameters to be replaced in message.
     * @param result        result to be returned
     * @author little940301@gmail.com
     * @since 1.0
     */
    public BaseErrorResponse(
            MessageSource messageSource,
            String moduleCode,
            String errorCode,
            Object[] parameters,
            T result) {
        returnCode = Integer.parseInt(moduleCode.concat(errorCode));
        message = messageSource.getMessage(errorCode, parameters, Locale.getDefault());
        this.result = result;
    }
}
