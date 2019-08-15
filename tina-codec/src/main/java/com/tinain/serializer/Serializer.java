package com.tinain.serializer;

/**
 * @author Alan Lau
 */
public interface Serializer<T> {

    /**
     * serializer object to byte.
     * @param message
     * @return
     */
    byte [] serializer(T message);

    /**
     * deserializer byte to object
     * @param bytes
     * @return
     */
    T deserializer(byte[] bytes, Class<T> clazz);
}
