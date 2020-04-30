package com.dragon.test.netty.service.niosk;

import io.netty.channel.Channel;
import io.netty.util.concurrent.ScheduledFuture;

/**
 * @ClassName LiveChannelCache
 * @Description TODO
 * @SysUser Administrator
 * @Author dragon
 * @Date 2020-04-30 16:16
 * @Version 1.0
 */
public class LiveChannelCache {

    private Channel channel;
    private ScheduledFuture scheduledFuture;

    public LiveChannelCache() {
    }

    public LiveChannelCache(Channel channel, ScheduledFuture scheduledFuture) {
        this.channel = channel;
        this.scheduledFuture = scheduledFuture;
    }

    public ScheduledFuture getScheduledFuture() {
        return scheduledFuture;
    }

    public void setScheduledFuture(ScheduledFuture scheduledFuture) {
        this.scheduledFuture = scheduledFuture;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
