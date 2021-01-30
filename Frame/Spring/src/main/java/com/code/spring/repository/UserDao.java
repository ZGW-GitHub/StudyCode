package com.code.spring.repository;

import com.code.spring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 愆凡
 * @date 2020/7/30 4:16 下午
 */
public interface UserDao extends JpaRepository<User, Integer> {
}
