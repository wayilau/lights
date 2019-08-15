package com.tinain.serializer;

/**
 * @author Alan Lau
 */
public class SerializerFactory {

    private Serializer serializer;

    private Serializer defaultSerializer = new KryoSerializer();

    private static volatile SerializerFactory factory;

    private SerializerFactory() {

    }

    public static SerializerFactory newIntance() {
        if (factory == null) {
            synchronized (SerializerFactory.class) {
                if (factory == null) {
                    factory = new SerializerFactory();
                }
            }
        }

        return factory;
    }

    public Serializer getSerializer(SerializerType type) {
        switch (type) {
            case KRYO:
                serializer = new KryoSerializer();
                break;
            default:
                return defaultSerializer;
        }
        return serializer;
    }

}
