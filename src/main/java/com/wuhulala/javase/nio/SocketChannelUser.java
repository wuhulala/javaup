package com.wuhulala.javase.nio;


import com.wuhulala.javase.UTF8Utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * Created by xueah20964 on 2017/5/23.
 */
public class SocketChannelUser {

    private Selector selector = null;
    private SocketChannel socketChannel = null;
    private String name;
    public static void main(String[] args) throws IOException, InterruptedException {
        //for (int i = 0; i < 10; i++) {
            new SocketChannelUser("lisi" ).init();
        //}

    }

    public SocketChannelUser(String name) {
        this.name = name;
    }

    public void init() throws IOException {
        selector = Selector.open();

        InetSocketAddress isa = new InetSocketAddress("127.0.0.1", 9876);
        SocketChannel socketChannel = SocketChannel.open(isa);
        socketChannel.configureBlocking(false);

        socketChannel.register(selector, SelectionKey.OP_READ);

        while (!socketChannel.finishConnect()) {
            System.out.println(" connecting ...");
        }

        //------------ 注册自己的存在------------------
        String newData = name;
        ByteBuffer buf = ByteBuffer.allocate(480);
        buf.clear();
        buf.put(newData.getBytes());
        buf.flip();
        socketChannel.write(buf);

        //-----------打开自己的读取功能----------------
        new Thread(new ReadThread()).start();

        // 创建键盘输入流
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            // 读取键盘输入
            String line = scan.nextLine();
            // 将键盘输入的内容输出到SocketChannel中
            socketChannel.write(UTF8Utils.encode(name+":"+line));
        }
    }

    public class ReadThread implements Runnable{

        @Override
        public void run() {
            try {
                while (selector.select() > 0) {
                    for (SelectionKey sk : selector.selectedKeys()) {
                        selector.selectedKeys().remove(sk);
                        // 如果该SelectionKey对应的Channel中有可读的数据
                        if (sk.isReadable()) {
                            SocketChannel sc = (SocketChannel) sk.channel();
                            ByteBuffer buff = ByteBuffer.allocate(1024);
                            String content = "";
                            while (sc.read(buff) > 0) {
                                buff.flip();
                                content += UTF8Utils.decode(buff);
                            }
                            System.out.println("聊天信息：" + content);
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("错误：" + e.getMessage());
            }
        }
    }
}
