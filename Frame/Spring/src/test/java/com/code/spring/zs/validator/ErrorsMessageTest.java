package com.code.spring.zs.validator;

import com.code.spring.MySpringApplicationTest;
import com.code.spring.a.basic.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.context.MessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Locale;

/**
 * Errors 文案来源示例
 *
 * @author 愆凡
 * @date 2021/3/28 17:16
 */
public class ErrorsMessageTest extends MySpringApplicationTest {

	@Test
	public void test() {
		User user = new ValidatorUser();

		// 1、选择 Errors
		Errors errors = new BeanPropertyBindingResult(user, "user");

		// 2、调用 reject 或 rejectValue（ reject 生成 ObjectError 、rejectValue 生成 FieldError ）
		errors.reject("user.not.null");
		if (StringUtils.isEmpty(user.getName())) {
			// 若 name 属性为空，则调用 rejectValue 生成 FieldError
			errors.rejectValue("name", "name.required");
		}

		// 3、获取 Errors 中 ObjectError 、FieldError
		List<ObjectError> allErrors = errors.getAllErrors();

		// 4、通过 ObjectError 和 FieldError 中的 code 和 args 来关联 MessageResource
		MessageSource messageSource = createMessageSource();

		allErrors.forEach(error -> {
			String message = messageSource.getMessage(error.getCode(), error.getArguments(), Locale.getDefault());
			System.err.println(message);
		});
	}

	MessageSource createMessageSource() {
		StaticMessageSource messageSource = new StaticMessageSource();

		messageSource.addMessage("user.not.null", Locale.getDefault(), "User 不能为 Null");
		messageSource.addMessage("id.required", Locale.getDefault(), "the id of User must not be null");
		messageSource.addMessage("name.required", Locale.getDefault(), "the name of User must not be null");

		return messageSource;
	}

}
