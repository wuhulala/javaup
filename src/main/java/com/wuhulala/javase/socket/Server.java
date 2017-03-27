package com.wuhulala.javase.socket;

import com.wuhulala.utils.BaseLog;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author xueaohui
 * @version 1.0
 * @date 2017/3/27
 * @link https://github.com/wuhulala
 */
public class Server extends BaseLog {



    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            logger.debug("------------------服务器启动成功---------------");
            doProcess(serverSocket);
        } catch (IOException e) {
            System.out.println("启动服务出错。。。");
            e.printStackTrace();
        }

    }

    private static void doProcess(ServerSocket serverSocket) {
        boolean shutdown = false;
        Socket socket;
        InputStream input;
        OutputStream out;
        try {
            while (!shutdown) {
                socket = serverSocket.accept();
                input = socket.getInputStream();
                out = socket.getOutputStream();
                //处理报文
                String message = processClientMessage(input);

                switch (message){
                    case Constants.SHUTDOWN_COMMAND:
                        shutdown = true;
                        break;
                    case Constants.RECEIVE_OK_COMMAND:
                        doSend(out);
                        break;
                    default:
                        logger.warn("报文解析失败。。。。丢掉");
                        break;
                }
            }
        }catch (Exception e){
            logger.error("接受参数错误。。。",e);
        }
    }

    /**
     * 发送文件到客户端
     * @param out
     */
    private static void doSend(OutputStream out) throws IOException {
        logger.debug("<==========开始发送文件========>");
        String message = "文件XXX";
        out.write(message.getBytes());
        logger.debug("<==========发送文件结束========>");
    }

    private static String processClientMessage(InputStream input) {
        StringBuilder request = new StringBuilder(2048);

        int i = 0;
        byte[] buffer = new byte[2048];

        try {
            i = input.read(buffer);
        } catch (IOException e) {
            logger.error(">>>>>>>>>>>>>>解析错误<<<<<<<<<<<<<<<",e);
            System.out.println(e.getMessage());
        }

        for (int j = 0; j < i; j++) {
            request.append((char)buffer[j]);
        }
        String result = request.toString();
        logger.debug(">>>>>>>>>>>>>>解析报文结果<<<<<<<<<<<<<<<");
        System.out.println(result);
        logger.debug(">>>>>>>>>>>>>>解析报文结果<<<<<<<<<<<<<<<");
        return result;
    }


}
