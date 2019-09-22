package com.oreilly.restclient.controllers;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/v1/customers")
public class CustomerRestController {

    private final CustomerRepository customerRepository;

    public CustomerRestController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    ResponseEntity<?> options() {
        return ResponseEntity
                .ok()
                .allow(HttpMethod.GET, HttpMethod.POST,
                        HttpMethod.HEAD, HttpMethod.OPTIONS,
                        HttpMethod.PUT, HttpMethod.DELETE)
                .build();
    }

    @GetMapping
    ResponseEntity<Collection<Customer>> getCollection() {
        return ResponseEntity.ok(this.customerRepository.findAll());
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<Customer> get(@PathVariable Long id) {
        return this.customerRepository.findById(id).map(ResponseEntity::ok)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @PostMapping
    ResponseEntity<Customer> post(@RequestBody Customer c) {
        Customer customer = this.customerRepository.save(new Customer(c
                .getFirstName(), c.getLastName()));

        URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}")
                .buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(uri).body(customer);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        return this.customerRepository.findById(id).map(c -> {
            customerRepository.delete(c);
            return ResponseEntity.noContent().build();
        }).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.HEAD)
    ResponseEntity<?> head(@PathVariable Long id) {
        return this.customerRepository.findById(id)
                .map(exists -> ResponseEntity.noContent().build())
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<Customer> put(@PathVariable Long id, @RequestBody Customer c) {
        return this.customerRepository
                .findById(id)
                .map(
                        existing -> {
                            Customer customer = this.customerRepository.save(new Customer(existing
                                    .getId(), c.getFirstName(), c.getLastName()));
                            URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest()
                                    .toUriString());
                            return ResponseEntity.created(selfLink).body(customer);
                        }).orElseThrow(() -> new CustomerNotFoundException(id));

    }
}