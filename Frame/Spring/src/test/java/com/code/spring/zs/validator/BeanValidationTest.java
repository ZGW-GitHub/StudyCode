package com.code.spring.zs.validator;

import com.code.spring.MySpringApplicationTest;
import lombok.Data;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Bean Validation 与 Validator 适配，示例
 *
 * @author 愆凡
 * @date 2021/3/28 18:05
 */
public class BeanValidationTest extends MySpringApplicationTest {

	@Test
	public void test() {
		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/zs/validator/bean-validation-context.xml");

		applicationContext.refresh();

		UserProcessor userProcessor = applicationContext.getBean(UserProcessor.class);
		userProcessor.process(new User());
	}

	// TODO zgw 为啥必须为 static 才能扫描到
	@Component
	@Validated
	static class UserProcessor {
		public void process(@Valid User user) {
			System.err.println(user);
		}
	}

	@Data
	static class User {
		@NotNull
		private String name;
	}

}
