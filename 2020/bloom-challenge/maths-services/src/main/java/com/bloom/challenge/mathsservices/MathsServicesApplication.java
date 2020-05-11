package com.bloom.challenge.mathsservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MathsServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MathsServicesApplication.class, args);
    }

}
