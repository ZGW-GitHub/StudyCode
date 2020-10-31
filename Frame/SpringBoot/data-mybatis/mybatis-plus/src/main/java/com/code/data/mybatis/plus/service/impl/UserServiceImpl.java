package com.code.data.mybatis.plus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.code.data.mybatis.plus.entity.User;
import com.code.data.mybatis.plus.mapper.UserMapper;
import com.code.data.mybatis.plus.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author 愆凡
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
