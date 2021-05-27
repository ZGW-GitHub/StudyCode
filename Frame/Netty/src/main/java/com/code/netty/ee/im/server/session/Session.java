package com.code.netty.ee.im.server.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 愆凡
 * @date 2021/5/8 14:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {

	private String userid;
	private String userName;

}
