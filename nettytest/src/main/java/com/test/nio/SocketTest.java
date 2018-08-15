package com.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class SocketTest {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8081);

        ssc.socket().bind(address); //绑定监听地址

        ssc.configureBlocking(false); // 设置为非阻塞

        Selector selector = Selector.open(); // 创建一个多路复用器


        SelectionKey skey = ssc.register(selector, SelectionKey.OP_ACCEPT);
        //  new Thread(new ReactorTask()).start();

    }


}