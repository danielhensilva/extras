package com.bloom.challenge.mathsapi.clients;

import com.bloom.challenge.mathsapi.models.Mathematician;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class MathsServiceFallbackImpl implements MathsServiceClient {

    @Override
    public CompletableFuture<Integer> sum(int a, int b) {
        // Simulates a case where MathsService is not available
        // and the fallback just provides a generic result
        // instead of crashing
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<List<Mathematician>> get(int pageNumber, int pageSize) {
        return CompletableFuture.completedFuture(Collections.emptyList());
    }

}
