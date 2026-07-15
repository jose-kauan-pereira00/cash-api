package com.invict.cash_api.cash_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.invict.cash_api.cash_api.config.property.CashMoneyApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(CashMoneyApiProperty.class)
public class CashApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CashApiApplication.class, args);
	}

}
