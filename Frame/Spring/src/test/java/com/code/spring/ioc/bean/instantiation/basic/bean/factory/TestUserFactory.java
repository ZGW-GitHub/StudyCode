package com.code.spring.ioc.bean.instantiation.basic.bean.factory;

/**
 * @author 愆凡
 * @date 2021/1/31 22:30
 */
public class TestUserFactory {

	public TestUser getUser() {
		TestUser user = new TestUser();
		user.setId(1L);
		user.setName("愆凡");
		return user;
	}

}
