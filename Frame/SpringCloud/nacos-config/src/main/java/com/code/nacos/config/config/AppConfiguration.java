package com.code.nacos.config.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @author 愆凡
 * @date 2020/12/11 5:12 下午
 */
@Data
@Configuration
@RefreshScope
public class AppConfiguration {

	@Value("${test:123}")
	private Integer test;
	
}
