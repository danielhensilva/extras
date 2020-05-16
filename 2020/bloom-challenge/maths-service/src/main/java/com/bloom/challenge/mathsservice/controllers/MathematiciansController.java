package com.bloom.challenge.mathsservice.controllers;

import com.bloom.challenge.mathsservice.domain.Mathematician;
import com.bloom.challenge.mathsservice.domain.MathematicianRepository;
import com.bloom.challenge.mathsservice.errors.ApiValidationException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mathematicians")
@Api(value="mathematicians")
public class MathematiciansController {

    private final MathematicianRepository repository;

    @Autowired
    public MathematiciansController(MathematicianRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    @ApiOperation(value="Retrieves a paged list of mathematicians sorted by name")
    public ResponseEntity<Object> getPagedMathematicians(
            @RequestParam(name="pageNumber") @ApiParam(value="Page number") int pageNumber,
            @RequestParam(name="pageSize") @ApiParam(value="Page size") int pageSize) {

        if (pageNumber < 0) {
            throw new ApiValidationException("Page number cannot be negative, but found " + pageNumber);
        }

        if (pageSize < 1 || pageSize > 100) {
            throw new ApiValidationException("Page size must be between 1 and 100, but found " + pageSize);
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
        Page<Mathematician> mathematicians = repository.findAll(pageable);

        return ResponseEntity.ok(mathematicians);
    }

}