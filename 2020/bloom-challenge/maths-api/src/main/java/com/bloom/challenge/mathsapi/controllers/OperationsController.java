package com.bloom.challenge.mathsapi.controllers;

import com.bloom.challenge.mathsapi.clients.MathsServicesClient;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class OperationsController {

    private final MathsServicesClient client;

    @Autowired
    public OperationsController(MathsServicesClient client) {
        this.client = client;
    }

    @PutMapping("/sum")
    public CompletableFuture<ResponseEntity<Integer>> sum(
            @RequestParam(name="a") @ApiParam(value="First number") int a,
            @RequestParam(name="b") @ApiParam(value="Second number") int b) {
        return this.client.sum(a, b).thenApply(result -> result == null
                ? ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build()
                : ResponseEntity.ok(result));
    }

}
