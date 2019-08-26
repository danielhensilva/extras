package com.oreilly.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.NumberFormat;
import java.util.Locale;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public NumberFormat defaultCurrencyFormat() {
		return NumberFormat.getCurrencyInstance();
	}

	@Bean
	public NumberFormat germanCurrencyFormat() {
		return NumberFormat.getCurrencyInstance(Locale.GERMANY);
	}

}
