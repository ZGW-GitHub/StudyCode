package com.code.netty;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author 愆凡
 * @date 2021/5/12 10:42
 */
@SpringBootApplication
public class NettyApplication {

	public static void main(String[] args) {

		new SpringApplicationBuilder(NettyApplication.class).run(args);

	}

}
