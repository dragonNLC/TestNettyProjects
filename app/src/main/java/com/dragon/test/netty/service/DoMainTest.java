package com.dragon.test.netty.service;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class DoMainTest {

    public static void doTest() {
        //处理连接请求
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        //接收连接，然后处理连接发送过来的数据
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //加载initializer
            ServerBootstrap sbs = new ServerBootstrap();
            sbs.group(parentGroup, workerGroup)
                    //channel方法用于指定服务器监听套接字通道NioServerSocketChannel
                    //其内部管理了一个Java NIO中的ServerSocketChannel实例
                    //handler方法添加处理器，则是服务于parentGroup的
                    .channel(NioServerSocketChannel.class)
                    //这里的childHandler是服务于workerGroup的
                    .childHandler(new HttpServerInitializer());
            ChannelFuture channelFuture = sbs.bind(9990)//绑定到9990端口
                    .sync();//sync表示用于阻塞当前Thread，一直到端口绑定操作完成
            channelFuture.channel().closeFuture().sync();//该方法将会阻塞等待直到服务器的Channel关闭
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            parentGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
