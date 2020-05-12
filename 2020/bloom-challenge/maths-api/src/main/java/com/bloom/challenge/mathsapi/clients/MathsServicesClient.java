package com.bloom.challenge.mathsapi.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.CompletableFuture;

@FeignClient(value = "Maths-Services", fallback = MathsServicesFallbackImpl.class)
public interface MathsServicesClient {

    @RequestMapping(method = RequestMethod.PUT, value = "/operations/sum", produces = "application/json")
    CompletableFuture<Integer> sum(
            @RequestParam(name = "a", required = false) int a,
            @RequestParam(name = "b", required = false) int b);

}
