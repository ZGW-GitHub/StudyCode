package com.code.spring.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final Integer SEX_MAN = 1;
	public static final Integer SEX_WOMAN = 2;

	@Id
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
