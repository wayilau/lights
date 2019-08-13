package com.tinain.base;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @author Alan Lau
 */
public class ByteBufLearn {

    public static void main(String[] args) throws Exception {
        //写入数据到Buffer
        String path = Thread.currentThread().getContextClassLoader().getResource("data/nio-data.txt").getPath();
        RandomAccessFile aFile = new RandomAccessFile(path, "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuf buf = Unpooled.buffer();



    }
}
