package com.wuhulala.javase.jsoup;

/**
 * Created by xueah20964 on 2017/5/24.
 */
public class BlogMonitor {
    public static void main(String[] args) {

        String url = null;
        if (args.length != 0) {
            url = args[0];
        }
        if (url == null) {
            url = "http://blog.csdn.net/u013076044";
        }
        System.out.println(BlogRankUtils.getBlogInfoOfUrl(url));
    }
}
