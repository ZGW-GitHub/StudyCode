package com.code.spring.tx.service;

import com.code.spring.tx.entity.TxEntity;
import com.code.spring.tx.repository.TxDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 愆凡
 * @date 2020/7/30 4:16 下午
 */
@Service
@Transactional(readOnly = true)
public class TxService {

	private final TxDao txDao;

	public TxService(TxDao txDao) {
		this.txDao = txDao;
	}

	@Transactional(readOnly = false)
	public String add() {
		TxEntity newTx = new TxEntity(null, "test1");
		return add(newTx);
	}

	@Transactional(readOnly = false)
	public String add(TxEntity txEntity) {
//		try {
			return addException(txEntity);
//		} catch (Exception e) {
//			return "SUCCESS";
//		}
	}

	@Transactional(readOnly = false)
	public String addException(TxEntity txEntity) {
		txDao.save(txEntity);
		int e = 1/0;
		return "SUCCESS";
	}

}
