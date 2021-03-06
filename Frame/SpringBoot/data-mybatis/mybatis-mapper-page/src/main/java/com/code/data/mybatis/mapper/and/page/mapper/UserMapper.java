package com.code.data.mybatis.mapper.and.page.mapper;

import com.code.data.mybatis.mapper.and.page.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author 愆凡
 * @date 2020/8/14 11:49 上午
 */
@Component
public interface UserMapper extends Mapper<User>, MySqlMapper<User> {

	@Delete(value = " DELETE FROM `user` WHERE 1 = 1 ")
	void deleteAll();

}
