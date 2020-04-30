package com.dragon.test.netty.service.niosk;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

/**
 * @ClassName LivingDecoder
 * @Description TODO
 * @SysUser Administrator
 * @Author dragon
 * @Date 2020-04-30 16:50
 * @Version 1.0
 */
public class LivingDecoder extends ReplayingDecoder<LivingDecoder.LiveState> {

    public enum LiveState {
        TYPE,
        LENGTH,
        CONTENT
    }

    private LiveMessage message;

    public LivingDecoder() {
        super(LiveState.TYPE);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        LiveState state = state();
        switch (state) {
            case TYPE:
                message = new LiveMessage();
                byte type = in.readByte();
                message.setType(type);
                checkpoint(LiveState.LENGTH);
                break;
            case LENGTH:
                int length = in.readInt();
                message.setLength(length);
                if (length > 0) {
                    checkpoint(LiveState.CONTENT);
                } else {
                    out.add(message);
                    checkpoint(LiveState.TYPE);
                }
                break;
            case CONTENT:
                byte[] bytes = new byte[message.getLength()];
                in.readBytes(bytes);
                String content = new String(bytes);
                message.setContent(content);
                out.add(message);
                checkpoint(LiveState.TYPE);
                break;
            default:
                throw new IllegalStateException("invalid state:" + state);
        }
    }

}
