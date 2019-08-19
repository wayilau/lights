package com.tinain;

import com.tinain.protocol.v1.Message;
import com.tinain.protocol.v1.TinaMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author Alan Lau
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        TinaMessage message = (TinaMessage) msg;
        System.out.println(message.getObject());

    }
}
