package com.company.legacy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger; // SRAO: Added import for SLF4J Logger
import org.slf4j.LoggerFactory; // SRAO: Added import for SLF4J LoggerFactory

/**
 * Main entry point for the Legacy Employee Management Application.
 *
 * This application demonstrates a legacy Java 8 enterprise
 * application which will later be modernized to latest java version.
 */
@SpringBootApplication
public class LegacyApplication {

    // SRAO: Replaced System.out.println with SLF4J logger
    private static final Logger logger = LoggerFactory.getLogger(LegacyApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(LegacyApplication.class, args);

        logger.info("--------------------------------------------"); // SRAO: Replaced System.out.println with logger.info
        logger.info(" Legacy Employee Management Service Started ");
        logger.info("--------------------------------------------");

    }

}
