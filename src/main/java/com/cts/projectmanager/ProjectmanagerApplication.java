package com.cts.projectmanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootApplication
public class ProjectmanagerApplication {
//private static Logger logger = LoggerFactory.getLogger(ProjectmanagerApplication.class);
	
	public static void main(String[] args) {
		log.debug("PMA2");
		SpringApplication.run(ProjectmanagerApplication.class, args);
	}

}
