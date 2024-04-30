package com.mbb.assignment.service.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * The MBBLoanApplicationResponseModel is a class to provide MBB Loan Application Rule Response.
 *
 * @author little940301@gmail.com
 * @version 1.0
 * @since 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Schema(title = "MBB Loan Application Rule Request Model", description = "Represent MBB Loan Application Rule Request Model")
public class MBBLoanApplicationRequestModel {

    /**
     * id
     */
    private String id;

    /**
     * applicantName
     */
    private String applicantName;

    /**
     * amount
     */
    private BigDecimal amount;

    /**
     * ccrisScore
     */
    private BigDecimal ccrisScore;

    /**
     * ctosScore
     */
    private BigDecimal ctosScore;

    /**
     * status
     */
    private String status;

}
