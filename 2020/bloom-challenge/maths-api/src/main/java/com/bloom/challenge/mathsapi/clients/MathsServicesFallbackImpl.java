package com.bloom.challenge.mathsapi.clients;

import java.util.concurrent.CompletableFuture;

public class MathsServicesFallbackImpl implements MathsServicesClient {

//    @Override
    public CompletableFuture<Integer> sum(int a, int b) {
        // Simulates a case where MathsService is not available
        // and the fallback just provides a generic result
        // instead of crashing
        return CompletableFuture.completedFuture(null);
    }

}
