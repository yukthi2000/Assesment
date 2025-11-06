package com.example.helloworld.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class NameValidationServiceTest {

    private NameValidationService nameValidationService;

    @BeforeEach
    void setUp() {
        nameValidationService = new NameValidationService();
    }

    @Test
    void shouldAcceptValidNames() {
        assertTrue(nameValidationService.isValidName("Alice"));
        assertTrue(nameValidationService.isValidName("bob"));
        assertTrue(nameValidationService.isValidName("Mike"));
    }

    @Test
    void shouldRejectInvalidNames() {
        assertFalse(nameValidationService.isValidName("Nancy"));
        assertFalse(nameValidationService.isValidName("zoe"));
    }

    @Test
    void shouldRejectNullOrEmpty() {
        assertFalse(nameValidationService.isValidName(null));
        assertFalse(nameValidationService.isValidName(""));
        assertFalse(nameValidationService.isValidName("   "));
    }

    @Test
    void shouldHandleBoundaryLetters() {
        // M is the last valid letter
        assertTrue(nameValidationService.isValidName("Mike"));
        // N is the first invalid letter
        assertFalse(nameValidationService.isValidName("Nancy"));
    }

    @Test
    void shouldFormatNamesCorrectly() {
        assertEquals("Alice", nameValidationService.formatName("alice"));
        assertEquals("Bob", nameValidationService.formatName("BOB"));
        assertEquals("Mike", nameValidationService.formatName("  mike  "));
    }
}
