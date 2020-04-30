package com.dragon.test.netty.service.niosk;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.ScheduledFuture;

/**
 * @ClassName LiveHandler
 * @Description TODO
 * @SysUser Administrator
 * @Author dragon
 * @Date 2020-04-30 16:18
 * @Version 1.0
 */
public class LiveHandler extends SimpleChannelInboundHandler<LiveMessage> {

    private static Map<Integer, LiveChannelCache> channelCacheMap = new HashMap<>();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LiveMessage msg) throws Exception {
        Channel channel = ctx.channel();
        final int hashCode = channel.hashCode();
        if (!channelCacheMap.containsKey(hashCode)) {
            channel.closeFuture().addListener(future -> {
                channelCacheMap.remove(hashCode);
            });
            ScheduledFuture scheduledFuture = ctx.executor().
                    schedule((Runnable) channel::close, 10, TimeUnit.SECONDS);
            channelCacheMap.put(hashCode, new LiveChannelCache(channel, scheduledFuture));
        }
        switch (msg.getType()) {
            case LiveMessage.TYPE_HEART:
                LiveChannelCache cache = channelCacheMap.get(hashCode);
                ScheduledFuture scheduledFuture = ctx.executor().schedule((Runnable) channel::closeFuture,
                        5, TimeUnit.SECONDS);
                if (cache != null) {
                    cache.getScheduledFuture().cancel(true);
                    cache.setScheduledFuture(scheduledFuture);
                }
                ctx.channel().writeAndFlush(msg);
                System.out.println(msg.getType());
                break;
            case LiveMessage.TYPE_MESSAGE://接受到数据，并返回回去
                Set<Integer> keys = channelCacheMap.keySet();
                for (Integer key :
                        keys) {
                    LiveChannelCache lcc = channelCacheMap.get(key);
                    if (lcc != null) {
                        Channel otherChannel = lcc.getChannel();
                        otherChannel.writeAndFlush(msg);
                        System.out.println(msg.getContent());
                    }
                }
                break;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        if (null != cause) {
            cause.printStackTrace();
        }
        ctx.close();
    }

}
