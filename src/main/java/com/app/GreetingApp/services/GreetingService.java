package com.app.GreetingApp.services;

import com.app.GreetingApp.dto.GreetingDTO;
import com.app.GreetingApp.model.Greeting;
import com.app.GreetingApp.repository.GreetingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GreetingService {

    private static final Logger logger = LoggerFactory.getLogger(GreetingService.class);

    private final GreetingRepository greetingRepository;

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

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



    public GreetingDTO saveGreeting(Greeting greeting) {
        logger.info("Saving greeting message: {}", greeting.getId());
        Greeting greeting1 = greetingRepository.save(greeting);
        return new GreetingDTO(greeting1.getId(), greeting1.getMessage());
    }

    // Method to find a Greeting by ID
    public GreetingDTO findGreetingById(Long id) {
        logger.info("Fetching greeting with ID: {}", id);
        Greeting greeting =  greetingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Greeting not found with ID: " + id));

        return new GreetingDTO(greeting.getId(), greeting.getMessage());
    }

    // Retrieve all greetings
    public List<Greeting> getAll() {
        logger.info("Fetching all greeting messages");
        return greetingRepository.findAll();
    }

    public Greeting updateGreeting(Long id, String newMessage){
        logger.info("Updating Greeting with ID: {}", id);

        // Find the existing greeting
        Optional<Greeting> optionalGreeting = greetingRepository.findById(id);

        if (optionalGreeting.isPresent()) {
            Greeting greeting = optionalGreeting.get();
            greeting.setMessage(newMessage);
            return greetingRepository.save(greeting);
        } else {
            throw new RuntimeException("Greeting not found with ID: " + id);
        }

    }

    // Method to delete a Greeting Message by ID
    public void deleteGreeting(Long id) {
        logger.info("Attempting to delete Greeting with ID: {}", id);

        if (greetingRepository.existsById(id)) {
            greetingRepository.deleteById(id);
            logger.info("Greeting with ID {} deleted successfully", id);
        } else {
            throw new RuntimeException("Greeting not found with ID: " + id);
        }
    }
}
