package com.start;

import com.controller.WindowController;
import com.trolltech.qt.core.QCoreApplication;
import com.trolltech.qt.core.QTranslator;
import com.trolltech.qt.gui.QApplication;

/**
 * @author HRH
 * @version 1.0
 * @date 2020/5/23 19:33
 */
/*
 *  TODO: 可以存储多个账户信息
 *  TODO: 解决重定向URL中文乱码问题
 *  TODO: 为什么用Map作为请求主体时会出错?
 * */
public class Main {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                QApplication app = new QApplication(args);
                //设置应用名称
                QCoreApplication.setApplicationName("AutoConnect");
                //设置中文翻译
                QTranslator translator = new QTranslator(app);
                translator.load("qt_zh_CN.qm");
                QApplication.installTranslator(translator);
                //显示窗口
                WindowController windowController = new WindowController();
                windowController.firstShowWindow();
                app.exec();
            }
        }).start();
    }
}
