package com.tinain.codec.serializer;

/**
 * @author Alan Lau
 */
public interface Serializer {

    /**
     * serializer object to byte.
     * @param message
     * @return
     */
    byte [] serializer(Object message);

    /**
     * deserializer byte to object
     * @param bytes
     * @return
     */
    Object deserializer(byte[] bytes);
}
