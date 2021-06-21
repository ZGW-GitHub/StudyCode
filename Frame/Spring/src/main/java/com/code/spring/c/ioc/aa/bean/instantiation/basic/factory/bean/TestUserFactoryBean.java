package com.code.spring.c.ioc.aa.bean.instantiation.basic.factory.bean;

import com.code.spring.c.ioc.aa.bean.instantiation.TestUser;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author 愆凡
 * @date 2021/6/21 23:05
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
