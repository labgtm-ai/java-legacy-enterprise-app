package com.company.legacy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger; // SRAO: Replaced System.out.println with SLF4J logger for better logging practices.
import org.slf4j.LoggerFactory;

/**
 * Main entry point for the Legacy Employee Management Application.
 *
 * This application demonstrates a legacy Java 8 enterprise
 * application which will later be modernized to latest java version.
 */
@SpringBootApplication
public class LegacyApplication {

    private static final Logger logger = LoggerFactory.getLogger(LegacyApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(LegacyApplication.class, args);

        logger.info("--------------------------------------------");
        logger.info(" Legacy Employee Management Service Started ");
        logger.info("--------------------------------------------");

    }

}
