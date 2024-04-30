package com.mbb.assignment.service.constants;

/**
 * The ErrorCodesConstant class is used to define constants used in the application.
 *
 * @author little940301@gmail.com
 * @version 1.0
 * @since 1.0
 */
public class ErrorCodesConstant {

    /**
     * Initializes ErrorCodesConstant
     *
     * @author little940301@gmail.com
     * @since 1.0
     */
    private ErrorCodesConstant() {
    }

    /**
     * Loan Application Duplicate
     */
    public static final String LOAN_APPLICATION_DUPLICATE = ModuleCodeConstant.LOAN_APPLICATION.concat("_").concat("ERR_000001");

    /**
     * Loan Application Not Found
     */
    public static final String LOAN_APPLICATION_NOT_FOUND = ModuleCodeConstant.LOAN_APPLICATION.concat("_").concat("ERR_000002");

    /**
     * Failed to Delete Loan Application
     */
    public static final String LOAN_APPLICATION_DELETE_FAILED = ModuleCodeConstant.LOAN_APPLICATION.concat("_").concat("ERR_000003");

    /**
     * Failed to Retrieve Zip Code Info
     */
    public static final String ZIP_CODE_INFO_RETRIEVAL_FAILED = ModuleCodeConstant.ZIP_CODE.concat("_").concat("ERR_000001");

}
