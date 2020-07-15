package com.code.study.Netty.Book_shizhan.p119;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

public class OutTest {

    @Test
    public void test() {

        ByteBuf buffer = Unpooled.buffer();
        for (int i = 0; i < 9; i++) {
            buffer.writeInt(i * (-1));
        }

        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new MyMessageToMessageEncoder());

        Assert.assertTrue(embeddedChannel.writeOutbound(buffer));

        Assert.assertTrue(embeddedChannel.finish());

        for (int i = 0; i < 9; i++) {
            Assert.assertEquals(Integer.valueOf(i), embeddedChannel.readOutbound());
        }

        Assert.assertNull(embeddedChannel.readOutbound());

    }

}
