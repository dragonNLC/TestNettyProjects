package com.dragon.test.netty.service.nio1;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;

/**
 * @ClassName NettyOioServer
 * @Description TODO
 * @SysUser Administrator
 * @Author dragon
 * @Date 2020-04-30 14:24
 * @Version 1.0
 */
public class NettyOioServer {

    public void server(int port) throws Exception {
        final ByteBuf btf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Hi ...", Charset.forName("UTF-8")));
        //创建一个io缓冲区
        EventLoopGroup group = new OioEventLoopGroup();
        //事件循环组，并实例化为OIO模型，即每个channel对应一个thread，阻塞模式，类似旧的I/O

        try {
            ServerBootstrap sbp = new ServerBootstrap();
            //服务引导程序
            sbp.group(group)
                    .channel(OioServerSocketChannel.class)//channel类型
                    .localAddress(new InetSocketAddress(port))//绑定本地地址
                    //指定ChannelInitializer,对于每个已接受的连接都调用它
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new ChannelInboundHandlerAdapter() {//添加一个ChannelInboundHandlerAdapter以拦截和处理事件
                                        @Override
                                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                            //将消息写入到客户端，并添加ChannelFutureListener，以便消息一被写完就关闭连接
                                            ctx.writeAndFlush(btf.duplicate())
                                                    .addListener(ChannelFutureListener.CLOSE);
                                        }
                                    });
                        }
                    });
            ChannelFuture f = sbp.bind().sync();//开始绑定本地端口，并执行同步操作（绑定服务器以便接受连接）
            f.channel().closeFuture().sync();//等待绑定的流关闭操作
        } finally {
            //释放所有资源
            group.shutdownGracefully().sync();
        }
    }

}
