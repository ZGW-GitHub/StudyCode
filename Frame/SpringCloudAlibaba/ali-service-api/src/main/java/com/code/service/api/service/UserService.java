package com.code.service.api.service;

import com.code.service.api.entity.User;

/**
 * @author 愆凡
 * @date 2021/4/7 18:23
 */
public interface UserService {

	/**
	 * 根据 id 查询 User
	 * @param id userid
	 * @return {@link User}
	 */
	User findById(Long id);

}
