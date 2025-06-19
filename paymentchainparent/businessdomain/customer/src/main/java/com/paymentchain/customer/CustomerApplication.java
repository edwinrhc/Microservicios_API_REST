package com.paymentchain.customer;

import com.paymentchain.customer.config.GlobalConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties(GlobalConfig.class)
public class CustomerApplication {

	public static void main(String[] args) {      
		SpringApplication.run(CustomerApplication.class, args);
	}
     
}
