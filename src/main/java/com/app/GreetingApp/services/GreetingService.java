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
}
