package com.bloom.challenge.mathsservice.controllers;

import com.bloom.challenge.mathsservice.domain.Mathematician;
import com.bloom.challenge.mathsservice.domain.MathematicianRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MathematiciansController.class)
public class MathematiciansControllerUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MathematicianRepository mathematicianRepository;

    @InjectMocks
    private MathematiciansController mathematiciansController;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnBadRequestWhenPageNumberIsTooLow() throws Exception {
        MvcResult result = mockMvc
                .perform(
                        get("/mathematicians/?pageNumber=-1&pageSize=2")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertThat(content).isEqualTo("{"
                + "\"type\":\"400 BAD_REQUEST\","
                + "\"message\":\"Validation error! Page number cannot be negative, but found -1\""
                + "}");
    }

    @Test
    public void shouldReturnBadRequestWhenPageSizeIsTooLow() throws Exception {
        MvcResult result = mockMvc
                .perform(
                        get("/mathematicians/?pageNumber=0&pageSize=0")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertThat(content).isEqualTo("{"
                + "\"type\":\"400 BAD_REQUEST\","
                + "\"message\":\"Validation error! Page size must be between 1 and 100, but found 0\""
                + "}");
    }

    @Test
    public void shouldReturnBadRequestWhenPageSizeIsTooHigh() throws Exception {
        MvcResult result = mockMvc
                .perform(
                        get("/mathematicians/?pageNumber=0&pageSize=101")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertThat(content).isEqualTo("{"
                + "\"type\":\"400 BAD_REQUEST\","
                + "\"message\":\"Validation error! Page size must be between 1 and 100, but found 101\""
                + "}");
    }

    @Test
    public void shouldReturnMathematiciansList() throws Exception {
        // Arrange
        var mathematician = new Mathematician();
        mathematician.setName("Test Mathematician");

        var mathematicians = new ArrayList<Mathematician>();
        mathematicians.add(mathematician);

        when(mathematicianRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(mathematicians));

        // Act
        MvcResult result = mockMvc
                .perform(
                        get("/mathematicians/?pageNumber=0&pageSize=100")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        String content = result.getResponse().getContentAsString();
        assertThat(content).contains("\"name\":\"Test Mathematician\",");
    }

}
