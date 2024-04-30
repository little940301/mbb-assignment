package com.mbb.assignment.service.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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
@Schema(title = "MBB Loan Application Rule Response Model", description = "Represent MBB Loan Application Rule Response Model")
public class MBBLoanApplicationResponseModel implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8862888691775371232L;

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
