package com.code.spring.tx;

import com.code.spring.tx.service.TxService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 愆凡
 * @date 2020/7/30 9:18 上午
 */
@RestController
@RequestMapping("/tx")
public class TxController {

	private final TxService txService;

	public TxController(TxService txService) {
		this.txService = txService;
	}

	@PostMapping("/add")
	public String add() {
		return txService.add();
	}

}
