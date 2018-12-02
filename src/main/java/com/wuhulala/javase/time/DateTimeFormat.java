package com.wuhulala.javase.time;

import com.wuhulala.utils.BaseLog;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * author： wuhulala
 * date： 2017/6/24
 * version: 1.0
 * description: 作甚的
 */
public class DateTimeFormat implements BaseLog {

    /**
     * 测试字符串转换日期(严格模式)
     *
     * Lenient => 宽容
     *
     */
    //@Test
    public void testExactingDateFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //String date = "2013-23-12";
        String date = "2013-12-12";
        //设置参数
        sdf.setLenient(false);
        try {
            sdf.parse(date);
        } catch (ParseException e) {
            logger.error("日期转化错误[" + e.getMessage() +"]", e);
        }
    }
}
