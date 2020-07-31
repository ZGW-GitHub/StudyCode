package com.code.spring.tx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author 愆凡
 * @date 2020/7/30 4:23 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tx")
@Entity
public class TxEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

}
