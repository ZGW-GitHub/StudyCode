package com.code.data.jpa.basic.repository;

import com.code.data.jpa.basic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 愆凡
 * @date 2020/7/31 2:08 下午
 */
public interface UserRepository extends JpaRepository<User, Long> {
}