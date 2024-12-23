package com.onchess.online_chess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OnlineChessApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineChessApplication.class, args);
	}

}
