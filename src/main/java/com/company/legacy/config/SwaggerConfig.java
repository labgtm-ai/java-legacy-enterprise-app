package com.company.legacy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger configuration for the Legacy Employee Management Service.
 *
 * This configuration is intentionally built using Springfox Swagger 2,
 * which was commonly used in Java 8 / Spring Boot 1.x applications.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket employeeApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.company.legacy.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .title("Legacy Employee Management API")
                        .description("REST APIs for Employee Management System")
                        .version("1.0")
                        .contact(new Contact(
                                "Legacy Development Team",
                                "https://company.com",
                                "legacy-support@company.com"))
                        .license("Internal Enterprise License")
                        .licenseUrl("https://company.com")
                        .build());

    }

}