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
 * The ZipCodePlaceInfoResponseModel represents response from Zip Code Place Info Endpoint.
 *
 * @author little940301@gmail.com
 * @version 1.0
 * @since 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Schema(title = "Zip Code Place Info Response Model", description = "Represent Zip Code Place Info Response model")
public class ZipCodePlaceInfoResponseModel implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8812888613275321214L;

    /**
     * placeName
     *
     * @since 1.0
     */
    @JsonProperty("place name")
    private String placeName;

    /**
     * longitude
     *
     * @since 1.0
     */
    private String longitude;

    /**
     * latitude
     *
     * @since 1.0
     */
    private String latitude;

    /**
     * state
     *
     * @since 1.0
     */
    private String state;

    /**
     * stateAbbreviation
     *
     * @since 1.0
     */
    @JsonProperty("state abbreviation")
    private String stateAbbreviation;

}
