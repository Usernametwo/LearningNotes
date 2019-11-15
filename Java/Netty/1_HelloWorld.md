# Hello World

## Netty开发步骤

1. 在main方法中通过ServerBootStrap类启动一个Server，或者通过BootStrap类启动一个Client
2. 定义一个ChannelInitializer，在initChannel方法里面获取pipline，并向其中添加Handler
3. 编写自定义的Handler，通过重写一些回调方法来实现具体的业务逻辑

## WebSocket

1. Http协议：无状态(Session,Cookie)，基于请求与响应(浏览器发送请求，建立连接，发送请求数据，返回响应数据，[Http:1.0 连接断开, Http1.1:Keep-Alive,连接保持几秒，随后关闭])
    > Http协议的问题：服务器端无法主动向客户端推送数据(需要在客户端使用轮询的方式来获取数据，但是消耗资源)
2. WebSocket真正实现浏览器与服务器之间的长连接
    > WebSocket是Http的一种升级协议，服务器端如果解析到请求头中包含WebSocket的信息，则将该连接升级为WebSocket协议的长连接

    > WebSocket在连接建立之后，如果对方非正常关闭，是无法感知到连接已经断开的，只有在发送数据的时候抛出异常才会感知到连接已经关闭(可以通过互相发送心跳包来探活)