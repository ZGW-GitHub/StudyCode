package com.code.spring.zs.validator;

import com.code.spring.MySpringApplicationTest;
import com.code.spring.entity.User;
import org.junit.Test;
import org.springframework.context.MessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Locale;

/**
 * 自定义 Validator 示例
 *
 * @author 愆凡
 * @date 2021/3/28 17:30
 */
@SuppressWarnings("all")
public class ValidatorTest extends MySpringApplicationTest {

	@Test
	public void test() {
		// 1、创建 Validator
		ValidatorDemo validator = new ValidatorDemo();

		// 2、判断是否支持目标对象类型
		User user = ValidatorUser.builder().name("愆凡").build();
		System.err.println("对象是否被 ValidatorDemo 支持校验：" + validator.supports(user.getClass()));

		// 3、创建 Errors 对象
		Errors errors = new BeanPropertyBindingResult(user, "validatorUser");
		validator.validate(user, errors);

		// 4、获取 MessageResource 对象
		MessageSource messageSource = createMessageSource();

		// 5、输出所有错误文案
		errors.getAllErrors().forEach(error -> {
			String message = messageSource.getMessage(error.getCode(), error.getArguments(), Locale.getDefault());
			System.err.println(message);
		});
	}

	class ValidatorDemo implements Validator {

		@Override
		public boolean supports(Class<?> clazz) {
			return User.class.isAssignableFrom(clazz);
		}

		@Override
		public void validate(Object target, Errors errors) {
			if (target == null) {
				errors.reject("user.not.null"); // 校验对象，若为 Null 则生成 ObjectError
			}
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required"); // 校验id，若为空则生成 FieldError
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required"); // 校验id，若为空则生成 FieldError
		}
	}

	MessageSource createMessageSource() {
		StaticMessageSource messageSource = new StaticMessageSource();

		messageSource.addMessage("user.not.null", Locale.getDefault(), "User 不能为 Null");
		messageSource.addMessage("id.required", Locale.getDefault(), "the id of User must not be null");
		messageSource.addMessage("name.required", Locale.getDefault(), "the name of User must not be null");

		return messageSource;
	}

}
