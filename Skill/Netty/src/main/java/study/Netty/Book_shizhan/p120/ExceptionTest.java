/*
      Date:  2019-11-21 21:00
                                 */
package study.Netty.Book_shizhan.p120;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.TooLongFrameException;
import org.junit.Assert;
import org.junit.Test;

public class ExceptionTest {

    @Test
    public void test() {

        ByteBuf buffer = Unpooled.buffer();

        for (int i = 0; i < 9; i++) {
            buffer.writeByte(1);
        }

        ByteBuf duplicate = buffer.duplicate();

        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new MyByteToMessageDecoder(3));
        Assert.assertTrue(embeddedChannel.writeInbound(duplicate.readBytes(2)));

        try {
            embeddedChannel.writeInbound(duplicate.readBytes(4));
            Assert.fail();
        } catch (TooLongFrameException e) {
            // do something
        }

        Assert.assertTrue(embeddedChannel.writeInbound(duplicate.readBytes(3)));
        Assert.assertTrue(embeddedChannel.finish());

        ByteBuf read = (ByteBuf) embeddedChannel.readInbound();
        Assert.assertEquals(buffer.readSlice(2), read);
        read.release();

        ByteBuf read2 = embeddedChannel.readInbound();
        Assert.assertEquals(buffer.skipBytes(4).readSlice(3), read2);
        read2.release();

        buffer.release();

    }

}
