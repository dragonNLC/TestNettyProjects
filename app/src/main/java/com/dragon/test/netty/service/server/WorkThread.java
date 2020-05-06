package com.dragon.test.netty.service.server;


import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class WorkThread extends Thread {

    private int port = 6601;

    @Override
    public void run() {
        super.run();
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);//监听线程只有一个
        NioEventLoopGroup childGroup = new NioEventLoopGroup();//处理数据的组
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, childGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            System.out.println("新客户端链接：" + ch.remoteAddress().getHostName());
                            ch.pipeline().addLast(new MealHandler());
                        }
                    });
                ChannelFuture future = serverBootstrap.bind(port).sync();
                future.channel().closeFuture().sync().addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture future) throws Exception {
                        System.out.println("关闭完成！");
                    }
                });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                bossGroup.shutdownGracefully().sync();
                childGroup.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
