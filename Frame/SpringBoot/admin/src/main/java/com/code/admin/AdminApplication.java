package com.code.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author 愆凡
 * @date 2021/5/17 10:06
 */
@EnableAdminServer
@SpringBootApplication
public class AdminApplication {

	public static void main(String[] args) throws InterruptedException {
		new SpringApplicationBuilder(AdminApplication.class).run(args);
		
		Thread.currentThread().join();
	}
	
}
