package com.mbb.assignment.service.dto.response.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The ApplicationExceptionResponse class will be used to respond exceptions occurring in
 * application
 *
 * @author little940301@gmail.com
 * @version 1.0
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationExceptionResponse implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8862888691775375776L;

    /**
     * errorMessage
     */
    private Object errorMessage;

    /**
     * timestamp
     */
    private LocalDateTime timestamp;

    /**
     * description of error
     */
    private String description;

    /**
     * error return code.
     */
    private int returnCode;
}
