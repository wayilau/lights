package com.tinain.client;

import com.tinain.Request;
import com.tinain.Response;

/**
 * @author Alan Lau
 */
public interface Client<T> {


    Response<T> newCall(Request<T> request);
}
