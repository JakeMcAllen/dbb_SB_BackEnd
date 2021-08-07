package com.java.main.dbb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "dto, entity, repository, RestController, services")
@EntityScan("entity")
@EnableJpaRepositories("repository")
// @EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableJpaAuditing
public class DeBlogBrotherSbApplication {

	public static void main(String[] args) { SpringApplication.run(DeBlogBrotherSbApplication.class, args); }

	
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return configureApplication(builder);
//	}
//
//	private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
//		return builder.sources(Application.class).bannerMode(Banner.Mode.OFF);
//	}
	
}