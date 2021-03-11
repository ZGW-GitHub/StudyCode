package com.code.data.redis.cache.repository;

import com.code.data.redis.cache.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 愆凡
 * @date 2021/3/11 14:57
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
