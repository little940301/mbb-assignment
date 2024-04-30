package com.mbb.assignment.service;

import com.mbb.assignment.service.config.ApiDocumentationConfig;
import com.mbb.assignment.service.config.RestTemplateConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * This SpecialCharacterServiceApplication is used to bootstrap the application.
 *
 * @author little940301@gmail.com
 * @version 1.0
 */
@Import(
    {
        RestTemplateConfig.class,
        ApiDocumentationConfig.class,
    }
)
@SpringBootApplication
public class MBBAssignmentServiceApplication {

    /**
     * Runs SpecialCharacterServiceApplication application.
     *
     * @param args arguments.
     * @author little940301@gmail.com
     * @since 1.0
     */
    public static void main(String[] args) {
        SpringApplication.run(MBBAssignmentServiceApplication.class, args);
    }

}
