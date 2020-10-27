package com.code.orm.jpa.basic.entity.base;

import javax.persistence.*;

/**
 * @author 愆凡
 * @date 2020/7/31 4:06 下午
 */
public class MyEntityListener {

	@PrePersist
	public void preSave(Object entity) {
		System.out.println("save 执行前！");
	}

	@PreUpdate
	public void preUpdate(Object entity) {
		System.out.println("update 执行前！");
	}

	@PreRemove
	public void preRemove(Object entity) {
		System.out.println("remove 执行前！");
	}

	@PostPersist
	public void postSave(Object entity) {
		System.out.println("save 执行后！");
	}

	@PostUpdate
	public void postUpdate(Object entity) {
		System.out.println("update 执行后！");
	}

	@PostRemove
	public void postRemove(Object entity) {
		System.out.println("remove 执行后！");
	}

}
