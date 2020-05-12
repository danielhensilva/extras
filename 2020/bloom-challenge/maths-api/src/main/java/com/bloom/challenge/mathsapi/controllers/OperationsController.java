package com.bloom.challenge.mathsapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class OperationsController {

//    private final MathsServicesClient client;
//
//    @Autowired
//    public OperationsController(MathsServicesClient client) {
//        this.client = client;
//    }

    @PostMapping("/sum")
    public CompletableFuture<ResponseEntity<Integer>> sum(
            @RequestParam(name="a") /*@ApiParam(value="First number")*/ int a,
            @RequestParam(name="b") /*@ApiParam(value="Second number")*/ int b) {
//        return this.client.sum(a, b).thenApply(result -> result == null
//                ? ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build()
//                : ResponseEntity.ok(result));
        return CompletableFuture.completedFuture(ResponseEntity.ok(1));
    }

}
