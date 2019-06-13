package com.wuhulala.javase.socket;

import com.wuhulala.utils.BaseLog;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author xueah20964
 * @date 2017/3/28
 */
public class MessageUtils  implements BaseLog{
    public static String processClientMessage(InputStream input) {
        StringBuilder request = new StringBuilder(2048);

        int i = 0;
        byte[] buffer = new byte[2048];

        try {
            i = input.read(buffer);
        } catch (IOException e) {
            logger.error(">>>>>>>>>>>>>>解析错误<<<<<<<<<<<<<<<", e);
            System.out.println(e.getMessage());
        }

        for (int j = 0; j < i; j++) {
            request.append((char) buffer[j]);
        }
        String result = request.toString();
        logger.debug(">>>>>>>>>>>>>>解析报文结果<<<<<<<<<<<<<<<");
        System.out.println(result);
        logger.debug(">>>>>>>>>>>>>>解析报文结果<<<<<<<<<<<<<<<");
        return result;
    }

    public synchronized static void  doSendMessage(Socket socket, String message) throws IOException {
        OutputStream out = socket.getOutputStream();
        out.write(message.getBytes());
        out.flush();
    }
}
