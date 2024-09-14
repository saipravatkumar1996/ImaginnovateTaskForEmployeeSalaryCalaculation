package com.imaginnovate.emp.config;

import org.springframework.context.annotation.Configuration;

@Configuration

public class SwaggerConfig {
	
//	@Bean
//	public Docket createDocket() {
//		
//		return new Docket(DocumentationType.SWAGGER_2) 
//				.select()
//				
//				.apis(RequestHandlerSelectors.basePackage("com.cm.emp.controller")) 
//
//				.paths(PathSelectors.regex("/api.*")) 
//				
//				.build() 
//				
//				.apiInfo(apiInfo())
//				;
//	}
//	
//	@SuppressWarnings("rawtypes")
//	private ApiInfo apiInfo() {
//		
//		return new ApiInfo(
//				 "WEL COME TO IMAGINNOVATE",
//			      "This application develop a java-based application with REST APIs",
//			      "version 1.0",
//			      "https://imaginnovate.com/",
//			      new Contact("Sravani Gadepalli", "https://imaginnovate.com/", "sravani.gadepalli@imaginnovate.com") ,
//			      "IMAGINNOVATE license",
//			      "https://imaginnovate.com/",
//			      new ArrayList<VendorExtension>());
//			
//	}
}