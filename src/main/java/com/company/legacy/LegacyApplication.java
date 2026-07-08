package com.company.legacy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Legacy Employee Management Application.
 *
 * This application demonstrates a legacy Java 8 enterprise
 * application which will later be modernized to latest java version.
 */
@SpringBootApplication
public class LegacyApplication {

    public static void main(String[] args) {

        SpringApplication.run(LegacyApplication.class, args);

        System.out.println("--------------------------------------------");
        System.out.println(" Legacy Employee Management Service Started ");
        System.out.println("--------------------------------------------");

    }

}
