/*
      Date:  2019-11-21 20:00
                                 */
package study.Netty.Book_shizhan.p116;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author NotUpToYou
 */
public class InTest {

    @Test
    public void test1() {

        ByteBuf buffer = Unpooled.buffer(9);
        for (int i = 0; i < 9; i++) {
            buffer.writeByte(i);
        }

        ByteBuf copyBuf = buffer.duplicate();

        // 创建一个 EmbeddedChannel ，并将要测试的 ChannelHandler 作为参数传入
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new MyByteToMessageDecoder(3));

        // retain : 将引用计数加1
        // 将数据写入 EmbeddedChannel
        Assert.assertTrue(embeddedChannel.writeInbound(copyBuf.retain()));

        // 标记 EmbeddedChannel 为已完成状态
        Assert.assertTrue(embeddedChannel.finish());

        // 读取所生成的消息，并验证是否有3帧
        ByteBuf read = embeddedChannel.readInbound();
        Assert.assertEquals(buffer.readSlice(3), read);
        read.release();

        ByteBuf read2 = embeddedChannel.readInbound();
        Assert.assertEquals(buffer.readSlice(3), read2);
        read2.release();

        ByteBuf read3 = embeddedChannel.readInbound();
        Assert.assertEquals(buffer.readSlice(3), read3);
        read3.release();

        Assert.assertNull(embeddedChannel.readInbound());
        buffer.release();

    }

    @Test
    public void test2() {

        ByteBuf buffer = Unpooled.buffer(9);
        for (int i = 0; i < 9; i++) {
            buffer.writeByte(i);
        }

        ByteBuf copyBuf = buffer.duplicate();

        // 创建一个 EmbeddedChannel ，并将要测试的 ChannelHandler 作为参数传入
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new MyByteToMessageDecoder(3));

        // retain : 将引用计数加1
        // 将数据写入 EmbeddedChannel
        Assert.assertTrue(embeddedChannel.writeInbound(copyBuf.readBytes(2))); // 返回false，因为不够组成一个帧，无法读取到数据
        Assert.assertTrue(embeddedChannel.writeInbound(copyBuf.readBytes(7)));

        // 标记 EmbeddedChannel 为已完成状态
        Assert.assertTrue(embeddedChannel.finish());

        // 读取所生成的消息，并验证是否有3帧
        ByteBuf read = embeddedChannel.readInbound();
        Assert.assertEquals(buffer.readSlice(3), read);
        read.release();

        ByteBuf read2 = embeddedChannel.readInbound();
        Assert.assertEquals(buffer.readSlice(3), read2);
        read2.release();

        ByteBuf read3 = embeddedChannel.readInbound();
        Assert.assertEquals(buffer.readSlice(3), read3);
        read3.release();

        Assert.assertNull(embeddedChannel.readInbound());
        buffer.release();

    }

}
