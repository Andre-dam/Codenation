package com.challenge.endpoints.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceType) {
        super("Resource: " + resourceType + " not found!");
    }
}
