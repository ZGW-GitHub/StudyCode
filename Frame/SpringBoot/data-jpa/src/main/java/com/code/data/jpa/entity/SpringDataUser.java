package com.code.data.jpa.entity;

import com.code.data.jpa.entity.base.AbstractEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * @author 愆凡
 * @date 2020/7/31 2:07 下午
 */
@Data
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "spring_data_user")
public class SpringDataUser extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	public static final Integer SEX_MAN = 1;
	public static final Integer SEX_WOMAN = 2;

	private String name;
	private Integer sex;
	private Integer age;
	private String phone;
	@Column(name = "is_active")
	private Boolean isActive;

}
