package com.mr.clock.service;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author orane
 * @create 2024/4/2 20:11
 * @Description 摄像头服务类
 **/

public class CameraService {
    private static final Webcam WEBCAM = Webcam.getDefault();       //摄像头对象
    public static boolean startCamera(){
        if (WEBCAM == null){
            return false;
        }
        WEBCAM.setViewSize(new Dimension(640, 480));    //摄像头采用默认的640*480宽高
        return WEBCAM.open();                                       //开启摄像头，返回开启是否成功
    }
    public static boolean cameraIsOpen(){
        if (WEBCAM == null){                                        //如果计算机没有连接摄像头
            return false;
        }
        return WEBCAM.isOpen();
    }
    public static JPanel getCameraPanel(){
        WebcamPanel panel = new WebcamPanel(WEBCAM);        //摄像头画面面板
        panel.setMirrored(true);                            //开启镜像
        return panel;
    }
    public static BufferedImage getCameraFrame(){           //获取当前帧画面
        return WEBCAM.getImage();
    }
    public static void releaseCamera(){
        if (WEBCAM != null){
            WEBCAM.close();                                 //关闭摄像头
        }
    }

}
