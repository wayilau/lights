package com.tinain.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.tinain.protocol.v1.Entity;
import com.tinain.protocol.v1.TinaMessage;

/**
 * support kryo serializer
 *
 * @author Alan Lau
 */
public class KryoSerializer<Message> extends AbstractSerializer<Message> {

    private Kryo kryo = new Kryo();
    private Output output = new Output(1024);

    private Input input = new Input(1024);

    public KryoSerializer() {
        kryo.register(TinaMessage.class);
        kryo.register(Entity.class);
    }

    @Override

    public byte[] serializer(Message message) {
        kryo.writeObject(output, message);
        return output.getBuffer();
    }

    @Override public Message deserializer(byte[] bytes, Class<Message> clazz) {
        return kryo.readObject(input, clazz);
    }

    public void register(Class clazz) {
        kryo.register(clazz);
    }
}
