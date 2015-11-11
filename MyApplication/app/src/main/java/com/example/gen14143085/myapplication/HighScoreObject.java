package com.example.gen14143085.myapplication;

/**
 * Created by gen14143085 on 11/11/2015.
 */
public class HighScoreObject {

    public int score;
    public String name;
    public long timestamp;

    public HighScoreObject(int score, String name, long timestamp) {

        this.score = score;
        this.name = name;
        this.timestamp = timestamp;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }



    public long getTimestamp() {
        return timestamp;
    }
}
