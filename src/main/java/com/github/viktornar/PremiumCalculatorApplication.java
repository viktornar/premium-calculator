package com.github.viktornar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.github.viktornar.*" })
public class PremiumCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PremiumCalculatorApplication.class, args);
	}

}
