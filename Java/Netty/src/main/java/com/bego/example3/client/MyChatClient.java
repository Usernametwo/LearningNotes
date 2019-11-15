package com.bego.example3.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Description: MyChatClient <br>
 * @Date: 2019/9/2 23:56 <br>
 * @Author: bego <br>
 * @Version: 1.0 <br>
 */
public class MyChatClient {
    public static void main(String[] args) throws IOException {
        EventLoopGroup eventExecutors = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventExecutors).channel(NioSocketChannel.class).handler(new MyChatClientInitializer());

        try {
            ChannelFuture channelFuture = bootstrap.connect("localhost", 8899).sync();
            Channel channel = channelFuture.channel();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                channel.writeAndFlush(bufferedReader.readLine() + "\r\n");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventExecutors.shutdownGracefully();
        }
    }
}
