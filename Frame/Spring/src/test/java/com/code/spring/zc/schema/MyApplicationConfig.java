package com.code.spring.zc.schema;

import lombok.Data;
import lombok.ToString;

/**
 * @author 愆凡
 * @date 2021/2/3 10:46
 */
@Data
@ToString
public class MyApplicationConfig {

	private String appName;
	private String address;
	private Integer port;

}
