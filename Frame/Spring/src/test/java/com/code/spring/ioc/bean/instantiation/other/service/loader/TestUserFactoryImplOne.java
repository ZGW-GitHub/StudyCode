package com.code.spring.ioc.bean.instantiation.other.service.loader;

/**
 * @author 愆凡
 * @date 2021/2/1 21:32
 */
public class TestUserFactoryImplOne implements TestUserFactory {

	@Override
	public TestUser getUser() {
		TestUser user = new TestUser();
		user.setId(1L);
		user.setName("愆凡1");
		return user;
	}

}
