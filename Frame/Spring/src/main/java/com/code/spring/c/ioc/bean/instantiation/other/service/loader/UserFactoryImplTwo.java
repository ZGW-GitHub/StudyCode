package com.code.spring.c.ioc.bean.instantiation.other.service.loader;

import com.code.spring.c.ioc.bean.instantiation.TestUser;

/**
 * @author 愆凡
 * @date 2021/2/1 21:32
 */
public class UserFactoryImplTwo implements UserFactory {

	@Override
	public TestUser getUser() {
		TestUser user = new TestUser();
		user.setId(2L);
		user.setName("愆凡2");
		return user;
	}

}
