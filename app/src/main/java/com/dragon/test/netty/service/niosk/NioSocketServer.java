package com.dragon.test.netty.service.niosk;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @ClassName NioSocketTest
 * @Description TODO
 * @SysUser Administrator
 * @Author dragon
 * @Date 2020-04-30 16:33
 * @Version 1.0
 */
public class NioSocketServer {

    public void server(int port) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap sb = new ServerBootstrap();
        sb.group(group)
                .localAddress(new InetSocketAddress(port))
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast("encoder", new LivingEncoder());
                        ch.pipeline().addLast("decoder", new LivingDecoder());
                        ch.pipeline().addLast("myHandler", new LiveHandler());
                    }
                })
                /*.option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)*/;
        ChannelFuture channel = sb.bind().sync();
        channel.channel().closeFuture().sync();
    }

}
