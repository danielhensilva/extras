package com.bloom.challenge.mathsservices.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operations")
public class OperationsController {

    @PostMapping("/sum")
    public int sum(int a, int b) {
        return a + b;
    }

}

