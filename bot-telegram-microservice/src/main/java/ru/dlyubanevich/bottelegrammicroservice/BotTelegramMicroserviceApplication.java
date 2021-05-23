package ru.dlyubanevich.bottelegrammicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "ru.dlyubanevich.bottelegrammicroservice.feign")
@EnableDiscoveryClient
public class BotTelegramMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BotTelegramMicroserviceApplication.class, args);
	}

}
