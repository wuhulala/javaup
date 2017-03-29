package com.wuhulala.javase.socket;

import com.wuhulala.utils.BaseLog;

import java.io.*;
import java.net.Socket;

/**
 * @author xueaohui
 * @version 1.0
 * @date 2017/3/27
 * @link https://github.com/wuhulala
 */
public class Client implements BaseLog {
    public static void main(String[] args) {
        createSocket();
    }

    private static void createSocket() {
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
        int count = 0;
        while (!shutdown) {
            try {
                input = socket.getInputStream();
                out = socket.getOutputStream();
                out.write(Constants.RECEIVE_OK_COMMAND.getBytes());
                String message = MessageUtils.processClientMessage(input);
                if("".equals(message)){
                    logger.warn("无效报文 抛弃之。。。");
                }else if(Constants.SEND_OK_COMMAND.equals(message)){
                    out.write(Constants.RECEIVE_START_COMMAND.getBytes());
                    Thread.sleep(500);
                    doReceive(input);
                    out.write(Constants.RECEIVE_END_COMMAND.getBytes());
                }
                //发送准备接收报文
                //out.write(Constants.RECEIVE_OK_COMMAND.getBytes());
                //logger.debug("第二次接收----------------");
                //doReceive(input);

            } catch (Exception e) {
                shutdown = true;
                logger.error("发生未知错误!关闭自己！！！！", e);
            }
        }
    }

    private static void doReceive(InputStream input) throws IOException {
        logger.debug(">>>>>>>>>>>>>>客户端接收开始<<<<<<<<<<<<<<<");
        FileOutputStream outputStream = null;
        try  {
             outputStream=
                    new FileOutputStream(new File(Constants.RECEIVE_DIR + File.separator + "test.txt"));
            byte[] buf = new byte[1024];
            int bytesRead;

            while ((bytesRead = input.read(buf)) > 0) {
                if (isNotEndCommand(buf,bytesRead)) {
                    outputStream.write(buf, 0, bytesRead);
                }else{
                    break;
                }
            }
        }catch (Exception e){
            logger.error("asdasdasd",e);
        }finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
        logger.debug(">>>>>>>>>>>>>>客户端接收完成<<<<<<<<<<<<<<<");
    }

    /**
     * 判断报文是否是
     *
     * @param buffer
     * @return
     */
    public static boolean isNotEndCommand(byte[] buffer,int bytesRead) {
        if (bytesRead != Constants.SEND_END_COMMAND.getBytes().length) return true;
        StringBuilder request = new StringBuilder(2048);
        for (int  i = 0 ; i < bytesRead ; i++) {
            request.append((char) buffer[i]);
        }
        return !Constants.SEND_END_COMMAND.equals(request.toString());
    }
}
