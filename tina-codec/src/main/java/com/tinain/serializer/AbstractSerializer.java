package com.tinain.serializer;

/**
 * get default serializer
 *
 * @author Alan Lau
 */
public abstract class AbstractSerializer<T> implements Serializer<T> {

    @Override public abstract byte[] serializer(T message);

    @Override public abstract T deserializer(byte[] bytes, Class<T> clazz);
}
