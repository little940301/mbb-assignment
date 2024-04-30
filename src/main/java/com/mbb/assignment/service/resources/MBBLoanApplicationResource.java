package com.mbb.assignment.service.resources;

import com.mbb.assignment.service.constants.ErrorCodesConstant;
import com.mbb.assignment.service.constants.SuccessCodesConstant;
import com.mbb.assignment.service.dto.request.MBBLoanApplicationRequestModel;
import com.mbb.assignment.service.dto.response.MBBLoanApplicationResponseModel;
import com.mbb.assignment.service.dto.response.base.ApplicationExceptionResponse;
import com.mbb.assignment.service.dto.response.base.BaseResponse;
import com.mbb.assignment.service.services.MBBLoanApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.links.LinkParameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

/**
 * The MBBLoanApplicationResource class will be used for Loan Application operations.
 *
 * @author little940301@gmail.com
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/v1/api/mbb-loan-application")
@Tag(name = "MBB Loan Application Resource", description = "MBB Loan Application Resource Related Endpoints")
public class MBBLoanApplicationResource {

    /**
     * MBBLoanApplicationService
     */
    private final MBBLoanApplicationService mbbLoanApplicationService;

    /**
     * messageSource
     */
    private final MessageSource messageSource;

    /**
     * This constructor is used to initialize instances created in MBBLoanApplicationResource class.
     *
     * @param mbbLoanApplicationService instance of MBBLoanApplicationService
     * @param messageSource               instance of MessageSource
     * @author little940301@gmail.com
     * @since 1.0
     */
    public MBBLoanApplicationResource(MBBLoanApplicationService mbbLoanApplicationService, MessageSource messageSource) {
        this.mbbLoanApplicationService = mbbLoanApplicationService;
        this.messageSource = messageSource;
    }

    /**
     * Create MBB Loan Application request.
     *
     * @param mbbLoanApplicationRequestModel MBBLoanApplicationRequestModel.
     * @return MBB Loan Application Response.
     * @author little940301@gmail.com
     * @since 1.0
     */
    @Operation(summary = "Create MBB Loan Application")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully Created MBB Loan Application",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = BaseResponse.class))
                            }),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Duplicate MBB Loan Application Found",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ApplicationExceptionResponse.class))
                            }),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ApplicationExceptionResponse.class))
                            },
                            links = {
                                    @Link(
                                            name = "Create MBB Loan Application's by mbbLoanApplicationRequestModel",
                                            operationId = "createMBBLoanApplication",
                                            requestBody = "mbbLoanApplicationRequestModel"
                                    )
                            }
                    )
            })
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<MBBLoanApplicationResponseModel>> createMBBLoanApplication(
            @Valid @RequestBody MBBLoanApplicationRequestModel mbbLoanApplicationRequestModel) {
        BaseResponse baseResponse = new BaseResponse(HttpStatus.CREATED.value(), messageSource.getMessage(SuccessCodesConstant.LOAN_APPLICATION_CREATED, null, Locale.getDefault()), mbbLoanApplicationService.createMBBLoanApplication(mbbLoanApplicationRequestModel));
        return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
    }

    /**
     * Update MBB Loan Application request.
     *
     * @param mbbLoanApplicationRequestModel MBBLoanApplicationRequestModel.
     * @return MBB Loan Application Response.
     * @author little940301@gmail.com
     * @since 1.0
     */
    @Operation(summary = "Update MBB Loan Application")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully Updated MBB Loan Application",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = BaseResponse.class))
                            }),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Duplicate MBB Loan Application Found",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ApplicationExceptionResponse.class))
                            }),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ApplicationExceptionResponse.class))
                            },
                            links = {
                                    @Link(
                                            name = "Update MBB Loan Application's by mbbLoanApplicationRequestModel",
                                            operationId = "updateMBBLoanApplication",
                                            requestBody = "mbbLoanApplicationRequestModel"
                                    )
                            }
                    )
            })
    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<MBBLoanApplicationResponseModel>> updateMBBLoanApplication(
            @Valid @RequestBody MBBLoanApplicationRequestModel mbbLoanApplicationRequestModel) {
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), messageSource.getMessage(SuccessCodesConstant.LOAN_APPLICATION_UPDATED, null, Locale.getDefault()), mbbLoanApplicationService.updateMBBLoanApplication(mbbLoanApplicationRequestModel));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    /**
     * Get list of MBB Loan Application's which are in active mode.
     *
     * @param mbbLoanApplicationRequestModel MBBLoanApplicationRequestModel.
     * @param page Page Number.
     * @param size Page Size.
     * @param sort Sorting Criteria.
     * @param sortColumn Sorting Column.
     * @return mbbLoanApplicationResponseModel MBBLoanApplicationResponse.
     * @author little940301@gmail.com
     * @since 1.0
     */
    @Operation(summary = "Get list of MBB Loan Application(s)")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully Retrieved MBB Loan Application(s)",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = BaseResponse.class))
                            }),
                    @ApiResponse(
                            responseCode = "404",
                            description = "MBB Loan Application Not Found",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ApplicationExceptionResponse.class))
                            }),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ApplicationExceptionResponse.class))
                            },
                            links = {
                                    @Link(
                                            name = "Get MBB Loan Application by MBBLoanApplicationRequestModel",
                                            operationId = "getMBBLoanApplication",
                                            requestBody = "mbbLoanApplicationRequestModel"
                                    )
                            }
                    )
            })
    @PostMapping(
            path = "/list",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<Page<MBBLoanApplicationResponseModel>>> getMBBLoanApplication(
            @Valid @RequestBody MBBLoanApplicationRequestModel mbbLoanApplicationRequestModel,
            @Parameter(description = "Page number") @RequestParam(defaultValue = "0") Integer page,
            @Parameter(description = "Number of items per page") @RequestParam(defaultValue = "10") Integer size,
            @Parameter(description = "Sorting criteria") @RequestParam(defaultValue = "asc") String sort,
            @Parameter(description = "Sort column") @RequestParam(defaultValue = "id") String sortColumn) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(sort), sortColumn);
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), messageSource.getMessage(SuccessCodesConstant.LOAN_APPLICATION_FOUND, null, Locale.getDefault()), mbbLoanApplicationService.getMBBLoanApplication(mbbLoanApplicationRequestModel, pageable));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    /**
     * Get The MBB Loan Application by Id.
     *
     * @param id MBB Loan Application Id.
     * @return mbbLoanApplicationResponseModel MBBLoanApplicationResponse.
     * @author little940301@gmail.com
     * @since 1.0
     */
    @Operation(summary = "Get MBB Loan Application By Id")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully Retrieved MBB Loan Application",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = BaseResponse.class))
                            }),
                    @ApiResponse(
                            responseCode = "404",
                            description = "MBB Loan Application Not Found",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ApplicationExceptionResponse.class))
                            }),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ApplicationExceptionResponse.class))
                            },
                            links = {
                                    @Link(
                                            name = "Get MBB Loan Application by valid id",
                                            operationId = "getMBBLoanApplicationById",
                                            parameters = {
                                                    @LinkParameter(name = "id", expression = "$request.query.id"),
                                            }
                                    )
                            }
                    )
            })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<Page<MBBLoanApplicationResponseModel>>> getMBBLoanApplicationById(
            @Valid @Parameter(description = "ID") @RequestParam String id) {
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), messageSource.getMessage(SuccessCodesConstant.LOAN_APPLICATION_FOUND, null, Locale.getDefault()), mbbLoanApplicationService.getMBBLoanApplicationById(id));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    /**
     * Delete MBB Loan Application by Id.
     *
     * @param id MBB Loan Application ID.
     * @return MBB Loan Application Response.
     * @author little940301@gmail.com
     * @since 1.0
     */
    @Operation(summary = "Delete MBB Loan Application by Id")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "202",
                            description = "Successfully Deleted MBB Loan Application",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = BaseResponse.class))
                            }),
                    @ApiResponse(
                            responseCode = "406",
                            description = "Failed to Delete MBB Loan Application",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ApplicationExceptionResponse.class))
                            }),
                    @ApiResponse(
                            responseCode = "404",
                            description = "MBB Loan Application Not Found",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ApplicationExceptionResponse.class))
                            }),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ApplicationExceptionResponse.class))
                            },
                            links = {
                                    @Link(
                                            name = "Delete MBB Loan Application by Id",
                                            operationId = "deleteMBBLoanApplicationById",
                                            parameters = {
                                                    @LinkParameter(name = "id", expression = "$request.query.id")
                                            }
                                    )
                            }
                    )
            })
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> deleteMBBLoanApplicationById(@Valid @Parameter(description = "ID") @RequestParam String id) {
        if (mbbLoanApplicationService.deleteMBBLoanApplicationById(id)) {
            BaseResponse baseResponse = new BaseResponse(HttpStatus.ACCEPTED.value(), messageSource.getMessage(SuccessCodesConstant.LOAN_APPLICATION_DELETED, null, Locale.getDefault()), null);
            return new ResponseEntity<>(baseResponse, HttpStatus.ACCEPTED);
        }
        BaseResponse baseResponse = new BaseResponse(HttpStatus.NOT_ACCEPTABLE.value(), messageSource.getMessage(ErrorCodesConstant.LOAN_APPLICATION_DELETE_FAILED, null, Locale.getDefault()), null);
        return new ResponseEntity<>(baseResponse, HttpStatus.NOT_ACCEPTABLE);
    }

}
