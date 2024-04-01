package com.mr.view;

import com.mr.modle.Dinosaur;
import com.mr.modle.Obstacle;
import com.mr.service.FreshThread;
import com.mr.service.ScoreRecorder;
import com.mr.service.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * @author orane
 * @create 2024/3/30 14:44
 * @Description 游戏面板，继承于JPanel
 **/

public class GamePanel extends JPanel implements KeyListener {
    private BufferedImage image;                                //主图片
    private BackgroundImage background;        //背景图片
    private Dinosaur golden;                                    //恐龙
    private Graphics2D g2;                                      //主图片绘图对象
    private int addObstacleTimer = 0;                           //添加障碍计时器
    private boolean finish = false;                             //游戏结束标志
    private List<Obstacle> list = new ArrayList<Obstacle>();    //障碍集合
    private final int FREASH = FreshThread.FREASH;              //刷新时间
    int score = 0;                                              //得分
    int scoreTimer = 0;                                         //分数计时器
    public GamePanel(){
        //主图片采用宽800、高300的彩色图片
        image = new BufferedImage(800, 300, BufferedImage.TYPE_INT_BGR);
        g2 = image.createGraphics();                        //获取主图片绘图对象
        background = new BackgroundImage();                 //初始化滚动背景
        golden = new Dinosaur();                            //初始化小恐龙
        list.add(new Obstacle());                             //添加第一个障碍
        FreshThread t = new FreshThread(this);          //刷新帧线程
        t.start();                                          //启动线程
    }
    private void paintImage(){
        background.roll();
        golden.move();
        g2.drawImage(background.image, 0, 0, this);
        if (addObstacleTimer == 1300){
            if (Math.random() * 100 > 40){
                list.add(new Obstacle());
            }
            addObstacleTimer = 0;
        }
        for (int i = 0; i < list.size(); i++){
            Obstacle o = list.get(i);
            if (o.isLive()){
                o.move();
                g2.drawImage(o.image, o.x, o.y, this);
                if (o.getBounds().intersects(golden.getFootBounds())
                    || o.getBounds().intersects(golden.getHeadBounds())){
                    Sound.hit();
                    gameOver();
                }
            }else {
                list.remove(i);
                i--;
            }
        }
        g2.drawImage(golden.image, golden.x, golden.y, this);
        if (scoreTimer >= 500){
            score += 10;
            scoreTimer = 0;
        }
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("黑体", Font.BOLD, 24));
        g2.drawString(String.format("%06d", score), 700, 30);
        addObstacleTimer += FREASH;
        scoreTimer += FREASH;
    }
    public void paint(Graphics g){
        paintImage();
        g.drawImage(image, 0, 0, this);
    }
    public void gameOver(){
        ScoreRecorder.addNewScore(score);
        finish = true;
    }
    public boolean isFinish(){
        return finish;
    }
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();// 获取按下的按键值
        if (code == KeyEvent.VK_SPACE) {// 如果是空格
            golden.jump();// 恐龙跳跃
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
