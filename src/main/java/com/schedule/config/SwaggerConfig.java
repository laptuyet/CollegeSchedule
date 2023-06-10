package com.schedule.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Value("${controller.package.name}")
	private String controllerPackage;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				// message chung cho toàn api đối với từng mã code
				.select().apis(RequestHandlerSelectors.basePackage(controllerPackage)).paths(PathSelectors.regex("/.*"))
				.build().apiInfo(apiEndPointsInfo());
	}

	private ApiInfo apiEndPointsInfo() {
		return new ApiInfoBuilder().title("College Schedule REST api :)").description("Schedule")
				.contact(new Contact("Phi", "https://www.facebook.com/hoangphi.2104", "tranhoangphi0987@gmail.com"))
				.license("Apache 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html").version("1.0.0")
				.build();
	}
}
