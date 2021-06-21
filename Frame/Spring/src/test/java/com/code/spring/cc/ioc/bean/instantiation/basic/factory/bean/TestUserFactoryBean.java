package com.code.spring.cc.ioc.bean.instantiation.basic.factory.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author 愆凡
 * @date 2021/1/31 22:45
 */
public class TestUserFactoryBean implements FactoryBean<TestUser> {
	@Override
	public TestUser getObject() {
		TestUser user = new TestUser();
		user.setId(1L);
		user.setName("愆凡");
		return user;
	}

	@Override
	public Class<?> getObjectType() {
		return TestUser.class;
	}
}
