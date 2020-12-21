package com.code.spring.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 愆凡
 * @date 2020/8/14 11:49 上午
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

	public static final Integer SEX_MAN = 1;
	public static final Integer SEX_WOMAN = 2;
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Integer sex;
	private Integer age;
	private String phone;
	private String salt;
	private Boolean isActive;
	private Date createTime;
	private Date lastUpdateTime;

}
