package com.tinain.codec;

import com.tinain.protocol.v1.TinaMessage;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import jdk.management.resource.internal.inst.SocketChannelImplRMHooks;
import org.junit.Test;

/**
 * @author Alan Lau
 */
public class TestTinaCodec {

    @Test
    public void test() throws Exception {

        Bootstrap b = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        b.group(group);
        b.channel(NioSocketChannel.class);
        b.option(ChannelOption.SO_KEEPALIVE, true);
        b.handler(new ChannelInitializer<SocketChannel>() {
            @Override protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new TinaCodec());
                ch.pipeline().addLast(new LogicHandler());
            }
        });

        ChannelFuture f = b.connect("localhost", 4000).sync();

        f.channel().closeFuture().sync();
    }
}
