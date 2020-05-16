package com.bloom.challenge.mathsapi.controllers;

import com.bloom.challenge.mathsapi.clients.MathsServiceClient;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class OperationsController {

    private final MathsServiceClient client;

    @Autowired
    public OperationsController(MathsServiceClient client) {
        this.client = client;
    }

    @GetMapping("/sum")
    @ApiOperation(value="Sum two values")
    public CompletableFuture<ResponseEntity<Integer>> sum(
            @RequestParam(name="a") @ApiParam(value="First number") int a,
            @RequestParam(name="b") @ApiParam(value="Second number") int b) {
        return this.client.sum(a, b).thenApply(result -> result == null
                ? ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build()
                : ResponseEntity.ok(result));
    }

}
