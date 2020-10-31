package com.code.orm.mybatis.plus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

import static com.baomidou.mybatisplus.annotation.FieldFill.INSERT;
import static com.baomidou.mybatisplus.annotation.FieldFill.INSERT_UPDATE;

/**
 * @author 愆凡
 * @date 2020/8/14 11:49 上午
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("user")
public class User extends Model<User> implements Serializable {

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
	@TableField(fill = INSERT)
	private Date createTime;
	@TableField(fill = INSERT_UPDATE)
	private Date lastUpdateTime;

}
