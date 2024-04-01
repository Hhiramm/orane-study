package com.mr.service;

import java.io.*;
import java.util.Arrays;

public class ScoreRecorder {
    private static final String SCOREFILE = "data/source";      //成绩记录文件
    private static int scores[] = new int[3];                   //存储当前得分前三名的成绩数据
    /**
     * @create 2024/3/30 14:07
     * @Description 从成绩记录文件中读取到历史前3名数据
     * @return void
     **/
    public static void init(){
        File f = new File(SCOREFILE);
        if (!f.exists()){
            try {
                f.createNewFile();                              //创建新文件
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(f);                       //文件字节输入流
            isr = new InputStreamReader(fis);                   //字节流转字符流
            br = new BufferedReader(isr);                       //缓冲字符流
            String value = br.readLine();                       //读取一行
            if (!(value == null || "".equals(value))){          //如果不为空值
                String vs[] = value.split(",");           //分割字符串
                if (vs.length < 3){                             //如果分割结果小于3
                    Arrays.fill(scores, 0);                 //数值填充0
                } else {
                    for (int i = 0; i < 3; i++){
                        scores[i] = Integer.parseInt(vs[i]);    //将记录文件中的值赋给当前分数数组
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * @create 2024/3/30 14:10
     * @Description 写入记录文件
     **/
    public static void saveScore(){
        String value = scores[0] + "," + scores[1] + "," + scores[2];
        FileOutputStream fos = null;             //文件字节输出流
        OutputStreamWriter osw = null;               //字节流转字符流
        BufferedWriter bw = null;                        //缓冲字符流
        try {
            fos = new FileOutputStream(SCOREFILE);
            osw = new OutputStreamWriter(fos);
            bw = new BufferedWriter(osw);
            bw.write(value);                                                    //写入拼接后的字符串
            bw.flush();                                                         //字符流刷新
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                osw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * @create 2024/3/30 14:19
     * @Description 向成绩数组中添加新成绩
     **/
    static public void addNewScore(int score){
        int tmp[] = Arrays.copyOf(scores, 4);       //长度为4的临时数组
        tmp[3] = score;                                       //将新分数赋值给第4个元素
        Arrays.sort(tmp);                                     //临时数组升序排列
        scores = Arrays.copyOfRange(tmp, 1, 4);      //将后3个元素赋值给得分数组
    }
    static public int[] getScores(){
        return scores;
    }
}
