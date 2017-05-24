package com.wuhulala.javase.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by xueah20964 on 2017/5/24.
 */
public class BlogRankUtils {

    public static BlogInfo getBlogInfoOfUrl(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements ranks = doc.select("#blog_rank");
            int number = Integer.valueOf(ranks.first().child(0).child(0).text().replace("次", ""));
            int score = Integer.valueOf(ranks.first().child(1).child(0).text());
            int rank = Integer.valueOf(ranks.first().child(3).child(0).text().replace("第", "").replace("名", ""));
            return new BlogInfo(score, rank, number);

        } catch (IOException e) {
            return new BlogInfo(0, 0, 0);
        }
    }

}
