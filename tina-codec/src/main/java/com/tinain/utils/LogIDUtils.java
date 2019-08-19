package com.tinain.utils;

import com.tinain.LogID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Alan Lau
 */
public class LogIDUtils {

    private static AtomicInteger index = new AtomicInteger(0);

    public static LogID generateId() {
        String id = "ids_" + index.getAndIncrement();
        return new LogID(id);
    }
}
