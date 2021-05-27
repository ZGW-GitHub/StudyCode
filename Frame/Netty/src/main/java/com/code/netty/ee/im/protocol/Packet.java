package com.code.netty.ee.im.protocol;

import lombok.Data;

/**
 * 协议：魔数(4)、版本(1)、序列算法(1)、指令类型(1)、数据长度(4)、数据
 *
 * @author 愆凡
 * @date 2021/5/5 22:05
 */
@Data
public abstract class Packet {

	/**
	 * 协议魔数
	 */
	public static final int MAGIC_NUMBER = 0x12345678;

	/**
	 * 协议版本
	 */
	private Byte version = 1;

	/**
	 * 指令类型
	 *
	 * @return byte
	 */
	public abstract Byte getType();

}
