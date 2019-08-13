package com.tinain.base;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Alan Lau
 */
public class ByteBufferLearn {

    public static void main(String[] args) throws Exception {

        //写入数据到Buffer
        String path = Thread.currentThread().getContextClassLoader().getResource("data/nio-data.txt").getPath();
        RandomAccessFile aFile = new RandomAccessFile(path, "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buf);
        buf.flip();
        //length > remaining 时报BufferUnderflowException
        //在写模式下 limit就是容量， 读模式下是当前写了多少数据
        byte[] dst = new byte[buf.limit()];
        while (buf.hasRemaining()) {
            buf.get(dst);
        }
        System.out.println(new String(dst));
        buf.rewind();
        buf.get(dst);
        System.out.println(new String(dst));
        buf.clear();

        aFile.close();

    }

}
