package com.bloom.challenge.mathsapi.clients;

import com.bloom.challenge.mathsapi.models.Mathematician;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@FeignClient(value = "Maths-Service", fallback = MathsServiceFallbackImpl.class)
public interface MathsServiceClient {

    @RequestMapping(method = RequestMethod.PUT, value = "/operations/sum", produces = "application/json")
    CompletableFuture<Integer> sum(
            @RequestParam(name = "a", required = true) int a,
            @RequestParam(name = "b", required = true) int b);

    @RequestMapping(method = RequestMethod.GET, value = "/mathematicians", produces = "application/json")
    CompletableFuture<List<Mathematician>> get(
            @RequestParam(name = "pageNumber", required = true) int pageNumber,
            @RequestParam(name = "pageSize", required = true) int pageSize);

}
