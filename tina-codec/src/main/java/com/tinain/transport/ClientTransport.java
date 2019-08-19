package com.tinain.transport;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;

/**
 * @author Alan Lau
 */
public class ClientTransport {

    private Bootstrap b = new Bootstrap();

    private NioEventLoopGroup group;

    //write data
    private Channel channel;


}
