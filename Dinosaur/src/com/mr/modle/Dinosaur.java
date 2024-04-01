package com.mr.modle;

import com.mr.service.FreshThread;
import com.mr.service.Sound;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author orane
 * @create 2024/3/30 11:26
 * @Description 恐龙类
 **/

public class Dinosaur {
    public BufferedImage image;                      //主图片
    private BufferedImage image1, image2, image3;    //跑步图片
    public int x, y;                                 //坐标
    private int jumpValue = 0;                       //跳跃的增变量
    private boolean jumpState = false;               //跳跃状态
    private int stepTimer = 0;                       //踏步计时器
    private final int JUMP_HIGHT = 100;              //最大起跳高度
    private final int LOWEST_Y = 120;                //落地最低坐标
    private final int FREASH = FreshThread.FREASH;   //刷新时间

    /**
     * @create 2024/3/30 11:38
     * @Description 将恐龙的横坐标固定在50像素的位置，纵坐标采用落地时的坐标
     **/
    public Dinosaur(){
        x = 50;
        y = LOWEST_Y;
        try {
            image1 = ImageIO.read(new File("image/恐龙1.png"));
            image2 = ImageIO.read(new File("image/恐龙2.png"));
            image3 = ImageIO.read(new File("image/恐龙3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * @create 2024/3/30 11:41
     * @Description 踏步
     **/
    public void step(){
        //每过250毫秒，更换一张图片。
        int tmp = stepTimer /250 % 3;
        switch (tmp){
            case 1:
                image = image1;
                break;
            case 2:
                image = image2;
                break;
            default:
                image = image3;
        }
        stepTimer += FREASH;
    }
    /**
     * @create 2024/3/30 11:46
     * @Description 跳跃
     **/
    public void jump(){
        if (!jumpState){
            Sound.jump();
        }
        jumpState = true;
    }

    /**
     * @create 2024/3/30 16:50
     * @Description 移动
     **/
    public void move(){
        step();                                     //不断踏步
        if (jumpState){                             //如果正在跳跃
            if (y >= LOWEST_Y){                     //如果纵坐标大于等于最低点
                jumpValue = -4;                     //增变量为负值
            }
            if (y <= LOWEST_Y - JUMP_HIGHT){        //如果跳过最高点
                jumpValue = 4;                      //增变量为正值
            }
            y += jumpValue;                         //纵坐标发生变化
            if (y >= LOWEST_Y){                     //如果再次落地
                jumpState = false;                  //停止跳跃
            }
        }
    }

    /**
     * @create 2024/3/30 16:54
     * @Description 矩形边界类
     **/
    public Rectangle getFootBounds(){
        return new Rectangle(x + 30, y + 59, 29, 18);
    }
    public Rectangle getHeadBounds(){
        return new Rectangle(x + 66, y + 25, 32, 22);
    }
}
