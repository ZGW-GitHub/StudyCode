package com.code.data.tool.sharding.jdbc.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * @author 愆凡
 * @date 2021/3/9 16:20
 */
@Data
@Table(name = "user")
public class User {
	
	private long id;
	private String name;
	private int age;
	
}
