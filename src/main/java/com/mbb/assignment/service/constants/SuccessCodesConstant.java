package com.mbb.assignment.service.constants;

/**
 * The SuccessCodesConstant class is used to define constants used in the application.
 *
 * @author little940301@gmail.com
 * @version 1.0
 * @since 1.0
 */
public class SuccessCodesConstant {

    /**
     * Initializes SuccessCodesConstant
     *
     * @author little940301@gmail.com
     * @since 1.0
     */
    private SuccessCodesConstant() {
    }

    /**
     * Loan Application Created
     */
    public static final String LOAN_APPLICATION_CREATED = ModuleCodeConstant.LOAN_APPLICATION.concat("_").concat("RES_000001");

    /**
     * Loan Application Found
     */
    public static final String LOAN_APPLICATION_FOUND = ModuleCodeConstant.LOAN_APPLICATION.concat("_").concat("RES_000002");

    /**
     * Loan Application Deleted
     */
    public static final String LOAN_APPLICATION_DELETED = ModuleCodeConstant.LOAN_APPLICATION.concat("_").concat("RES_000003");

    /**
     * Loan Application Updated
     */
    public static final String LOAN_APPLICATION_UPDATED = ModuleCodeConstant.LOAN_APPLICATION.concat("_").concat("RES_000004");

    /**
     * Loan Application Found
     */
    public static final String ZIP_CODE_INFO_FOUND = ModuleCodeConstant.ZIP_CODE.concat("_").concat("RES_000001");
    
}
