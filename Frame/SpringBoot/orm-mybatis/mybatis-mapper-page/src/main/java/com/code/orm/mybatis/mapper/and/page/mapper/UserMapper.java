package com.code.orm.mybatis.mapper.and.page.mapper;

import com.code.orm.mybatis.mapper.and.page.entity.User;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author 愆凡
 * @date 2020/8/14 11:49 上午
 */
@Component
public interface UserMapper extends Mapper<User>, MySqlMapper<User> {

}
