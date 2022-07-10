package com.grupo16.src;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SrcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SrcApplication.class, args);
	}

	@Bean
	public PasswordEncoder encoder(){
		return  new BCryptPasswordEncoder();
	}
}
