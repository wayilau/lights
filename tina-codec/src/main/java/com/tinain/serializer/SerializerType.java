package com.tinain.serializer;

import lombok.Getter;

public enum SerializerType {

    KRYO(0),

    HESSIAN(1);

    @Getter
    private int type;

    SerializerType(int type) {
        this.type = type;
    }
}
