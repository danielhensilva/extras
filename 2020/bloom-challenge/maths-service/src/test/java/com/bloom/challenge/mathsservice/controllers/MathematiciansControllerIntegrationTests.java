package com.bloom.challenge.mathsservice.controllers;

import com.bloom.challenge.mathsservice.domain.Mathematician;
import com.bloom.challenge.mathsservice.errors.ApiValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MathematiciansControllerIntegrationTests {

    @Autowired
    MathematiciansController mathematiciansController;

    @Test
    public void shouldThrownValidationExceptionWhenPageNumberIsTooLow() {
        // Arrange
        int pageNumber = -1;
        int pageSize = 10;

        // Act
        var exception = assertThatThrownBy(() -> mathematiciansController
                .getPagedMathematicians(pageNumber, pageSize));

        // Assert
        exception.hasMessage("Page number cannot be negative, but found -1")
                .isInstanceOf(ApiValidationException.class);
    }

    @Test
    public void shouldThrownValidationExceptionWhenPageSizeIsTooLow() {
        // Arrange
        int pageNumber = 0;
        int pageSize = 0;

        // Act
        var exception = assertThatThrownBy(() -> mathematiciansController
                .getPagedMathematicians(pageNumber, pageSize));

        // Assert
        exception.hasMessage("Page size must be between 1 and 100, but found 0")
                .isInstanceOf(ApiValidationException.class);
    }

    @Test
    public void shouldThrownValidationExceptionWhenPageSizeIsTooHigh() {
        // Arrange
        int pageNumber = 0;
        int pageSize = 101;

        // Act
        var exception = assertThatThrownBy(() -> mathematiciansController
                .getPagedMathematicians(pageNumber, pageSize));

        // Assert
        exception.hasMessage("Page size must be between 1 and 100, but found 101")
                .isInstanceOf(ApiValidationException.class);
    }

    @Test
    public void shouldReturnFirstMathematicianWhenPageNumberIsZeroAndPageSizeIsOne() {
        // Arrange
        int pageNumber = 0;
        int pageSize = 1;

        // Act
        var response = mathematiciansController
                .getPagedMathematicians(pageNumber, pageSize);

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isInstanceOf(Page.class);

        var mathematicianPage = (Page<Mathematician>) response.getBody();
        assertThat(mathematicianPage.getTotalElements()).isEqualTo(3);
        assertThat(mathematicianPage.getTotalPages()).isEqualTo(3);

        var mathematicians = mathematicianPage.get().collect(Collectors.toList());
        assertThat(mathematicians).hasSize(1);
        assertThat(mathematicians.get(0).getName()).isEqualTo("Carl Gauss");
    }

    @Test
    public void shouldReturnAllMathematiciansWhenPageNumberIsZeroAndPageSizeIs100() {
        // Arrange
        int pageNumber = 0;
        int pageSize = 100;

        // Act
        var response = mathematiciansController
                .getPagedMathematicians(pageNumber, pageSize);

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isInstanceOf(Page.class);

        var mathematicianPage = (Page<Mathematician>) response.getBody();
        assertThat(mathematicianPage.getTotalElements()).isEqualTo(3);
        assertThat(mathematicianPage.getTotalPages()).isEqualTo(1);

        var mathematicians = mathematicianPage.get().collect(Collectors.toList());
        assertThat(mathematicians).hasSize(3);
        assertThat(mathematicians.get(0).getName()).isEqualTo("Carl Gauss");
        assertThat(mathematicians.get(1).getName()).isEqualTo("Euclid");
        assertThat(mathematicians.get(2).getName()).isEqualTo("Isaac Newton");
    }

}
