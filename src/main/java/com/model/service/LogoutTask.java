package com.model.service;

import com.model.bean.Message;
import com.trolltech.qt.core.QObject;

/**
 * @author HRH
 * @version 1.0
 * @date 2020/6/5 17:52
 */
public class LogoutTask extends QObject implements Runnable {
    private LogoutService logoutService;
    //参数：Boolean是否成功登录，String消息字符串
    public Signal1<Message> finished = new Signal1<>();

    public LogoutTask(LogoutService logoutService) {
        this.logoutService = logoutService;
    }

    @Override
    public void run() {
        Message message = logoutService.logout();
        //登录操作完成，发送finished信号
        finished.emit(message);
    }
}
