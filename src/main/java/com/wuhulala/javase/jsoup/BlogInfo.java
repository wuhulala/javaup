package com.wuhulala.javase.jsoup;

/**
 * Created by xueah20964 on 2017/5/24.
 */
public class BlogInfo {
    private int score;
    private int rankNum;
    private int readNum;

    public BlogInfo(int score, int rankNum, int readNum) {
        this.score = score;
        this.rankNum = rankNum;
        this.readNum = readNum;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRankNum() {
        return rankNum;
    }

    public void setRankNum(int rankNum) {
        this.rankNum = rankNum;
    }

    public int getReadNum() {
        return readNum;
    }

    public void setReadNum(int readNum) {
        this.readNum = readNum;
    }

    @Override
    public String toString() {

        return "BlogInfo{" +
                "score=" + score +
                ", rankNum=" + rankNum +
                ", readNum=" + readNum +
                '}';
    }
}
