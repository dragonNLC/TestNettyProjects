package com.dragon.test.netty;

import com.dragon.test.netty.service.nio0.NettyClient1;
import com.dragon.test.netty.service.nio0.NettyServer0;
import com.dragon.test.netty.service.nio0.NettyServer1;
import com.dragon.test.netty.service.nio1.NettyNioServer;
import com.dragon.test.netty.service.nio1.NettyOioServer;
import com.dragon.test.netty.service.niosk.LiveMessage;
import com.dragon.test.netty.service.niosk.NioSocketServer;
import com.dragon.test.netty.service.server.WorkThread;

import org.junit.Test;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import io.netty.buffer.ByteBuf;

/**
 * @ClassName NettyOioTest
 * @Description TODO
 * @SysUser Administrator
 * @Author dragon
 * @Date 2020-04-30 14:49
 * @Version 1.0
 */
public class NettyOioTest {

    @Test
    public void testOio() throws Exception {
        NettyOioServer nettyOioServer = new NettyOioServer();
        nettyOioServer.server(9990);
    }

    @Test
    public void testNio() throws Exception {
        NettyNioServer nettyNioServer = new NettyNioServer();
        nettyNioServer.server(9990);
    }

    @Test
    public void testNio2() throws Exception {
        NioSocketServer server = new NioSocketServer();
        server.server(9990);
    }

    @Test
    public void testOioSocketClient() throws Exception {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("127.0.0.1", 9990));
        String content = "Hello i'm client";
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeByte(LiveMessage.TYPE_MESSAGE);
        dos.writeInt(content.getBytes().length);
        dos.write(content.getBytes());
        dos.flush();
        InputStream is = socket.getInputStream();
        StringBuilder sb = new StringBuilder();
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = is.read(buffer)) > 0) {
            sb.append(new String(buffer, 0, length));
        }
        System.out.println(sb.toString());//阻塞读取不行，因为会一直等待读取，必须等待服务器写完或者关闭channel
        dos.close();
        is.close();
        socket.close();
    }

    public void testNioSocketClient() throws Exception {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("127.0.0.1", 9990));
        ByteBuffer bf = ByteBuffer.allocate(5);
        bf.put(LiveMessage.TYPE_HEART);
        bf.putInt(0);
        bf.flip();
        sc.write(bf);

        ByteBuffer ibf = ByteBuffer.allocate(5);
        int readByte = sc.read(ibf);
        ibf.flip();
        if (readByte == -1) {
            return;
        }
        for (int i = 0; i < readByte; i++) {
            System.out.println("read [" + ibf.get() + "]");
        }
    }

    @Test
    public void testNettyServer0() {
        new NettyServer0().connect(8080);
    }

    @Test
    public void testNettyServer1() throws InterruptedException {
        new NettyServer1().start(9999);
    }

    @Test
    public void testNettyClient1() throws InterruptedException {
        new NettyClient1().client("127.0.0.1", 9999);
    }

    @Test
    public void testWorkThread() throws InterruptedException {
        new WorkThread().run();
    }

}
