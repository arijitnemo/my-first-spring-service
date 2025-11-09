package com.bose.my_services.model;

// A record automatically provides a constructor, getters, equals(), and hashCode().
// And this class used for data transfer between different layers of the application.
public record Greeting(
        long id,
        String content
) {}
