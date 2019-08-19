package com.tinain.codec;

public interface MessageCodec<T> extends Codec {

    byte[] encode(T t) throws CodecException;

    T decode(byte[] bytes, Class<T> clazz) throws CodecException;
}
