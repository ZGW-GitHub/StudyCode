package com.code.data.jpa.entity.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 愆凡
 * @date 2020/7/31 2:17 下午
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(MyEntityListener.class)
public abstract class AbstractEntity implements Serializable {
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = false, updatable = false)
	@CreatedDate
	private Date createTime;

	/**
	 * 上次更新时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_update_time", nullable = false)
	@LastModifiedDate
	private Date lastUpdateTime;
}
