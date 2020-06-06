package com.controller;


import com.model.bean.Login;
import com.model.bean.Message;
import com.model.service.LoginService;
import com.model.service.LoginTask;
import com.model.service.LogoutService;
import com.model.service.LogoutTask;
import com.model.util.SystemUtil;
import com.trolltech.qt.gui.QApplication;
import com.view.LoginWindow;
import com.view.Ui_LoginWindow;

import java.io.*;

/**
 * @author HRH
 * @version 1.0
 * @date 2020/5/24 21:19
 */
public class WindowController  {
    private LoginWindow loginWindow = new LoginWindow();
    private LoginService loginService = new LoginService();
    private LogoutService logoutService = new LogoutService();
    private int loginCount = 0; //连续登录次数
    private int maxLoginCount = 3; //最大连续登录次数, 超过该次数就不再尝试自动登录

    public WindowController() {
        Ui_LoginWindow ui = loginWindow.getUi();
        //加载登录信息
        loadLoginData();
        ui.combo_password.lineEdit().returnPressed.connect(ui.pb_Login, "click()");
//        ui.le_password.returnPressed.connect(ui.pb_Login, "click()");
        ui.pb_Login.clicked.connect(this, "login()");
        ui.pb_logout.clicked.connect(this, "logout()");
        ui.cb_autoStart.toggled.connect(this, "toggled_on_cb_autoStart(boolean)");
    }

    /**
     * 加载登录信息
     */
    public void loadLoginData() {
        Ui_LoginWindow ui = loginWindow.getUi();
        try (ObjectInputStream in =
                     new ObjectInputStream(new BufferedInputStream(new FileInputStream("loginData.txt")));) {
            //设置窗口信息
            ui.cb_autoLogin.setChecked(in.readBoolean());
            ui.cb_autoStart.setChecked(in.readBoolean());
            ui.combo_isp.setCurrentIndex(in.readInt());

            //添加账号和密码
            Object obj;
            Login login;
            int index = 0;
            while ((obj = (Login) in.readObject()) != null) {
                login = (Login) obj;
                ui.combo_id.setItemText(index, login.getId());
                ui.combo_password.setItemText(index, login.getPassword());
                index++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存登录信息
     */
    public void saveLoginData() {
        Ui_LoginWindow ui = loginWindow.getUi();
        //将当前的账号密码移到顶端
//        int currentIndex = ui.combo_id.currentIndex();
//        ui.combo_id.insertItem(0, ui.combo_id.itemText(currentIndex));
//        ui.combo_password.insertItem(0, ui.combo_password.itemText(currentIndex));
//        currentIndex = ui.combo_id.currentIndex();
//        ui.combo_id.removeItem(currentIndex);
//        ui.combo_password.removeItem(currentIndex);
//        ui.combo_id.setCurrentIndex(0);

        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(
                "loginData.txt")));) {
            //保存窗口信息
            out.writeBoolean(ui.cb_autoLogin.isChecked());
            out.writeBoolean(ui.cb_autoStart.isChecked());
            out.writeInt(ui.combo_isp.currentIndex());

            //保存账号密码
            Login login;
            int index = 0;
            while (index < ui.combo_id.count()) {
                login = new Login();
                login.setId(ui.combo_id.itemText(index));
                login.setPassword(ui.combo_password.itemText(index));
                out.writeObject(login);
                index++;
            }
            out.writeObject(null);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 开启或关闭开机自启
     */
    public void toggled_on_cb_autoStart(boolean isChecked) {
        if (isChecked) {
            //开启开机自启
            SystemUtil.setAutoStart(true, "./AutoConnect.exe");
            loginWindow.showMessage("开启开机自启!", "");
        } else {
            //关闭开机自启
            SystemUtil.setAutoStart(false, "./AutoConnect.exe");
            loginWindow.showMessage("关闭开机自启!", "");
        }

        saveLoginData();
    }

    /**
     * 执行登录操作
     */
    public void login() {
        Ui_LoginWindow ui = loginWindow.getUi();

        ui.pb_Login.setText("登录中...");
        ui.pb_Login.setEnabled(false);

        String id = ui.combo_id.currentText();
        String password = ui.combo_password.currentText();
        String isp = "";
        switch (ui.combo_isp.currentIndex()) {
            case 1:
                isp = Login.CHINA_TELECOM;
                break;
            case 2:
                isp = Login.CHINA_MOBILE;
                break;
            case 3:
                isp = Login.CHINA_UNICOM;
                break;
            case 4:
                isp = Login.BINJIANG_COLLEGE;
                break;
            default:
                loginWindow.showMessage("请选择运营商!", "");
                loginWindow.getUi().pb_Login.setText("登录");
                loginWindow.getUi().pb_Login.setEnabled(true);
                return;
        }
        Login login = new Login(id, password, isp);

//        System.out.println(login);

        LoginTask loginTask = new LoginTask(login, loginService);
        loginCount++;
        loginTask.finished.connect(this, "afterLogin(Message)");
        Thread loginThread = new Thread(loginTask, "loginThread");
        loginThread.setDaemon(true);
        loginThread.start();
    }

    /**
     * 执行注销操作
     */
    public void logout() {
        loginWindow.getUi().pb_logout.setText("注销中...");
        loginWindow.getUi().pb_logout.setEnabled(false);

        LogoutTask logoutTask = new LogoutTask(logoutService);
        logoutTask.finished.connect(this, "afterLogout(Message)");
        Thread logoutThread = new Thread(logoutTask, "logoutThread");
        logoutThread.setDaemon(true);
        logoutThread.start();
    }

    /**
     * 判断是否成功登录: 若成功登录则显示成功消息并保存记录,15秒后自动关闭程序; 否则显示失败消息
     */
    public void afterLogin(Message message) {
        loginWindow.getUi().pb_Login.setText("登录");
        loginWindow.getUi().pb_Login.setEnabled(true);

        if (message.getSucceed()) {
            saveLoginData();
            int closeMills = 15000; //表示过多久自动关闭程序
            loginCount = 0; //连续登录次数置0

            Runnable closeTask = new Runnable() {
                @Override
                public void run() {
                    if (loginWindow.isHidden()) {
                        loginWindow.close();
                    } else {
                        loginWindow.showMessage("自动关闭已取消", "");
                    }
                }
            };
            //closeSeconds后关闭程序
            QApplication.invokeLater(closeMills, closeTask);
            loginWindow.hide();
            loginWindow.showMessage(message.getTitle(), "程序将在" + closeMills / 1000 + "后关闭...");

        } else {
            if (loginCount >= maxLoginCount) {
                //连续maxLoginCount次登录失败, 就停止登录
                loginWindow.showMessage(message.getTitle(), message.getText());
                loginCount = 0;
            } else {
                //继续尝试登录
                login();
            }
        }

    }

    public void afterLogout(Message message) {
        loginWindow.getUi().pb_logout.setText("注销");
        loginWindow.getUi().pb_logout.setEnabled(true);
        loginWindow.showMessage(message.getTitle(), message.getText());
    }

    /**
     * 显示窗口, 若开启了自动登录, 则进行登录操作
     */
    public void firstShowWindow() {
        loginWindow.show();
        if (loginWindow.getUi().cb_autoLogin.isChecked())
            login();
    }

}
