package com.test.netty.messagepack;

import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/***
 *
 *  MessagePack编码解码(序列化与反序列化)技术
 *
 */

public class MsgPackTest {
    public static void main(String[] args) throws IOException {
        List<String> src = new ArrayList<>();
        src.add("msgpack");
        src.add("kumofs");
        src.add("viver");
        MessagePack mp = new MessagePack();
        //序列化
        byte[] raw = mp.write(src);

        // 反序列化
        List<String> data = mp.read(raw, Templates.tList(Templates.TString));
        System.out.println(data.get(0));
        System.out.println(data.get(1));
        System.out.println(data.get(2));
    }
}
