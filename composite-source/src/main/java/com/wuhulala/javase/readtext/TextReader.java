package com.wuhulala.javase.readtext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by xueah20964 on 2017/5/22.
 */
public class TextReader {
    public static void main(String[] args) throws IOException, InterruptedException {
        Thread.sleep(Long.parseLong("10000"));

        long start = System.currentTimeMillis();
        String filePath = "G:" + File.separator + "test.txt";
        File theFile = new File(filePath);

        String filePath2 = "G:" + File.separator + "test2.txt";
        File file2 = new File(filePath2);
        if(file2.exists()){
            file2.delete();
            file2.createNewFile();

        }
        BufferedWriter bf = new BufferedWriter(new FileWriter(file2));

        LineIterator it = FileUtils.lineIterator(theFile, "GBK");
        try {
            while (it.hasNext()) {
                String line = it.nextLine();
                System.out.println(line);
                bf.write(line+"\n\r");
                bf.flush();
            }
        } finally {
            LineIterator.closeQuietly(it);
        }

        long end = System.currentTimeMillis();
        System.out.println("-------------------------------");
        System.out.println("-耗时：【" + (end - start) + "】-");
        System.out.println("-------------------------------");

    }
}
