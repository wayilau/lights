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

    private TinaMessage message;

    private Serializer serializer;


    @Override protected void encode(ChannelHandlerContext ctx, TinaMessage msg, ByteBuf out) throws Exception {
        if (msg == null) {
            return;
        }

        out.writeInt(msg.getMajor());
        out.writeShort(msg.getVersion());
        out.writeByte(msg.getType());
        out.writeShort(msg.getLength());
        out.writeBytes(serializer.serializer(msg.getObject()));
        out.writeInt(msg.getCsc());
    }

    @Override protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        if (message == null) {
            message = new TinaMessage();
        }

        if (!in.isReadable()) {
            return;
        }

        if (in.readableBytes() > 4 && message.getMajor() == 0) {
            message.setMajor(in.readInt());
        }

        if (in.readableBytes() > 2 && message.getVersion() == 0) {
            message.setVersion(in.readShort());
        }

        if (in.readableBytes() > 1 && message.getType() == 0) {
            message.setType(in.readByte());
        }

        if (in.readableBytes() > 2 && message.getLength() == 0) {
            message.setLength(in.readShort());
        }

        if (in.readableBytes() > message.getLength() && message.getObject() == null) {
            message.setObject(serializer.deserializer(in.readBytes(message.getLength()).array()));
        }

        if (in.readableBytes() > 4 && message.getCsc() == 0) {
            message.setCsc(in.readInt());
        }

        if (message.getCsc() != 0) {
            out.add(message);
            message = null;
        }

    }
}
