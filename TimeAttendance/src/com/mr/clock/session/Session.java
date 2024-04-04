package com.mr.clock.session;

import com.arcsoft.face.FaceFeature;
import com.mr.clock.pojo.Employee;
import com.mr.clock.pojo.User;
import com.mr.clock.pojo.WorkTime;

import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Session {
    /**
     * @create 2024/4/2 19:59
     * @Description 当前登陆管理员
     **/
    public static User user = null;
    /**
     * @create 2024/4/2 20:00
     * @Description 当前作息时间
     **/
    public static WorkTime workTime = null;
    /**
     * @create 2024/4/2 20:01
     * @Description 全部员工
     **/
    public static final HashSet<Employee> EMP_SET = new HashSet<>();
    /**
     * @create 2024/4/2 20:03
     * @Description 全部人脸特征
     **/
    public static final HashMap<String, FaceFeature> FACE_FEATURE_MAP = new HashMap<>();
    /**
     * @create 2024/4/2 20:05
     * @Description 全部人脸图像
     **/
    public static final HashMap<String, BufferedImage> IMAGE_MAP = new HashMap<>();
    /**
     * @create 2024/4/2 20:07
     * @Description 全部打卡记录
     **/
    public static final HashMap<Integer, Set<Date>> RECORD_MAP= new HashMap<>();
    /**
     * @create 2024/4/2 20:08
     * @Description 初始化全局资源
     * @return void
     **/
    public static void init(){

    }
    /**
     * @create 2024/4/2 20:09
     * @Description 释放全局资源
     * @return void
     **/
    public static void dispose(){

    }

}
