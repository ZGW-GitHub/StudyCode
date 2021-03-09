package com.code.data.tool.sharding.jdbc.repository;

import com.code.data.tool.sharding.jdbc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 愆凡
 * @date 2021/3/9 16:26
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
