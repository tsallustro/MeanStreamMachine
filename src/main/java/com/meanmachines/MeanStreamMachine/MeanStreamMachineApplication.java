package com.meanmachines.MeanStreamMachine;

import com.meanmachines.MeanStreamMachine.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MeanStreamMachineApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeanStreamMachineApplication.class, args);

	}
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.init();
		};
	}
}
