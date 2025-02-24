package com.app.GreetingApp.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    private static final Logger logger = LoggerFactory.getLogger(GreetingService.class);

    public String getGreeting() {
        logger.info("Generating greeting message from GreetingService");
        return "Hello World";
    }

    public String getPersonalizedGreeting(String firstName, String lastName) {
        logger.info("Generating personalized greeting for firstName: {} and lastName: {}", firstName, lastName);

        if (firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty()) {
            return "Hello, " + firstName + " " + lastName + "!";
        } else if (firstName != null && !firstName.isEmpty()) {
            return "Hello, " + firstName + "!";
        } else if (lastName != null && !lastName.isEmpty()) {
            return "Hello, " + lastName + "!";
        } else {
            return "Hello World";
        }
    }
}
