package com.tinain.codec;

import com.tinain.protocol.v1.Message;
import com.tinain.protocol.v1.TinaMessage;
import com.tinain.serializer.Serializer;
import com.tinain.serializer.SerializerFactory;
import com.tinain.serializer.SerializerType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import java.util.List;

/**
 * @author Alan Lau
 */
public abstract class AbstractCodec implements MessageCodec {

    public static class InternalCodec extends ByteToMessageCodec<Message> {

        private TinaMessage message;

        private Serializer serializer;

        @Override protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
            if (msg == null) {
                return;
            }

            TinaMessage tinaMessage = (TinaMessage)msg;

            out.writeInt(tinaMessage.getMajor());
            out.writeShort(tinaMessage.getVersion());
            out.writeByte(tinaMessage.getType());
            if (serializer == null) {
                serializer = SerializerFactory.newIntance().getSerializer(SerializerType.KRYO);
            }
            byte[] bytes = serializer.serializer(tinaMessage.getObject());
            int length = bytes.length;
            out.writeInt(length);
            out.writeBytes(bytes);
            out.writeInt(tinaMessage.getCrc());
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

            if (in.readableBytes() > 4 && message.getObject() == null) {
                message.setObject(serializer.deserializer(in.readBytes(in.readInt()).array(), Object.class));
            }

            if (in.readableBytes() > 4 && message.getCrc() == 0) {
                message.setCrc(in.readInt());
            }

            if (message.getCrc() != 0) {
                out.add(message);
                message = null;
            }

        }
    }

    @Override public abstract byte[] encode(Object o);

    @Override public abstract Object decode(byte[] bytes, Class clazz);
}
