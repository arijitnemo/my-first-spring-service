package com.bose.my_services.model;

// A record automatically provides a constructor, getters, equals(), and hashCode().
public record Greeting(
        long id,
        String content
) {}
