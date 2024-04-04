package com.mr.clock.service;

import com.arcsoft.face.*;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.DetectOrient;
import com.arcsoft.face.enums.ErrorInfo;
import com.arcsoft.face.toolkit.ImageFactory;
import com.arcsoft.face.toolkit.ImageInfo;
import com.mr.clock.session.Session;

import javax.naming.ConfigurationException;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

/**
 * @author orane
 * @create 2024/4/3 15:26
 * @Description 人脸识别服务
 **/


public class FaceEngineService {
    private static String appld = null;
    private static String sdkKey = null;
    private static FaceEngine faceEngine = null;                                            //人脸识别引擎
    private static String ENGINE_PATH = "lib/ArcFace/WIN64";                                //算法库文件夹地址
    private static final  String CONFIG_FILE = "src/com/mr/clock/config/ArcFace.properties";    //配置文件地址
    /**
     * @create 2024/4/3 15:25
     * @Description 对人脸识别引擎进行初始化
     **/
    static {
        Properties pro = new Properties();                                                  //配置文件解析类
        File config = new File(CONFIG_FILE);                                                //配置文件的文件对象
        try {
            if (!config.exists()) {                                                          //如果配置文件不存在
                throw new FileNotFoundException("缺少文件：" + config.getAbsolutePath());
            }
            pro.load(new FileInputStream(config));                                          //加载配置文件
            String appId = pro.getProperty("app_id");                                       //获取指定字段值
            String sdkKey = pro.getProperty("sdk_key");
            if (appId == null || sdkKey == null) {                                           //如果配置文件中获取不到这两个字段
                throw new ConfigurationException("ArcFace.properties文件缺少配置信息");
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        File path = new File(ENGINE_PATH);                                              //算法库文件夹
        faceEngine = new FaceEngine(path.getAbsolutePath());                            //人脸识别引擎
        //激活引擎，**首次激活需要联网
        int errorCode = faceEngine.activeOnline(appld, sdkKey);
        if (errorCode != ErrorInfo.MOK.getValue()
                && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()){
            System.err.println("ERROR:ArcFace 引擎激活失败，请检查激活码是否填写错误，或重新联网激活");
        }
        EngineConfiguration engineConfiguration = new EngineConfiguration();            //引擎配置
        engineConfiguration.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);            //单张图像模式
        engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_ALL_OUT);   //检测所有角度
        engineConfiguration.setDetectFaceMaxNum(1);                                     //检测最多人脸数
        engineConfiguration.setDetectFaceScaleVal(16);                                  //设置人脸相对于所在图片的长边的占比
        FunctionConfiguration functionConfiguration = new FunctionConfiguration();      //功能配置
        functionConfiguration.setSupportFaceDetect(true);                               //支持人脸检测
        functionConfiguration.setSupportFaceRecognition(true);                          //支持人脸识别
        engineConfiguration.setFunctionConfiguration(functionConfiguration);            //引擎使用此功能配置
        errorCode = faceEngine.init(engineConfiguration);                               //初始化引擎
        if (errorCode != ErrorInfo.MOK.getValue()){
            System.err.println("ERRORL:ArcFace 引擎初始化失败");
        }
    }
    /**
     * @create 2024/4/3 15:26
     * @Description 从一张人脸图像中分析出此人的面部特征
     * @Param img 被检测的图像
     * @return com.arcsoft.face.FaceFeature
     **/
    public static FaceFeature getFaceFeature(BufferedImage img){
        if (img == null){
            throw new NullPointerException("人脸图像为null");
        }
        //创建一个和原图像一样大的临时图像，临时图像类型为普通BRG图像
        BufferedImage face = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_BGR);
        face.setData(img.getData());                                                    //临时图像使用原图像中的数据
        ImageInfo imageInfo = ImageFactory.bufferedImage2ImageInfo(face);               //采集图像信息
        ArrayList<FaceInfo> faceInfoList = new ArrayList<>();                           //人物信息列表
        //从图像信息中采集人物信息
        faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(),imageInfo.getImageFormat(), faceInfoList);
        if (faceInfoList.isEmpty()){                                                    //如果人脸信息是空的
            return null;
        }
        FaceFeature faceFeature = new FaceFeature();                                    //人脸特征
        //从人脸信息中采集人脸特征
        faceEngine.extractFaceFeature(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList.get(0), faceFeature);
        return faceFeature;                                                             //采集之后的人脸特征
    }
    /**
     * @create 2024/4/3 15:31
     * @Description 用于一次性加载所有员工的人脸特征
     **/
    public static void loadAllFaceFeature(){
        Set<String> keys = Session.IMAGE_MAP.keySet();                                  //获取所有人脸图片对应的特征码集合
        for (String code : keys){                                                       //遍历所有的特征码
            BufferedImage image = Session.IMAGE_MAP.get(code);                          //取出一张人脸图片
            FaceFeature faceFeature = getFaceFeature(image);                            //获取该人脸图片的人脸特征对象
            Session.FACE_FEATURE_MAP.put(code, faceFeature);                            //将人脸特征对象保存至全局会话中
        }
    }
    /**
     * @create 2024/4/3 15:37
     * @Description 用于检测某个人脸特征是否可以在人脸特征库找到匹配
     * @Param targetFaceFeature 为传入的人脸特征对象
     * @return 得分最高的员工特征码
     **/
    public static String detectFace(FaceFeature targetFaceFeature){
        if (targetFaceFeature == null){
            return null;
        }
        //获取所有人脸特征对应的特征码集合
        Set<String> keys = Session.FACE_FEATURE_MAP.keySet();
        float score = 0;
        String resultCode = null;
        for (String code : keys){
            FaceFeature sourceFaceFeature = Session.FACE_FEATURE_MAP.get(code);
            FaceSimilar faceSimilar = new FaceSimilar();
            faceEngine.compareFaceFeature(targetFaceFeature, sourceFaceFeature, faceSimilar);
            if (faceSimilar.getScore() > score){
                score = faceSimilar.getScore();
                resultCode = code;
            }
        }
        if (score > 0.9){
            return resultCode;
        }
        return null;
    }
    /**
     * @create 2024/4/3 15:59
     * @Description 释放人脸识别引擎
     **/
    public static void dispost(){
        faceEngine.unInit();                    //引擎卸载
    }
}
