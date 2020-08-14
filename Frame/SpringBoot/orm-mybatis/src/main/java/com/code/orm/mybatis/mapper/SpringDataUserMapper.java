package com.code.orm.mybatis.mapper;

import com.code.orm.mybatis.entity.SpringDataUser;
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
public interface SpringDataUserMapper {

	@Select("SELECT * FROM spring_data_user")
	List<SpringDataUser> listSpringDataUser();

	@Select("SELECT * FROM spring_data_user WHERE id = #{id}")
	SpringDataUser selectUserById(@Param("id") Long id);

	// return : 1:成功，0：失败
	int saveSpringDataUser(@Param("user") SpringDataUser user);

}
