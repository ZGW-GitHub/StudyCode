package com.code.data.redis.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author 愆凡
 * @date 2020/9/10 9:20 上午
 */
@SpringBootApplication
public class RedisClientApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(RedisClientApplication.class).run(args);
	}

}
