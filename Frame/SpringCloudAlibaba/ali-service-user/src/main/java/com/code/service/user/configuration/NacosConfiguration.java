package com.code.service.user.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * 读取 Nacos 的配置
 *
 * @author 愆凡
 * @date 2021/4/8 21:14
 */
@Data
@RefreshScope
@Configuration
public class NacosConfiguration {

	@Value("${test.user.id:0}")
	private Long id;

	@Value("${test.user.name:init}")
	private String name;

}
