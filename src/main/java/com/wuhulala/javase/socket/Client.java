package com.wuhulala.javase.socket;

import com.wuhulala.utils.BaseLog;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

/**
 * @author xueaohui
 * @version 1.0
 * @date 2017/3/27
 * @link https://github.com/wuhulala
 */
public class Client extends BaseLog {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8888);
            doProcess(socket);
        } catch (IOException e) {
            System.out.println("连接失败");
        }
    }

    private static void doProcess(Socket socket) {
        boolean shutdown = false;
        InputStream input;
        OutputStream out;
        int count = 0 ;
        while (!shutdown) {
            try {
                input = socket.getInputStream();
                out = socket.getOutputStream();
                if(count == 0 ) {
                    //发送准备接收报文
                    out.write(Constants.RECEIVE_OK_COMMAND.getBytes());
                    count ++;
                }
                doReceive(input);
            } catch (Exception e) {
                shutdown = true;
                logger.error("发生未知错误!关闭自己！！！！", e);
            }
        }
    }

    private static void doReceive(InputStream input) throws UnsupportedEncodingException {
        StringBuilder request = new StringBuilder(2048);

        int i = 0;
        byte[] buffer = new byte[2048];
        try {
            i = input.read(buffer);
        } catch (IOException e) {
            logger.error(">>>>>>>>>>>>>>解析错误<<<<<<<<<<<<<<<", e);
        }

        for (int j = 0; j < i; j++) {
            request.append((char) buffer[j]);
        }
        String result = request.toString();
        logger.debug(">>>>>>>>>>>>>>客户端接收开始<<<<<<<<<<<<<<<");
        System.out.println(new String(result.getBytes(), "GBK"));
        logger.debug(">>>>>>>>>>>>>>客户端接收完成<<<<<<<<<<<<<<<");

    }
}
