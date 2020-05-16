package com.bloom.challenge.mathsservice.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OperationsController.class)
public class OperationsControllerUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private OperationsController operationsController;

    @Test
    public void shouldReturnTheSumOfTwoNumbers() throws Exception {
        mockMvc
                .perform(put("/sum?a=4&b=9"))
                .andExpect(status().isOk())
                .andReturn();

        // TODO continue here

    }

}
