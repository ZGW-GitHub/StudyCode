package com.code.data.jpa.basic.repository;

import com.code.data.jpa.basic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author 愆凡
 * @date 2020/7/31 2:08 下午
 */
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = " SELECT sex FROM user WHERE name = ?1 LIMIT 1 ", nativeQuery = true)
	Integer findSexByName(String name);

}
