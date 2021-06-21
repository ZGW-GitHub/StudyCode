package com.code.spring.cc.ioc.bean.instantiation.basic.staticmethod;

import com.code.spring.a.basic.entity.User;

/**
 * @author 愆凡
 * @date 2021/1/31 22:08
 */
public class TestUser extends User {

	public static TestUser getUser() {
		TestUser user = new TestUser();
		user.setId(1L);
		user.setName("愆凡");
		return user;
	}

}
