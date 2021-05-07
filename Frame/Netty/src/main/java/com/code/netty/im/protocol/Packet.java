package com.code.netty.im.protocol;

import lombok.Data;

/**
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
	public abstract Byte getCommand();

}
