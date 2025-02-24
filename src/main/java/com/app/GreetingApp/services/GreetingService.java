package com.app.GreetingApp.services;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    public String getGreeting(){
        return "Hello World";
    }
}
