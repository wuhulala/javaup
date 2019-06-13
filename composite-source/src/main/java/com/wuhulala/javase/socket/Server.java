package com.wuhulala.javase.socket;

import com.wuhulala.utils.BaseLog;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author xueaohui
 * @version 1.0
 * @date 2017/3/27
 * @link https://github.com/wuhulala
 */
public class Server implements BaseLog {


    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            logger.debug("------------------服务器启动成功---------------");
            while(true) {
                Socket socket = serverSocket.accept();
                doProcess(socket);
            }
        } catch (IOException e) {
            System.out.println("启动服务出错。。。");
            e.printStackTrace();
        }

    }

    private static void doProcess(Socket socket) {
        boolean shutdown = false;
        InputStream input;
        OutputStream out;
        try {
            while (!shutdown) {
                input = socket.getInputStream();
                out = socket.getOutputStream();

                //处理报文
                String message = MessageUtils.processClientMessage(input);

                switch (message) {
                    case Constants.SHUTDOWN_COMMAND:
                        shutdown = true;
                        break;
                    case Constants.RECEIVE_OK_COMMAND:
                        logger.debug("获取到确定 写入信息");
                        MessageUtils.doSendMessage(socket,Constants.SEND_OK_COMMAND);
                        break;
                    case Constants.RECEIVE_START_COMMAND:
                        logger.debug("获取到接受开始信息，开始写入信息");
                        doSend(socket);
                        break;
                    case Constants.RECEIVE_END_COMMAND:
                        socket.close();
                        break;
                    default:
                        logger.warn("报文解析失败。。。。丢掉");
                        break;
                }
            }
        } catch (Exception e) {
            logger.error("接受参数错误。。。", e);
        }
    }



    /**
     * 发送文件到客户端
     *
     * @param socket
     */
    private static void doSend(Socket socket) throws IOException {
        OutputStream out = socket.getOutputStream();
        boolean shutdown = false;
        try (FileInputStream inputStream = new FileInputStream(new File(Constants.TEST_FILE_PATH))) {
            logger.debug("<==========开始发送文件========>");
            byte[] buffer = new byte[2048];
            while (inputStream.read(buffer) != -1) {
                out.write(buffer);
            }
            out.flush();
            out.write(Constants.SEND_END_COMMAND.getBytes());
            out.flush();
            logger.debug("<==========发送文件结束========>");

            while (true) {
                //处理报文
                String message = MessageUtils.processClientMessage(socket.getInputStream());

                //如果是空说明客户端断开
                if("".equals(message)){
                    socket.close();
                    break;
                }

                if (Constants.RECEIVE_OK_COMMAND.equals(message)) {
                    break;
                } else if (Constants.RECEIVE_END_COMMAND.equals(message)) {
                    logger.info("客户端接收文件成功:继续监控");
                } else {
                    logger.warn("报文解析失败。。。。丢掉[:" + message + "]");
                }
            }
            if(socket.isConnected()) {
                doSend(socket);
            }
        } catch (IOException e) {
            logger.error("发送文件失败", e);
        }
    }

}
