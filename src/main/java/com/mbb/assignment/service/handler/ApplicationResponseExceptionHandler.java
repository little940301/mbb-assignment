package com.mbb.assignment.service.handler;

import com.mbb.assignment.service.dto.response.base.ApplicationExceptionResponse;
import com.mbb.assignment.service.exception.base.ItemDuplicateException;
import com.mbb.assignment.service.exception.base.ItemNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * The Application ResponseException Handler class will be used to handle
 * exceptions occurring in operations
 *
 * @author little940301@gmail.com
 * @version 1.0
 * @since 1.0
 */
@ControllerAdvice
@Slf4j
public class ApplicationResponseExceptionHandler {

    /**
     * This method is used to handle item not found exception
     *
     * @param ex      - ItemNotFoundException
     * @param request - web request
     * @return exceptionResponse for item not found
     * @author little940301@gmail.com
     * @version 1.0
     * @since 1.0
     */
    @ExceptionHandler(ItemNotFoundException.class)
    public final @ResponseBody
    ResponseEntity<ApplicationExceptionResponse> handleItemNotFoundExceptionException(
            ItemNotFoundException ex, WebRequest request) {
        log.info("ItemNotFoundException Handler");
        String errorMessage = ex.getLocalizedMessage() == null ? ex.toString() : ex.getLocalizedMessage();
        ApplicationExceptionResponse exceptionResponse = buildApplicationExceptionResponse(request,
                HttpStatus.NOT_FOUND, errorMessage);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * This method is used to handle item duplicate exception
     *
     * @param ex      - ItemDuplicateException
     * @param request - web request
     * @return exceptionResponse for item not found
     * @author little940301@gmail.com
     * @version 1.0
     * @since 1.0
     */
    @ExceptionHandler(ItemDuplicateException.class)
    public final @ResponseBody
    ResponseEntity<ApplicationExceptionResponse> handleItemNotFoundExceptionException(
            ItemDuplicateException ex, WebRequest request) {
        log.info("ItemDuplicateException Handler");
        String errorMessage = ex.getLocalizedMessage() == null ? ex.toString() : ex.getLocalizedMessage();
        ApplicationExceptionResponse exceptionResponse = buildApplicationExceptionResponse(request,
                HttpStatus.CONFLICT, errorMessage);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

    /**
     * This method is used to handle all Exception
     *
     * @param ex      - Exception
     * @param request - web request
     * @return exceptionResponse for internal server error
     * @author little940301@gmail.com
     * @version 1.0
     * @since 1.0
     */
    @ExceptionHandler(Exception.class)
    public final @ResponseBody
    ResponseEntity<ApplicationExceptionResponse> handleAllException(Exception ex,
                                                                    WebRequest request) {
        log.info("General 500  Handler");
        String errorMessage = ex.getLocalizedMessage() == null ? ex.toString() : ex.getLocalizedMessage();
        ApplicationExceptionResponse exceptionResponse = buildApplicationExceptionResponse(request,
                HttpStatus.INTERNAL_SERVER_ERROR, errorMessage);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    /**
     * This method is used to handle Method Argument Not Valid Exception
     *
     * @param ex      - MethodArgumentNotValidException
     * @param headers - HttpHeaders
     * @param status  - HttpStatus
     * @param request - web request
     * @return exceptionResponse for bad request
     * @author little940301@gmail.com
     * @version 1.0
     * @since 1.0
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("handleMethodArgumentNotValid Handler");
        String errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
                .collect(Collectors.joining(","));
        ApplicationExceptionResponse exceptionResponse = buildApplicationExceptionResponse(request,
                HttpStatus.BAD_REQUEST, errors);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    private ApplicationExceptionResponse buildApplicationExceptionResponse(WebRequest request, HttpStatus httpStatus,
                                                                           String... errorMessages) {
        return ApplicationExceptionResponse.builder()
                .errorMessage(errorMessages)
                .timestamp(LocalDateTime.now())
                .description(request.getDescription(false))
                .returnCode(httpStatus.value())
                .build();
    }
}
