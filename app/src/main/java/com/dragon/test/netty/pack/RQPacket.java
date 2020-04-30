package com.dragon.test.netty.pack;

import io.netty.buffer.ByteBuf;

/**
 * @ClassName RQPacket
 * @Description TODO
 * @SysUser Administrator
 * @Author dragon
 * @Date 2020-04-30 11:03
 * @Version 1.0
 *
 * sid是会话标识，每一个连接上来的请求都会有一个sid
 * sn是数据包标识，用于业务上消息丢失，重复过滤处理
 *
 */
public class RQPacket {

    public static final short PACKET_HEAD = 0xFA;
    public static final short PACKET_END = 0xFA;

    /**序号，用于包ID，解决幂问题**/
    private long sn;
    /**session id， 会话id，一个会话可以发送很多个包**/
    private long sid;
    /**控制码**/
    private short ctlCode;
    /**内容**/
    private byte[] content;

    public void write2ByteBuf(ByteBuf byteBuf) {
        final int packageLen = size();
        byteBuf.writeShort(PACKET_HEAD);
        byteBuf.writeInt(packageLen);
        writeBytes(byteBuf);
        byteBuf.writeByte(PACKET_END);
    }

    public int writeBytes(ByteBuf byteBuf) {
        byteBuf.writeLong(sn);
        byteBuf.writeShort(ctlCode);
        byteBuf.writeBytes(content);
        byteBuf.writeLong(sid);
        return size();
    }

    public int size() {
        return content != null ? content.length : 0;
    }

}
