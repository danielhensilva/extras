package com.bloom.challenge.mathsapi.clients;

import com.bloom.challenge.mathsapi.models.Mathematician;
import com.bloom.challenge.mathsapi.models.PagedMathematicians;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Primary
@FeignClient(value = "Maths-Service", fallback = MathsServiceFallbackImpl.class)
public interface MathsServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/operations/sum", produces = "application/json")
    CompletableFuture<Integer> sum(
            @RequestParam(name = "a") int a,
            @RequestParam(name = "b") int b);

    @RequestMapping(method = RequestMethod.GET, value = "/mathematicians", produces = "application/json")
    CompletableFuture<PagedMathematicians> getAllMathematicians(
            @RequestParam(name = "pageNumber") int pageNumber,
            @RequestParam(name = "pageSize") int pageSize);

}
