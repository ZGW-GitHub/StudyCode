package com.code.spring.c.ioc.bean.instantiation.bean.factory;

import com.code.spring.c.ioc.bean.instantiation.TestUser;

/**
 * @author 愆凡
 * @date 2021/6/21 22:48
 */
public class UserFactory {

	public TestUser getUser() {
		TestUser user = new TestUser();
		user.setId(1L);
		user.setName("通过 BeanFactory 创建");
		return user;
	}

}
