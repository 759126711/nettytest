package com.test.netty.base.chapter3;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TimeClient {
    public void connect(int port, String host) throws Exception {
        // 配置客户端NIO线程组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();

            b.group(group).channel(NioServerSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInit());
            // 发起异步连接操作
            ChannelFuture f = b.connect(host, port);
            // 等待客户端链路关闭
            f.channel().closeFuture().sync();

        } catch (Exception e) {
            System.out.println("error");
        } finally {
            // 优雅退出，释放NIO线程组
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port = 8080;

        try {
            new TimeClient().connect(port,"127.0.0.1");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("连接服务器错误");
        }
    }

    private class ChannelInit extends ChannelInitializer<SocketChannel>  {
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast(new TimeClientHandler());
        }
    }
}
