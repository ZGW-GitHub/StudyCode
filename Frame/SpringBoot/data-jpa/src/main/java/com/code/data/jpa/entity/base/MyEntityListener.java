package com.code.data.jpa.entity.base;

import javax.persistence.*;

/**
 * @author 愆凡
 * @date 2020/7/31 4:06 下午
 *
 * 被 @Prepersist 注解的方法，在 save 之前执行。
 * 被 @Preupdate 注解的方法，在 update 之前执行。
 * 被 @PreRemove 注解的方法，在 remove 之前执行。
 * 被 @Postpersist 注解的方法，在 save 之后执行。
 * 被 @Postupdate 注解的方法，在 update 之后执行。
 * 被 @PostRemovet 注解的方法，在 remove 之后执行。
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
