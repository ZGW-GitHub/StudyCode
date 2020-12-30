package com.code.spring.core.bean.lifecycle;

import com.code.spring.entity.User;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * @author 愆凡
 * @date 2020/12/30 20:47
 */
public class UserBeanLifecycle extends User implements InitializingBean {

	/**
	 * 依赖于注解驱动，当使用 BeanFactory 启动上下文时并不会回调该方法。<br />
	 * 解决：通过 beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor()) 解决。
	 */
	@PostConstruct
	public void initPostConstruct() {
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {

	}

	public void init() {

	}

}
