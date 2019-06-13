package com.wuhulala.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xueaohui
 * @version 1.0
 * @date 2017/3/27
 * @link https://github.com/wuhulala
 */
public interface BaseLog {
    //其实这种方式是不严格的，但是为了方便。。。。
    Logger logger = LoggerFactory.getLogger(BaseLog.class);
}
