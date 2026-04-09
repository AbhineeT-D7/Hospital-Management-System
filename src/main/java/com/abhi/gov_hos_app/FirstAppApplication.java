package com.abhi.gov_hos_app;


import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies; 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication; 
import org.springframework.context.annotation.Bean; 

@SpringBootApplication
public class FirstAppApplication {

	
	//private static final Logger LOGGER=LoggerFactory.getLogger(FirstAppApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(FirstAppApplication.class, args);
        //LOGGER.info("--Simple log statement is running with inputs now");
	}

//	@Bean
//	public ModelMapper getModelMapper() {
//		ModelMapper modelmapper =new ModelMapper();
//		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//		return modelmapper;
//	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		// Set matching strategy (STANDARD is default, STRICT is safer)
		modelMapper.getConfiguration()
				.setMatchingStrategy(MatchingStrategies.STRICT)
				.setFieldMatchingEnabled(true)
				.setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
				.setSkipNullEnabled(true);

		return modelMapper;
	}

}
