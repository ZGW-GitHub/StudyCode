package com.code.spring.c.ioc.bean.instantiation.other.service.loader.factory;

import com.code.spring.c.ioc.bean.instantiation.other.service.loader.TestUser;

/**
 * @author 愆凡
 * @date 2021/2/1 21:32
 */
public class TestUserFactoryImplTwo implements TestUserFactory {

	@Override
	public TestUser getUser() {
		TestUser user = new TestUser();
		user.setId(2L);
		user.setName("愆凡2");
		return user;
	}

}
