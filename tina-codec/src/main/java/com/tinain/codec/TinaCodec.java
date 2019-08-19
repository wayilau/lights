package com.tinain.codec;

import com.tinain.serializer.Serializer;
import com.tinain.serializer.SerializerFactory;
import com.tinain.serializer.SerializerType;
import com.tinain.protocol.v1.Message;
import com.tinain.protocol.v1.TinaMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import java.util.List;

/**
 * @author Alan Lau
 */
public class TinaCodec extends AbstractCodec {

    @Override public byte[] encode(Object o) {
        return new byte[0];
    }

    @Override public Object decode(byte[] bytes, Class clazz) {
        return null;
    }


}
