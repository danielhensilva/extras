package com.bloom.challenge.mathsservice.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OperationsControllerIntegrationTests {

    @Autowired
    OperationsController operationsController;

    @Test
    public void shouldReturnTheSumOfTwoNumbers() {
        // Arrange
        int firstNumber = 1;
        int secondNumber = 2;

        // Act
        var response = operationsController.sum(firstNumber, secondNumber);

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(3);
    }

}
