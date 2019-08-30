package com.tinain.transport;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;

/**
 * @author Alan Lau
 */
public class NettyServer implements Server{

    private final ServerBootstrap serverBootstrap;

    private final EventLoopGroup bossGroup;

    private final EventLoopGroup workerGroup;

    private final Class<SocketChannel> channelType;

    public NettyServer(ServerBootstrap serverBootstrap, EventLoopGroup bossGroup,
        EventLoopGroup workerGroup, Class<SocketChannel> channelType) {
        this.serverBootstrap = serverBootstrap;
        this.bossGroup = bossGroup;
        this.workerGroup = workerGroup;
        this.channelType = channelType;
    }

    @Override public void start() {

    }

    @Override public void shutdown() {

    }
}


