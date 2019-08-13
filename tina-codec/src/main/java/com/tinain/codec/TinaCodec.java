package com.tinain.codec;

import com.tinain.codec.serializer.Serializer;
import com.tinain.protocol.v1.Message;
import com.tinain.protocol.v1.TinaMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import java.util.List;

/**
 * @author Alan Lau
 */
public class TinaCodec extends ByteToMessageCodec<TinaMessage> {

    private Serializer serializer;

    @Override protected void encode(ChannelHandlerContext ctx, TinaMessage msg, ByteBuf out) throws Exception {

    }

    @Override protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        if (!in.isReadable()) {
            return;
        }else {
            int major = in.readInt();
            byte version = in.readByte();
            byte type = in.readByte();
            short length = in.readShort();

            byte[] bytes = new byte[]{};
            in.readBytes(bytes, 5, in.capacity() - 4);

            Object obj = serializer.deserializer(bytes);

            int crc = in.readInt();

            TinaMessage tm = new TinaMessage();

        }
    }
}
