package com.code.spring.zu.data.binding;

import com.code.spring.MySpringApplicationTest;
import org.junit.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.validation.DataBinder;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据绑定示例
 *
 * @author 愆凡
 * @date 2021/3/28 20:58
 */
public class DataBinderTest extends MySpringApplicationTest {

	@Test
	public void test() {
		DataBinderUser user = new DataBinderUser();

		// 1、创建 DataBinder
		DataBinder binder = new DataBinder(user, "");

		Map<String, Object> source = new HashMap<>();
		source.put("id", 1L);
		source.put("name", "愆凡");

		// 2、创建 PropertyValues
		PropertyValues propertyValues = new MutablePropertyValues(source);

		// 3、进行绑定
		binder.bind(propertyValues);

		System.err.println(user);

		// 4、获取绑定结果
		System.err.println(binder.getBindingResult());
	}

}
