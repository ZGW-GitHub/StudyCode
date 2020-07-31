package com.code.data.jpa.repository;

import com.code.data.jpa.entity.Tx;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 愆凡
 * @date 2020/7/31 2:08 下午
 */
public interface TxDao extends JpaRepository<Tx, Long> {
}
