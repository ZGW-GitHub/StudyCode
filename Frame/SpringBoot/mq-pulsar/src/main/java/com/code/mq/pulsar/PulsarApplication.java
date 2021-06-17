package com.code.mq.pulsar;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author 愆凡
 * @date 2021/6/17 14:13
 */
@Slf4j
public class PulsarApplication {
	public static void main(String[] args) {

		new SpringApplicationBuilder(PulsarApplication.class).run(args);

	}
}
