package com.dragon.test.netty.service.nio0;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

@ChannelHandler.Sharable//该标志标识该类的实例可以在channel里面共享
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    //每个信息入站的时候都会被调用
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        System.out.println("Server received: " + in.toString(CharsetUtil.UTF_8));
        ctx.write(in);//将所接受的消息返回给发送者。注意，这里还没有冲刷数据
    }

    //通知处理器最后的channelRead是当前批处理器中最后一条消息
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
        //冲刷所有待审消息到远程节点，关闭通道后，操作完成。
    }

    //当出现读异常时被调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();//打印异常堆栈跟踪
        ctx.close();//关闭通道
    }

}
