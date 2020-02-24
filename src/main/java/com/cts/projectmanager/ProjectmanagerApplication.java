package com.cts.projectmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootApplication
public class ProjectmanagerApplication extends SpringBootServletInitializer{
//private static Logger logger = LoggerFactory.getLogger(ProjectmanagerApplication.class);
	
	public static void main(String[] args) {
		log.debug("PMA2");
		SpringApplication.run(ProjectmanagerApplication.class, args);
	}
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ProjectmanagerApplication.class);
		
	}

}
