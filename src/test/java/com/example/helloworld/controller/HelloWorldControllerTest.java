package com.example.helloworld.controller;

import com.example.helloworld.service.NameValidationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(HelloWorldController.class)
class HelloWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public NameValidationService nameValidationService() {
            return new NameValidationService();
        }
    }

    @Test
    void shouldReturnGreetingForValidName() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", "alice"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is("Hello Alice")));
    }

    @Test
    void shouldHandleDifferentCases() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", "BOB"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Hello Bob")));
    }

    @Test
    void shouldRejectInvalidName() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", "nancy"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("Invalid Input")));
    }

    @Test
    void shouldRejectMissingName() throws Exception {
        mockMvc.perform(get("/hello-world"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("Invalid Input")));
    }

    @Test
    void shouldRejectEmptyName() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", ""))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("Invalid Input")));
    }

    @Test
    void shouldTestBoundaryBetweenMAndN() throws Exception {
        // M should be valid
        mockMvc.perform(get("/hello-world").param("name", "Mike"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Hello Mike")));
        
        // N should be invalid
        mockMvc.perform(get("/hello-world").param("name", "Nancy"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("Invalid Input")));
    }
}
