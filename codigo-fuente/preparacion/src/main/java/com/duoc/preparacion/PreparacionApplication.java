package com.duoc.preparacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PreparacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(
				PreparacionApplication.class,
				args);
	}
}