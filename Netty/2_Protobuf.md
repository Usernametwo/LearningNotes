# ProtoBuf

## RMI
rmi(remote method invocation):远程方法调用，只针对Java

client:stub

server:skeleton

序列化与反序列化：也叫做编码与解码

## RPC 
RPC(remote procedure call):远程过程调用，很多RPC框架都是跨语言的

1. 定义一个接口说明文件(idl)：描述了接口对象（结构体），对象成员，接口方法等一系列信息
2. 通过RPC框架所提供的编译器，将接口文件编译成具体语言文件
3. 在客户端与服务器端分别引入RPC编译器所生成的文件，即可像调用本地方法一样调用远程方法

## WebService

## ProtoBuf