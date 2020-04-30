package com.dragon.test.netty.service.nio2;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //http服务关键handler，用于http消息的编解码
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        //HttpServerCodec内部封装了HttpRequestDecoder与HttpResponseEncoder

        //自定义的handler，添加自定义的ChannelHandler
        pipeline.addLast("testHttpServerHandler", new HttpServerHandler());
        //链式，所有请求都会经过上面的ChannelHandler处理
    }

}
