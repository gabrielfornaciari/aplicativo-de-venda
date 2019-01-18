package br.com.fornaciari.appVenda.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "br.com.fornaciari.appVenda.model")
public class VendaApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendaApplication.class, args);
	}

}

