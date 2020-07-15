package com.code.study.NIO;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author NotUpToYou
 */
public class A_Test {

    // 向文件中输入内容
    @Test
    public void test1() throws IOException {

        FileOutputStream fos = new FileOutputStream("D:\\_IT_\\Workspace\\JetBrains\\Java_IDEA_workspace\\B_Plus\\E_Netty\\src\\main\\resources\\test.txt");

        FileChannel channel = fos.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        buffer.put("你好！NIO 。".getBytes());

        buffer.flip();

        channel.write(buffer);

        fos.close();

    }

    // 从文件中读取内容
    @Test
    public void test2() throws IOException {

        FileInputStream fis = new FileInputStream("D:\\_IT_\\Workspace\\JetBrains\\Java_IDEA_workspace\\B_Plus\\E_Netty\\src\\main\\resources\\test.txt");

        FileChannel channel = fis.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate((int) new File("D:\\_IT_\\Workspace\\JetBrains\\Java_IDEA_workspace\\B_Plus\\E_Netty\\src\\main\\resources\\test.txt").length());

        channel.read(buffer);

        byte[] bytes = buffer.array();

        System.out.println(new String(bytes));

        fis.close();

    }

    // 实现文件复制
    @Test
    public void test03() throws IOException {

        FileInputStream fis = new FileInputStream("D:\\_IT_\\Workspace\\JetBrains\\Java_IDEA_workspace\\B_Plus\\E_Netty\\src\\main\\resources\\getFile\\男孩.jpg");
        FileOutputStream fos = new FileOutputStream("D:\\_IT_\\Workspace\\JetBrains\\Java_IDEA_workspace\\B_Plus\\E_Netty\\src\\main\\resources\\setFile\\男孩"+System.currentTimeMillis()+".jpg");

        FileChannel fisChannel = fis.getChannel();
        FileChannel fosChannel = fos.getChannel();

        fosChannel.transferFrom(fisChannel, 0, fisChannel.size());
//        fisChannel.transferTo(0, fisChannel.size(), fosChannel);

        fis.close();
        fos.close();

    }

}
