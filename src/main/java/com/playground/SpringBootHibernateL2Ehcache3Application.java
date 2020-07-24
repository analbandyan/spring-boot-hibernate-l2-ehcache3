package com.playground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SpringBootHibernateL2Ehcache3Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootHibernateL2Ehcache3Application.class, args);
	}

}
