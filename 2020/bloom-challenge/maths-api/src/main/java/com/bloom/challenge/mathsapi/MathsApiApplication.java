package com.bloom.challenge.mathsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@EnableSwagger2
//@EnableFeignClients
//@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
public class MathsApiApplication {

//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2).groupName("Maths").select()
//				.apis(RequestHandlerSelectors.basePackage("com.bloom.challenge.mathsapi"))
//				.paths(any()).build().apiInfo(new ApiInfo(
//						"Maths API", "Public-facing API for math calculations", "1.0.0", null,
//						new Contact("Daniel H da Silva", "https://github.com/danielhensilva", null), null, null));
//	}

	public static void main(String[] args) {
		SpringApplication.run(MathsApiApplication.class, args);
	}

}
