package com.wuhulala.javase.nio;

import com.wuhulala.javase.UTF8Utils;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xueah20964 on 2017/5/23.
 */
public class ServerSocketChannelUser {

    private static Map<String, SocketAddress> nameAddress = new ConcurrentHashMap<>();
    private static Map<SocketAddress, SocketChannel> addressChannel = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        ServerSocketChannel ssc = null;
        Selector selector = null;
        try {
            selector = Selector.open();
            ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);  //设置为异步
            ssc.bind(new InetSocketAddress(9876));

            ssc.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            System.out.println("e 1" + e.getMessage());
        }

        try {
            assert selector != null;
            while (selector.select() > 0) {
                for (SelectionKey sk : selector.selectedKeys()) {
                    selector.selectedKeys().remove(sk);
                    if (sk.isValid() && sk.isAcceptable()) {
                        // 如果这个key是可以接收客户端请求的
                        assert ssc != null;
                        SocketChannel sc = ssc.accept();
                        sc.configureBlocking(false);
                        try {
                            sc.register(selector, SelectionKey.OP_READ);
                        } catch (ClosedChannelException e) {
                            System.out.println("客户端关闭了：" + e.getMessage());
                        }
                    } else if (sk.isValid() && sk.isReadable()) {
                        SocketChannel sc = null;
                        try {
                            sc = (SocketChannel) sk.channel();
                            parse(sc);
                        } catch (IOException e) {
                            addressChannel.remove(sc.getRemoteAddress());
                            nameAddress.remove(getKey(nameAddress, sc.getRemoteAddress()));
                            sk.cancel();
                            System.out.println("e 3" + e.getMessage());
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("e while" + e.getMessage());
        }


    }

    private static void parse(SocketChannel sc) throws IOException {
        ByteBuffer bb = ByteBuffer.allocate(1024);
        StringBuilder sb = new StringBuilder();
        String username;
        synchronized (sc) {
            while (sc.read(bb) > 0) {
                bb.flip();
                while (bb.hasRemaining()) {
                    sb.append((char) bb.get());
                }
                bb.clear();
            }
            username = sb.toString();
            System.out.println(username);
            if (!username.contains(":")) {
                SocketAddress sa = sc.getRemoteAddress();
                nameAddress.put(username, sa);
                addressChannel.put(sa, sc);
                System.out.println("进入聊天室的是：[" + username + "]-：地址" + sc.getRemoteAddress());
                sc.write(UTF8Utils.encode("进入聊天室成功！！！你好" + username));
            } else {
                String[] users = username.split(":");
                if(users.length == 3) {
                    SocketAddress address = nameAddress.get(users[1]);
                    if (address != null) {
                        SocketChannel socketChannel = addressChannel.get(address);
                        if (socketChannel != null) {
                            socketChannel.write(UTF8Utils.encode(users[0] + "说:【" + users[2] + "】"));
                        } else {
                            sc.write(UTF8Utils.encode("他不在线。。"));

                        }
                    } else {
                        sc.write(UTF8Utils.encode("他不在线。。"));
                    }
                }else{
                    sc.write(UTF8Utils.encode("输入信息无效。。"));
                }
            }
        }
    }


    //根据value值获取到对应的一个key值
    private static <K, V> K getKey(Map<K, V> map, V value) {
        for (K getKey : map.keySet()) {
            if (map.get(getKey) == value) {
                return getKey;
            }
        }
        return null;
    }

    @Test
    public void main1() {
        Map<String, InetSocketAddress> map = new HashMap<>();
        InetSocketAddress i1 = new InetSocketAddress(9631);
        InetSocketAddress i2 = new InetSocketAddress(9632);

        map.put("1", i1);
        map.put("2", i2);

        System.out.println(getKey(map, i2));
    }

}
