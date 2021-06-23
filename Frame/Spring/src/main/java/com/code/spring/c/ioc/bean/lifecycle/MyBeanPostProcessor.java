package com.code.spring.c.ioc.bean.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

/**
 * 自定义 BeanPostProcess
 *
 * @author 愆凡
 * @date 2021/6/22 23:02
 */
public class MyBeanPostProcessor implements InstantiationAwareBeanPostProcessor, DestructionAwareBeanPostProcessor {

	/**
	 * 实例化前会被调用
	 */
	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		if (ObjectUtils.nullSafeEquals("userOne", beanName)) {
			BeanUser user = new BeanUser();
			user.setName("愆凡V1");

			System.err.println(beanName + " --> postProcessBeforeInstantiation : 返回对象跳过剩余的实例化、初始化操作");

			return user; // 该对象将作为创建的 Bean对象 返回，而不再继续执行 Spring 提供的 Bean 的构建流程
		}
		return null; // 继续执行 Bean 的实例化、初始化操作
	}

	/**
	 * 实例化后会被调用
	 */
	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		if (ObjectUtils.nullSafeEquals("userOne", beanName)) {
			BeanUser user = (BeanUser) bean;
			user.setName("愆凡V2");

			System.err.println(beanName + " --> postProcessAfterInstantiation : user.name = " + user.getName());

			return false; // 返回 false 将不允许属性赋值（配置元信息 ——> 属性值）
		}
		return true;
	}

	/* ------------------------------------------ 华丽的分割线 ------------------------------------------ */

	/**
	 * 属性填充前会被调用
	 */
	@Override
	public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
		final MutablePropertyValues propertyValues;
		if (pvs instanceof MutablePropertyValues) {
			propertyValues = (MutablePropertyValues) pvs;
		} else {
			propertyValues = new MutablePropertyValues();
		}

		if (ObjectUtils.nullSafeEquals("userOne", beanName) || ObjectUtils.nullSafeEquals("userTwo", beanName)) {
			// 配置 name = 11
			propertyValues.add("id", "9");

			// 修改配置文件中的配置
			PropertyValue namePropertyValue = propertyValues.getPropertyValue("name");
			if (namePropertyValue != null) {
				// 因为 PropertyValue 的 value 属性为 final ，不能修改，所以这里只能先删除再添加
				propertyValues.removePropertyValue("name");
				propertyValues.add("name", "愆凡V3");
			}

			System.err.println(beanName + " --> postProcessProperties : user.name = " + propertyValues.getPropertyValue("name"));
		}

		return propertyValues;
	}

	/* ------------------------------------------ 华丽的分割线 ------------------------------------------ */

	/**
	 * 初始化前会被调用
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (ObjectUtils.nullSafeEquals("userOne", beanName) || ObjectUtils.nullSafeEquals("userTwo", beanName)) {
			BeanUser user = (BeanUser) bean;
			user.setName("愆凡V4");

			System.err.println(beanName + " --> postProcessBeforeInitialization : user.name = " + user.getName());

			return user;
		}
		return bean;
	}

	/**
	 * 初始化后会被调用
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (ObjectUtils.nullSafeEquals("userOne", beanName) || ObjectUtils.nullSafeEquals("userTwo", beanName)) {
			BeanUser user = (BeanUser) bean;
			user.setName("愆凡V5");

			System.err.println(beanName + " --> postProcessAfterInitialization : user.name = " + user.getName());

			return user;
		}
		return bean;
	}

	/* ------------------------------------------ 华丽的分割线 ------------------------------------------ */

	@Override
	public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
		if (ObjectUtils.nullSafeEquals("userFour", beanName)) {
			BeanUser user = (BeanUser) bean;
			user.setName("愆凡销毁前");

			System.err.println(beanName + " --> postProcessAfterInitialization : user.name = " + user.getName());
		}
	}
}
