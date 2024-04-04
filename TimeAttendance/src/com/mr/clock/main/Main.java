package com.mr.clock.main;

import com.mr.clock.frame.MainFrame;
import com.mr.clock.frame.MainPanel;
/**
 * @author orane
 * @create 2024/4/4 16:53
 * @Description 启动类
 **/

public class Main {
    public static void main(String[] args) {
        MainFrame f = new MainFrame();// 创建主窗体
        f.setPanel(new MainPanel(f));// 主窗体加载主面板
        f.setVisible(true);// 显示主窗体
    }
}