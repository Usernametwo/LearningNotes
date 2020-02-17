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

- 读写索引分离
- 池化
- 引用计数

### 缓冲区的类型

- 堆缓冲区(ByteBuf)：数据存储在JVM堆上，处理数据比较方便，hasArray()方法会返回true，因为有支撑数组
- 直接缓冲区(ByteBuf)：通过本地调用来分配内存，避免了I/O操作时中间缓冲区的内容复制操作，缺点为当需要处理数据时，需要将数据复制到JVM堆上，hasArray()方法会返回false，无支撑数组
- 复合缓冲区(CompositeByteBuf)：多个缓冲区的组合，继承自ByteBuf

### ByteBufAllocator

可以通过Channel.alloc()或者ChannelHandlerContext.alloc()获取ByteBufAllocator的引用

- PooledByteBufAllocator：用来创建池化了ByteBuf的实例，减少内存碎片，默认使用
- UnpooledByteBufAllocator：创建非池化的ByteBuf实例
