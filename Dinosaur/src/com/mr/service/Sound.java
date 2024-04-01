package com.mr.service;

import java.io.FileNotFoundException;

public class Sound {
    static final String DIR = "music/";
    static final String BACKGROUND = "background.wav";
    static final String JUMP = "jump.wav";
    static final String HIT = "hit.wav";
    /**
     * @create 2024/3/30 12:22
     * @Description 播放声音方法
     * @Param file
     * @Param circulate
     * @return void
     **/
    private static void play(String file, boolean circulate){
        try {
            MusicPlayer player = new MusicPlayer(file, circulate);
            player.play();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * @create 2024/3/30 12:24
     * @Description 播放音效
     **/
    static public void jump(){
        play(DIR + JUMP, false);
    }
    static public void hit(){
        play(DIR + HIT, false);
    }
    static public void background(){
        play(DIR + BACKGROUND, true);
    }

}