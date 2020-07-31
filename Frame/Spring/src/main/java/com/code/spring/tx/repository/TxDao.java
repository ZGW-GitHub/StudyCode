package com.code.spring.tx.repository;

import com.code.spring.tx.entity.TxEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 愆凡
 * @date 2020/7/30 4:16 下午
 */
public interface TxDao extends JpaRepository<TxEntity, Integer> {
}
