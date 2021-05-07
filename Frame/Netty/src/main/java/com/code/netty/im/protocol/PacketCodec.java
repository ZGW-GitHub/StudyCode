package com.code.netty.im.protocol;

import com.code.netty.im.protocol.command.CommandEnum;
import com.code.netty.im.serializer.Serializer;
import com.code.netty.im.serializer.SerializerAlgorithmEnum;
import io.netty.buffer.ByteBuf;
import org.assertj.core.annotations.NonNull;

/**
 * @author 愆凡
 * @date 2021/5/5 22:50
 */
public class PacketCodec {

	/**
	 * 编码
	 * @param packet 数据包
	 * @return 编码后的 ByteBuf
	 */
	public static void encode(ByteBuf byteBuf, Packet packet) {
		encode(byteBuf, packet, Serializer.DEFAULT);
	}

	public static void encode(ByteBuf byteBuf, Packet packet, @NonNull Serializer serializer) {
		// 1. 序列化 Java 对象
		byte[] bytes = serializer.serialize(packet);

		// 2. 实际编码过程
		byteBuf.writeInt(Packet.MAGIC_NUMBER);
		byteBuf.writeByte(packet.getVersion());
		byteBuf.writeByte(serializer.getSerializerAlgorithm());
		byteBuf.writeByte(packet.getCommand());
		byteBuf.writeInt(bytes.length);
		byteBuf.writeBytes(bytes);
	}

	/**
	 * 解码
	 *
	 * @param byteBuf ByteBuf
	 * @return 数据包
	 * @throws RuntimeException e
	 */
	public static Packet decode(ByteBuf byteBuf) throws RuntimeException {
		// 跳过 magic number
		byteBuf.skipBytes(4);
		// 跳过版本号
		byteBuf.skipBytes(1);

		// 序列化算法标识
		byte serializeAlgorithm = byteBuf.readByte();
		// 指令
		byte command = byteBuf.readByte();

		// 数据包长度
		int length = byteBuf.readInt();

		byte[] bytes = new byte[length];
		byteBuf.readBytes(bytes);

		// 根据指令获取对应的协议
		Class<? extends Packet> requestPacket = CommandEnum.getPacket(command);
		// 根据序列化算法标识获取对应的序列化算法
		Serializer serializer = SerializerAlgorithmEnum.getSerializer(serializeAlgorithm);

		if (requestPacket != null && serializer != null) {
			return serializer.deserialize(requestPacket, bytes);
		}
		return null;
	}

}
