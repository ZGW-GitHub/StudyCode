package com.code.data.tool.sharding.jdbc.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author 愆凡
 * @date 2021/3/9 16:20
 */
@Data
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private int age;
	
}
