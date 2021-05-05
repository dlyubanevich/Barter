package ru.dlyubanevich.nomenclatures;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NomenclaturesApplication {

	public static void main(String[] args) {
		SpringApplication.run(NomenclaturesApplication.class, args);
	}

}
