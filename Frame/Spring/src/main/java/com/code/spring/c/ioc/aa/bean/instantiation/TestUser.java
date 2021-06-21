package com.code.spring.c.ioc.aa.bean.instantiation;

import com.code.spring.a.basic.entity.User;

/**
 * @author 愆凡
 * @date 2021/6/21 22:45
 */
public class TestUser extends User {

	public TestUser(Long id, String name) {
		this.setId(id);
		this.setName(name);
	}

	public TestUser() {
	}

	public static TestUser getUser() {
		TestUser user = new TestUser();
		user.setId(1L);
		user.setName("愆凡");
		return user;
	}

}
