package com.oreilly.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.NumberFormat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private ApplicationContext ctx;

	@Autowired
	@Qualifier("defaultCurrencyFormat")
	private NumberFormat formatter;

	@Test
	public void defaultCurrency() {
		double amount = 123456.901234;
		System.out.println(formatter.format(amount));
	}

	@Test
	public void germanCurrency() {
		double amount = 123456.901234;
		NumberFormat deutschFormatter = ctx.getBean("germanCurrencyFormat", NumberFormat.class);
		System.out.println(deutschFormatter.format(amount));
	}

	@Test
	public void contextLoads() {
		int count = ctx.getBeanDefinitionCount();
		System.out.println(String.format("There are %d beans in the application context", count));

		for (String name : ctx.getBeanDefinitionNames()) {
			System.out.println(name);
		}
	}

}
