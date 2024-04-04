package com.example.msconfigserve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class MsConfigServeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsConfigServeApplication.class, args);
	}

}
