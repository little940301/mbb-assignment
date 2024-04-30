package com.mbb.assignment.service.dto.response.base;

import lombok.*;

/**
 * This class is used as the base response dto to return the result.
 *
 * @author little940301@gmail.com
 * @version 1.0
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class BaseResponse<T> {

    /*** return code */
    protected int returnCode;

    /*** return message */
    protected String message;

    /*** represents actual response. */
    protected T result;

}
