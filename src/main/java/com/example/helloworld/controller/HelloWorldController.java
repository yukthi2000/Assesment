package com.example.helloworld.controller;

import com.example.helloworld.dto.ErrorResponse;
import com.example.helloworld.dto.MessageResponse;
import com.example.helloworld.service.NameValidationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldController {

    private final NameValidationService nameValidationService;

    public HelloWorldController(NameValidationService nameValidationService) {
        this.nameValidationService = nameValidationService;
    }

    @GetMapping("/hello-world")
    public ResponseEntity<?> helloWorld(@RequestParam(value = "name", required = false) String name) {
        // Check if name is missing or empty
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Invalid Input"));
        }

        // Validate name based on first letter
        if (!nameValidationService.isValidName(name)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Invalid Input"));
        }

        // Format name and return success response
        String formattedName = nameValidationService.formatName(name);
        return ResponseEntity
                .ok(new MessageResponse("Hello " + formattedName));
    }
}
