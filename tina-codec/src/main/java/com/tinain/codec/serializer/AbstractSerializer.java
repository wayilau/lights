package com.tinain.codec.serializer;

/**
 *
 * get default serializer
 * @author Alan Lau
 */
public class AbstractSerializer {

    private Serializer defaultSerializer;

    protected Serializer getSerializer() {
        //return kryo default
        return null;
    }
}
