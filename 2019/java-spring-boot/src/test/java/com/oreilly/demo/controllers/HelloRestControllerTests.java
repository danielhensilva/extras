package com.oreilly.demo.controllers;

import com.oreilly.demo.entities.Greeting;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloRestControllerTests {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void greetWithoutName() {
        var entity = template.getForEntity("/rest", Greeting.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON_UTF8, entity.getHeaders().getContentType());
        var response = entity.getBody();
        assertNotNull(response);
        assertEquals("Hello, world!", response.getMessage());
    }

    @Test
    public void greetWithName() {
        var response = template.getForObject("/rest?name=Dolly", Greeting.class);
        assertNotNull(response);
        assertEquals("Hello, Dolly!", response.getMessage());
    }

}
