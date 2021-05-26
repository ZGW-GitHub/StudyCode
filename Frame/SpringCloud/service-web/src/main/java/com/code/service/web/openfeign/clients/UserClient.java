package com.code.service.web.openfeign.clients;

import com.code.service.common.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 愆凡
 * @date 2021/4/7 16:19
 */
@FeignClient("service-user")
public interface UserClient {

	/**
	 * 根据 id 查询 User
	 * @param id userid
	 * @return {@link User}
	 */
	@GetMapping("/user/{id}")
	User findById(@PathVariable("id") Long id);

}
