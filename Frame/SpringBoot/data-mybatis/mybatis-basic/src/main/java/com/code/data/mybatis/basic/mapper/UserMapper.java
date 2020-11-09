package com.code.data.mybatis.basic.mapper;

import com.code.data.mybatis.basic.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 愆凡
 * @date 2020/8/14 11:49 上午
 */
@Mapper
@Component
public interface UserMapper {

	@Select(" SELECT * FROM user ")
	List<User> listUser();

	@Select(" SELECT * FROM user WHERE id = #{id} ")
	User selectUserById(@Param("id") Long id);

	// return : 1:成功，0：失败
	int saveUser(@Param("user") User user);

	void deleteAll();

}
