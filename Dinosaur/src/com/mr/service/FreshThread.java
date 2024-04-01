package com.mr.service;

import com.mr.view.GamePanel;
import com.mr.view.MainFrame;
import com.mr.view.ScoreDialog;
import com.sun.tools.javac.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author orane
 * @create 2024/3/30 14:52
 * @Description 刷新帧
 **/

public class FreshThread extends Thread{
    public static final int FREASH = 20;                        //刷新时间
    GamePanel p;                                                //游戏面板
    public FreshThread(GamePanel p){
        this.p = p;
    }
    public void run(){
        while (!p.isFinish()){                                  //如果游戏未结束
            p.repaint();                                        //重绘游戏面板
            try {
                Thread.sleep(FREASH);                           //按照刷新时间休眠
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Container c = p.getParent();                            //获取面板父容器
        while (!(c instanceof MainFrame)){                      //如果父容器不是主窗体类
            c = c.getParent();                                  //继续获取父容器的父容器
        }
        MainFrame frame = (MainFrame) c;                        //将容器强制转换为主窗体类
        new ScoreDialog(frame);                                 //弹出的成绩对话框
        frame.restart();                                        //主窗体重新开始游戏
    }
}
