package com.app.GreetingApp.controller;



import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    // GET Request - Default Greeting
    @GetMapping
    public String getGreeting() {
        return "{\"message\": \"Hello, World!\"}";
    }

    // GET Request with Path Variable
    @GetMapping("/{name}")
    public String getPersonalizedGreeting(@PathVariable String name) {
        return "{\"message\": \"Hello, " + name + "!\"}";
    }

    // POST Request - Accepts a Name and Returns Greeting
    @PostMapping
    public String createGreeting(@RequestBody String name) {
        return "{\"message\": \"Hello, " + name + "! Your greeting has been created.\"}";
    }

    // PUT Request - Update a Greeting
    @PutMapping("/{name}")
    public String updateGreeting(@PathVariable String name) {
        return "{\"message\": \"Hello, " + name + "! Your greeting has been updated.\"}";
    }

    // DELETE Request - Delete a Greeting
    @DeleteMapping("/{name}")
    public String deleteGreeting(@PathVariable String name) {
        return "{\"message\": \"Goodbye, " + name + "! Your greeting has been deleted.\"}";
    }
}


