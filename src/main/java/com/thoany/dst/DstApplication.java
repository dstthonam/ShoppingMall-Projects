package com.thoany.dst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DstApplication {

	public static void main(String[] args) {
		SpringApplication.run(DstApplication.class, args);
	}

}
