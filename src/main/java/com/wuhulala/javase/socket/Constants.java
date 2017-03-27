package com.wuhulala.javase.socket;

import java.io.File;

/**
 * 常量类
 *
 * @author xueaohui
 * @version 1.0
 * @date 2017/3/27
 * @link https://github.com/wuhulala
 */
public interface Constants {
    //测试文件地址
    String TEST_FILE_PATH = "F:"+File.separator+"test.txt";
    //文件接收地址
    String RECEIVE_DIR = "D:"+ File.separator+"test"+File.separator;
    //关闭服务器命令
    static final String SHUTDOWN_COMMAND = "sd";
    //接收开始命令
    static final String RECEIVE_START_COMMAND = "rs";
    //接收方接收完成
    static final String RECEIVE_END_COMMAND = "re";
    //接收准备完成
    static final String RECEIVE_OK_COMMAND = "ro";
    //发送方准备完成
    static final String SEND_OK_COMMAND = "so";
    //发送开始命令
    static final String SEND_START_COMMAND = "ss";
    //发送方发送完成
    static final String SEND_END_COMMAND = "se";

}
