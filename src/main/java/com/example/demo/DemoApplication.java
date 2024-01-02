package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@SpringBootApplication
public class DemoApplication {
	// 10.10.10.11 asd 가상머신, DB는 siu
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
