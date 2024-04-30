package com.mbb.assignment.service.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbb.assignment.service.constants.ErrorCodesConstant;
import com.mbb.assignment.service.dto.response.ZipCodeInfoResponseModel;
import com.mbb.assignment.service.exception.base.ItemNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Locale;

/**
 * The ZipCodeInfoService class will be used for Zip Code Info functions.
 *
 * @author little940301@gmail.com
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Service
public class ZipCodeInfoService {

    /**
     * restTemplate
     */
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * messageSource
     */
    private final MessageSource messageSource;

    /**
     * This constructor is used to initialize instances created in ZipCodeInfoService.
     *
     * @param messageSource                            instance of MessageSource
     * @author little940301@gmail.com
     * @since 1.0
     */
    public ZipCodeInfoService(
            MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Get Zip Code Info.
     *
     * @return List<ZipCodeInfoResponseModel> List of Zip Code Info Response.
     * @author little940301@gmail.com
     * @since 1.0
     */
    public ZipCodeInfoResponseModel getZipCodeInfoByCountryCodeAndZipCode(String countryCode, String zipCode) {
        try {
            // Create an HttpClient instance
            HttpClient httpClient = HttpClient.newHttpClient();

            // Create the API endpoint URL with the given postal code
            String endpointUrl = "https://api.zippopotam.us/" + countryCode + "/" + zipCode;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endpointUrl))
                    .build();

            // Send the HTTP request and retrieve the response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Check the response status code
            if (response.statusCode() == 200) {

                // Parse the JSON response into a list of LocationData objects
                ZipCodeInfoResponseModel zipCodeInfoResponses = objectMapper.readValue(
                        response.body(),
                        new TypeReference<ZipCodeInfoResponseModel>() {}
                );
                return zipCodeInfoResponses;
            } else {
                throw new ItemNotFoundException(messageSource
                        .getMessage(ErrorCodesConstant.ZIP_CODE_INFO_RETRIEVAL_FAILED,
                                new Object[]{}, Locale.getDefault()));
            }
        } catch (Exception e) {
            throw new ItemNotFoundException(messageSource
                    .getMessage(ErrorCodesConstant.ZIP_CODE_INFO_RETRIEVAL_FAILED,
                            new Object[]{}, Locale.getDefault()));
        }
    }

}
