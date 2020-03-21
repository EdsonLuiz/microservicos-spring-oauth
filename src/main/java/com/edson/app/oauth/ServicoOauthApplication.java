package com.edson.app.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ServicoOauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicoOauthApplication.class, args);
	}

}
