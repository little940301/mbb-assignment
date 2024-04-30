package com.mbb.assignment.service.resources;

import com.mbb.assignment.service.constants.SuccessCodesConstant;
import com.mbb.assignment.service.dto.response.ZipCodeInfoResponseModel;
import com.mbb.assignment.service.dto.response.base.ApplicationExceptionResponse;
import com.mbb.assignment.service.dto.response.base.BaseResponse;
import com.mbb.assignment.service.services.ZipCodeInfoService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

/**
 * The ZipCodeInfoResource class will be used for Zip Code Info operations.
 *
 * @author little940301@gmail.com
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/v1/api/zip-code")
@Tag(name = "Zip Code Info Resource", description = "Zip Code Info Resource Related Endpoints")
public class ZipCodeInfoResource {

    /**
     * zipCodeInfoService
     *
     */
    private ZipCodeInfoService zipCodeInfoService;

    /**
     * messageSource
     */
    private final MessageSource messageSource;

    /**
     * This constructor is used to initialize instances created in ZipCodeInfoResource class.
     *
     * @param messageSource               instance of MessageSource
     * @author little940301@gmail.com
     * @since 1.0
     */
    public ZipCodeInfoResource(ZipCodeInfoService zipCodeInfoService, MessageSource messageSource) {
        this.zipCodeInfoService = zipCodeInfoService;
        this.messageSource = messageSource;
    }

    /**
     * Get The Zip Code Info by Country Code And Zip Code.
     *
     * @param countryCode Country Code.
     * @param zipCode Zip Code.
     * @return List<ZipCodeInfoResponseModel> List of ZipCodeInfoResponse.
     * @author little940301@gmail.com
     * @since 1.0
     */
    @Operation(summary = "Get Zip Code Info By Id")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully Retrieved Zip Code Info",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = BaseResponse.class))
                            }),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Zip Code Info Not Found",
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
                                            name = "Get Zip Code Info by Country and Zip Code",
                                            operationId = "getZipCodeInfoByCountryCodeZipCode",
                                            parameters = {
                                                    @LinkParameter(name = "countryCode", expression = "$request.query.countryCode"),
                                                    @LinkParameter(name = "zipCode", expression = "$request.query.zipCode"),
                                            }
                                    )
                            }
                    )
            })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<ZipCodeInfoResponseModel>> getZipCodeInfoByCountryCodeZipCode(
            @Valid @Parameter(description = "Country Code") @RequestParam String countryCode,
            @Valid @Parameter(description = "Zip Code") @RequestParam String zipCode) {
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), messageSource.getMessage(SuccessCodesConstant.ZIP_CODE_INFO_FOUND, null, Locale.getDefault()), zipCodeInfoService.getZipCodeInfoByCountryCodeAndZipCode(countryCode, zipCode));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

}
