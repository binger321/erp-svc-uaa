package com.binger.uaa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.binger.uaa.dao")
public class ErpSvcUaaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErpSvcUaaApplication.class, args);
	}
}
