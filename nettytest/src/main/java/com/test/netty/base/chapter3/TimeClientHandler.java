package com.test.netty.base.chapter3;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeClientHandler extends ChannelHandlerAdapter {
    private  ByteBuf firstMEsg = null;
    
    public TimeClientHandler () {
        byte[] req = "QUERY TIME ORDER".getBytes();
        firstMEsg = Unpooled.buffer(req.length);
        // 序列化消息
        firstMEsg.writeBytes(req);
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    //向服务器端发送信息
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(firstMEsg);
    }

    // 处理服务器端返回的数据
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req,"UTF-8");
        System.out.println("Now is:" + body);
    }
}
