package com.code.spring.c.ioc.aa.bean.lifecycle;

import com.code.spring.a.basic.entity.User;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author 愆凡
 * @date 2021/6/22 22:56
 */
public class BeanUser extends User implements InitializingBean, DisposableBean {

	/**
	 * 依赖于注解驱动，当使用 BeanFactory 启动上下文时并不会回调该方法。<br />
	 * 解决：通过 beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor()) 解决。
	 */
	@PostConstruct
	public void initPostConstruct() {

	}

	@Override
	public void afterPropertiesSet() {

	}

	public void init() {

	}

	/**
	 * 同样依赖于注解驱动，当使用 BeanFactory 启动上下文时并不会回调该方法。<br />
	 * 解决：通过 beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor()) 解决。
	 */
	@PreDestroy
	public void destroyPreDestroy() {

	}

	@Override
	public void destroy() {

	}

	public void destroyDemo() {

	}

}
