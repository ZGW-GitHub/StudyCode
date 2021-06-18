package com.code.spring.c.ioc.bean.instantiation.basic.constructor;

import com.code.spring.a.basic.entity.User;

/**
 * @author 愆凡
 * @date 2021/1/31 22:08
 */
public class TestUser extends User {

	public TestUser(Long id, String name) {
		this.setId(id);
		this.setName(name);
	}

	public TestUser() {
	}

}
