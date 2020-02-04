# Netty的组件和设计

## Channel,EvenetLoop和ChannelFuture

1. Channel:Socket
    - OioServerSocketChannel:阻塞传输，实现方式为Socket调用超时抛出异常，捕获异常重复调用，代价比真正的阻塞传输高
    - NioServerSocketChannel:基于Java NIO(基于)的非阻塞传输，也即基于操作系统的select/poll调用
    - EpollServerSocketChannel:基于Netty为Linux提供的一组NIO API，基于Linux系统的epoll调用
2. EventLoop:控制流，多线程处理，并发
3. EventLoopGroup
    - OioEventLoopGroup:阻塞传输，实现方式为Socket调用超时抛出异常，捕获异常重复调用，代价比真正的阻塞传输高
    - NioEventLoopGroup:基于Java NIO(基于)的非阻塞传输，也即基于操作系统的select/poll调用
    - EpollEventLoopGroup:基于Netty为Linux提供的一组NIO API，基于Linux系统的epoll调用
4. ChannelFuture:异步通知

## ChannelHandler，ChannelPipeline

1. ChannelHandler:处理入站和出站数据的应用程序逻辑
    > 编码器和解码器也是ChannelHandler

2. ChannelPipeline:为ChannelHandler链提供容器
    > 使用拦截过滤器模式来调用ChannelHandler

## Bootstrap

1. Bootstrap
    > 客户端使用，只需要一组EventLoopGroup
2. ServerBootstrap
    > 服务器端使用，两组EvenetLoopGroup和Channel，一组用来处理监听套接字，另一组用来处理已经连接的套接字

## ByteBuf