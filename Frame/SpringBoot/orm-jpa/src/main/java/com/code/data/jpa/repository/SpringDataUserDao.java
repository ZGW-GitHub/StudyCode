package com.code.data.jpa.repository;

import com.code.data.jpa.entity.SpringDataUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 愆凡
 * @date 2020/7/31 2:08 下午
 */
public interface SpringDataUserDao extends JpaRepository<SpringDataUser, Long> {
}
