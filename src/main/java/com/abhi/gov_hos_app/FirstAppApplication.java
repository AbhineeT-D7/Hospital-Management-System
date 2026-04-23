package com.abhi.gov_hos_app;


import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies; 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication; 
import org.springframework.context.annotation.Bean; 

@SpringBootApplication
public class FirstAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstAppApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		modelMapper.getConfiguration()
				.setMatchingStrategy(MatchingStrategies.STRICT)
				.setFieldMatchingEnabled(true)
				.setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
				.setSkipNullEnabled(true);

		return modelMapper;
	}

}
