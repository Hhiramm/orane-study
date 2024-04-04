package com.mr.clock.service;

import com.mr.clock.session.Session;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @author orane
 * @create 2024/4/4 14:32
 * @Description 图像文件服务类
 **/

public class ImageService {
    private static final File FACE_DIR = new File("src/faces");         //存放员工照片文件的文件夹
    private static final String SUFFIX = "png";                                 //图像文件的默认格式
    /**
     * @create 2024/4/4 14:41
     * @Description 一次性加载所有员工照片
     **/
    public static Map<String, BufferedImage> loadAllImage(){
        if (!FACE_DIR.exists()){
            System.err.println("src\\face\\人脸图像文件夹丢失！");                    //如果人脸图像的文件夹丢失
            return null;
        }
        File faces[] = FACE_DIR.listFiles();                                    //获取文件夹下的所有文件
        for (File f : faces){
            try {
                BufferedImage img = ImageIO.read(f);                            //创建该图像文件的BufferedImage对象
                String fileName = f.getName();                                  //文件名
                String code = fileName.substring(0, fileName.indexOf('.'));     //截取文件名，丢掉后缀名
                Session.IMAGE_MAP.put(code, img);                               //将人脸图像添加到全局对话中
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    /**
     * @create 2024/4/4 14:45
     * @Description 保存新员工的照片文件
     * @Param image 新员工的照片图像
     * @Param code 新员工的特征码
     * @return void
     **/
    public static void saveFaceImage(BufferedImage image, String code){
        try {
            //将图像按照SUFFIX格式写入文件夹中
            ImageIO.write(image, SUFFIX, new File(FACE_DIR, code + "." + SUFFIX));
            Session.IMAGE_MAP.put(code, image);                             //将人脸图像添加到全局对话中
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * @create 2024/4/4 14:49
     * @Description 删除人脸图像文件
     * @Param code 员工特征码
     * @return void
     **/
    public static void deleteFaceImage(String code){
        Session.IMAGE_MAP.remove(code);                                     //在全局会话中删除该员工的图像
        File image = new File(FACE_DIR, code + "." + SUFFIX);         //创建该员工人脸图像文件对象
        if (image.exists()){                                                //如果此文件存在
            image.delete();                                                 //删除文件
            System.out.println(image.getAbsolutePath() + "---已删除");       //提示删除文件成功
        }
    }
}
