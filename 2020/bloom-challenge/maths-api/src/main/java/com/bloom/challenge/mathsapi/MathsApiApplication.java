package com.bloom.challenge.mathsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.any;

@EnableSwagger2
@EnableFeignClients
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
public class MathsApiApplication {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Maths").select()
				.apis(RequestHandlerSelectors.basePackage("com.bloom.challenge.mathsapi"))
				.paths(any()).build().apiInfo(new ApiInfo(
						"Maths API", "Public-facing API for math calculations", "1.0.0", null,
						new Contact("Daniel H da Silva", "https://github.com/danielhensilva", null), null, null));
	}

	public static void main(String[] args) {
		SpringApplication.run(MathsApiApplication.class, args);
	}

}
