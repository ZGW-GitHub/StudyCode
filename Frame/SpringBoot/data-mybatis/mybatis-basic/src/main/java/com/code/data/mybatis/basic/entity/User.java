package com.code.data.mybatis.basic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 愆凡
 * @date 2020/8/14 11:49 上午
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final Integer SEX_MAN = 1;
	public static final Integer SEX_WOMAN = 2;

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
