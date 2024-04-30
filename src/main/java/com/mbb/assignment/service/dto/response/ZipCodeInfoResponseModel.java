package com.mbb.assignment.service.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * The ZipCodeInfoResponseModel represents response from Zip Code Info Endpoint.
 *
 * @author little940301@gmail.com
 * @version 1.0
 * @since 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Schema(title = "Zip Code Info Response Model", description = "Represent Zip Code Info Response model")
public class ZipCodeInfoResponseModel implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8862888613275371214L;

    /**
     * postCode
     *
     * @since 1.0
     */
    @JsonProperty("post code")
    private String postCode;

    /**
     * country
     *
     * @since 1.0
     */
    private String country;

    /**
     * countryAbbreviation
     *
     * @since 1.0
     */
    @JsonProperty("country abbreviation")
    private String countryAbbreviation;

    /**
     * places
     *
     * @since 1.0
     */
    private List<ZipCodePlaceInfoResponseModel> places;

}
