package com.code.data.jpa.entity;

import com.code.data.jpa.entity.base.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author 愆凡
 * @date 2020/7/31 2:07 下午
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tx")
@Entity
public class Tx extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	private String name;

}
