package com.tinain.transport;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * @author Alan Lau
 */
public class NettyServerBuilder {

    private NioEventLoopGroup bossGroup;

    private NioEventLoopGroup workerGroup;

    private Channel channel;

    private Class<SocketChannel> channelType;

    private SocketAddress address;

    private ServerBootstrap serverBootstrap;

    public NettyServerBuilder forAddress(SocketAddress address) {
        this.address = address;
        return this;
    }

    public NettyServerBuilder port(int port) {
        this.address = new InetSocketAddress(port);
        return this;
    }

    public NettyServerBuilder channelType(Class<SocketChannel> channelType) {
        this.channelType = channelType;
        return this;
    }


    public Server buildTransportServer() {
        return new NettyServer(serverBootstrap, bossGroup, workerGroup, channelType);
    }
}
