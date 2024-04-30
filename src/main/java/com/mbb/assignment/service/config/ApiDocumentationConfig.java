package com.mbb.assignment.service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The ApiDocumentationConfig class will be used for configuration
 *
 * @author little940301@gmail.com
 * @version 1.0
 * @since 1.0
 */

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "openapi.info")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class ApiDocumentationConfig {

	/**
     * api title
     */
    @Value("${openapi.info.title}")
    private String apiTitle;

    /**
     * api version
     */
    @Value("${openapi.info.version}")
    private String apiVersion;

    /**
     * server
     */
    @Value("${openapi.info.server}")
    private String server;

    /**
     * ownerName
     */
    @Value("${openapi.info.ownerName}")
    private String ownerName;

    /**
     * ownerEmail
     */
    @Value("${openapi.info.ownerEmail}")
    private String ownerEmail;

    /**
     * termsOfService
     */
    @Value("${openapi.info.termsOfService}")
    private String termsOfService;

    /**
     * licenseName
     */
    @Value("${openapi.info.licenseName}")
    private String licenseName;

    /**
     * licenseUrl
     */
    @Value("${openapi.info.licenseUrl}")
    private String licenseUrl;

    /**
     * activeProfile
     */
    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Bean
    public OpenAPI customOpenAPI() {
        OpenAPI openAPI = new OpenAPI()
                .info(new Info().title(apiTitle)
                        .version(apiVersion)
                        .contact(new Contact()
                                .name(ownerName)
                                .email(ownerEmail))
                        .description(apiTitle)
                        .termsOfService(termsOfService)
                        .license(new License().name(licenseName)
                                .url(licenseUrl)));
        openAPI.addServersItem(new Server().description(activeProfile).url(server));
        return openAPI;
    }

}
