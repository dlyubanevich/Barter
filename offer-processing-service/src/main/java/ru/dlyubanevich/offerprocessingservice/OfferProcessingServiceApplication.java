package ru.dlyubanevich.offerprocessingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "ru.dlyubanevich.offerprocessingservice.feign")
public class OfferProcessingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OfferProcessingServiceApplication.class, args);
	}

}
