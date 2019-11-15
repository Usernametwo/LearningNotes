package com.bego.example3.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Description: MyChatClientHandler <br>
 * @Date: 2019/9/2 23:58 <br>
 * @Author: bego <br>
 * @Version: 1.0 <br>
 */
public class MyChatClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);
    }
}
