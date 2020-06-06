package com.model.service;

import com.model.bean.Login;
import com.model.bean.Message;
import com.trolltech.qt.core.QObject;

/**
 * @author HRH
 * @version 1.0
 * @date 2020/5/25 0:14
 */
public class LoginTask extends QObject implements Runnable {
    private Login login;
    private LoginService loginService;
    //参数：Boolean是否成功登录，String消息字符串
    public Signal1<Message> finished = new Signal1<>();

    public LoginTask(Login login, LoginService loginService) {
        this.login = login;
        this.loginService = loginService;
    }

    @Override
    public void run() {
        Message message = loginService.login(login);
        //登录操作完成，发送finished信号
        finished.emit(message);
    }
}
