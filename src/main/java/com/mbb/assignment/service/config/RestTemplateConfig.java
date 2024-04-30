package com.mbb.assignment.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configures RestTemplate bean.
 *
 * @author little940301@gmail.com
 * @version 1.0
 * @since 1.0
 */
@Configuration
public class RestTemplateConfig {

    /**
     * Creates instance of RestTemplate
     *
     * @return instance of RestTemplate
     * @author little940301@gmail.com
     * @since 1.0
     */
    @Bean
    public RestTemplate restTemplate() {
        final RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }
}
