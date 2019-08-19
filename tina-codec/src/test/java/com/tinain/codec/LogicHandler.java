package com.tinain.codec;

import com.tinain.protocol.v1.Entity;
import com.tinain.protocol.v1.TinaMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author Alan Lau
 */
public class LogicHandler extends ChannelInboundHandlerAdapter {

    @Override public void channelActive(ChannelHandlerContext ctx) throws Exception {

        TinaMessage message = new TinaMessage();

        Entity e = new Entity();
        e.setId("aaa");
        e.setName("test");
        message.setObject(e);
        ctx.channel().writeAndFlush(message);
    }
}
