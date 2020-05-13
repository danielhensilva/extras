package com.bloom.challenge.mathsservices.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operations")
@Api(value="operations")
public class OperationsController {

    @PutMapping("/sum")
    @ApiOperation(value="Sum two values")
    public ResponseEntity<Integer> sum(
            @RequestParam(name="a") @ApiParam(value="First number") int a,
            @RequestParam(name="b") @ApiParam(value="Second number")  int b) {
        return ResponseEntity.ok(a + b);
    }

}

