package com.dragon.test.netty.service.nio0;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyServer0 {

    public void connect(int port) {
        Channel channel = new NioSocketChannel();
        //不会阻塞
        ChannelFuture future = channel.connect(new InetSocketAddress("127.0.0.1", port));
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {//表示链接成功
                    ByteBuf bf = Unpooled.copiedBuffer("Hello，你好", Charset.defaultCharset());
                    ChannelFuture wf = future.channel().writeAndFlush(bf);
                    wf.addListener(new ChannelFutureListener() {
                        @Override
                        public void operationComplete(ChannelFuture future) throws Exception {
                            if (future.isSuccess()) {
                                System.out.println("发送成功！");
                            } else {
                                System.out.println("发送失败！");
                            }
                            wf.channel().closeFuture().sync();
                        }
                    });
                } else {
                    Throwable cause = future.cause();
                    cause.printStackTrace();
                }
            }
        });

    }

}
