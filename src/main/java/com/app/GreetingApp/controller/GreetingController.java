package com.app.GreetingApp.controller;

import com.app.GreetingApp.dto.GreetingDTO;
import com.app.GreetingApp.model.Greeting;
import com.app.GreetingApp.services.GreetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

    private final GreetingService greetingService;

    // Constructor-Based Dependency Injection
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    // GET Request - Default Greeting
    @GetMapping
    public String getGreeting() {
        logger.info("Received GET request for default greeting");
        return "{\"message\": \"Hello, World!\"}";
    }

    // GET Request with Path Variable
    @GetMapping("/{name}")
    public String getPersonalizedGreeting(@PathVariable String name) {
        logger.info("Received GET request for personalized greeting with name: {}", name);
        return "{\"message\": \"Hello, " + name + "!\"}";
    }

    // POST Request - Accepts a Name and Returns Greeting
    @PostMapping
    public String createGreeting(@RequestBody String name) {
        logger.info("Received POST request to create greeting for: {}", name);
        return "{\"message\": \"Hello, " + name + "! Your greeting has been created.\"}";
    }

    // PUT Request - Update a Greeting
    @PutMapping("/{name}")
    public String updateGreeting(@PathVariable String name) {
        logger.info("Received PUT request to update greeting for: {}", name);
        return "{\"message\": \"Hello, " + name + "! Your greeting has been updated.\"}";
    }

    // DELETE Request - Delete a Greeting
    @DeleteMapping("/{name}")
    public String deleteGreeting(@PathVariable String name) {
        logger.info("Received DELETE request to remove greeting for: {}", name);
        return "{\"message\": \"Goodbye, " + name + "! Your greeting has been deleted.\"}";
    }

    // UC2: Extend GreetingController with Service Layer
    @GetMapping("/greeting-from-service")
    public String getGreetingFromService() {
        logger.info("Received GET request to fetch greeting from service");
        String response = "{\"message\": \"" + greetingService.getGreeting() + "\"}";
        logger.debug("Service Response: {}", response);
        return response;
    }



    // GET Request - Personalized Greeting
    @GetMapping("/personalized")
    public String getPersonalizedGreeting(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {

        logger.info("Received GET request for personalized greeting with firstName: {} and lastName: {}", firstName, lastName);

        String message = greetingService.getPersonalizedGreeting(firstName, lastName);
        logger.debug("Generated Greeting: {}", message);

        return "{\"message\": \"" + message + "\"}";
    }

    // POST Request - Save Greeting Message
    @PostMapping("/save")
    public GreetingDTO saveGreeting(@RequestBody Greeting greeting) {
        logger.info("Saving the request for Greeting : {}", greeting);
        return greetingService.saveGreeting(greeting);
    }

    // GET Request - Find Greeting by ID
    @GetMapping("/messages/{id}")
    public GreetingDTO getGreetingById(@PathVariable Long id) {
        logger.info("Received GET request for Greeting with ID: {}", id);
        return greetingService.findGreetingById(id);
    }
    @GetMapping("/messages/all")
    // GET Request - Fetching all Messages
    public List<Greeting> getAll() {
        logger.info("Received GET request for listing all greetings");
        return greetingService.getAll();
    }

    // PUT Request - Update a Greeting Message
    @PutMapping("/update/{id}")
    public Greeting updateGreeting(@PathVariable Long id, @RequestBody String newMessage) {
        logger.info("Received PUT request to update Greeting ID: {}", id);
        return greetingService.updateGreeting(id, newMessage);
    }


    // DELETE Request - Delete a Greeting Message
    @DeleteMapping("/delete/{id}")
    public String deleteGreeting(@PathVariable Long id) {
        logger.info("Received DELETE request for Greeting ID: {}", id);
        greetingService.deleteGreeting(id);
        return "Greeting with ID " + id + " has been deleted successfully.";
    }
}
