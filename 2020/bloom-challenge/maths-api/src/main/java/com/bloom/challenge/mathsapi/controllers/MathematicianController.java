package com.bloom.challenge.mathsapi.controllers;

import com.bloom.challenge.mathsapi.clients.MathsServiceClient;
import com.bloom.challenge.mathsapi.models.Mathematician;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mathematicians")
@Api(value="mathematicians")
public class MathematicianController {

    private final MathsServiceClient client;

    @Autowired
    public MathematicianController(MathsServiceClient client) {
        this.client = client;
    }

    @GetMapping()
    @ApiOperation(value="Retrieves top 10 mathematicians by name")
    public CompletableFuture<ResponseEntity<Object>> getTop10MathematiciansByName() {
        return client.getAllMathematicians(0, 10)
                .thenApply(mathematicians ->  mathematicians
                    .getContent().stream()
                    .map(Mathematician::getName)
                    .collect(Collectors.toList()))
                .thenApply(ResponseEntity::ok);
    }

}