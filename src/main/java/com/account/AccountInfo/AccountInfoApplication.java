package com.account.AccountInfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AccountInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountInfoApplication.class, args);
	}

}
