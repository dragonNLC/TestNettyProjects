package com.dragon.test.netty.service.nio0;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer1 {

    public void start(int port) throws InterruptedException {
        NioEventLoopGroup nlp = new NioEventLoopGroup();
        try {
            ServerBootstrap sb = new ServerBootstrap();
            sb.group(nlp)
                    .channel(NioServerSocketChannel.class)//指定使用NIO的传输Channel
                    .localAddress(new InetSocketAddress(port))//设置socket地址使用所选的端口
                    .childHandler(new ChannelInitializer<SocketChannel>() {//当一个新的连接被接收，一个新的子Channel将被创建，ChannelInitializer会添加我们的Handler实例到Channel的ChannelPipeline
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoServerHandler());//添加我们自己的处理链
                        }
                    });
            ChannelFuture future = sb.bind().sync();//绑定端口并等待绑定完成，返回一个ChannelFuture
            System.out.println(NettyServer1.class.getName() + " started and listen on " + future.channel().localAddress());
            future.channel().closeFuture().addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {

                }
            }).sync();//关闭channel和块，等待服务器关闭
        } finally {
            nlp.shutdownGracefully().sync();//关闭EventLoopGroup，释放所有资源
        }
    }
}
