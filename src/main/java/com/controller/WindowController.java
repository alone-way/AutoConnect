package com.controller;


import com.model.bean.Login;
import com.model.bean.Message;
import com.model.service.LoginService;
import com.model.service.LoginTask;
import com.model.service.LogoutService;
import com.model.service.LogoutTask;
import com.model.util.SystemUtil;
import com.trolltech.qt.core.QObject;
import com.trolltech.qt.gui.*;
import com.view.ComboItemWidget;
import com.view.LoginWindow;
import com.view.Ui_LoginWindow;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HRH
 * @version 1.0
 * @date 2020/5/24 21:19
 */
public class WindowController extends QObject {
    private LoginWindow loginWindow = new LoginWindow();
    private LoginService loginService = new LoginService();
    private LogoutService logoutService = new LogoutService();
    private int loginCount = 0; //连续登录次数

    public WindowController() {
        Ui_LoginWindow ui = loginWindow.getUi();
        //加载登录信息
        loadLoginData();
//        ui.combo_password.lineEdit().returnPressed.connect(ui.pb_Login, "click()");
        ui.pb_Login.clicked.connect(this, "login()");
        ui.pb_logout.clicked.connect(this, "logout()");
        ui.cb_autoStart.toggled.connect(this, "toggled_on_cb_autoStart(boolean)");
//        ui.combo_id.currentIndexChanged.connect(this, "currentIndexChanged_on_combo_id(int)");
        ui.combo_id.activatedIndex.connect(this, "activatedIndex_on_combo_id(int)");
        QListWidget listWidget = loginWindow.getListWidget();

    }

    /**
     * 根据选择的索引 显示 账户/密码/运营商
     */
    public void activatedIndex_on_combo_id(int index) {
        Ui_LoginWindow ui = loginWindow.getUi();
        QListWidget listWidget = loginWindow.getListWidget();

        //获得widget
        QListWidgetItem item = listWidget.item(index);
        ComboItemWidget widget = (ComboItemWidget) listWidget.itemWidget(item);
        //设置账户/密码/运营商
        ui.combo_id.setEditText(widget.getText());
        ui.le_password.setText(widget.getLogin().getPassword());
        ui.combo_isp.setCurrentIndex(widget.getLogin().getIspIndex());
    }

    /**
     * 加载登录信息
     */
    public void loadLoginData() {
        Ui_LoginWindow ui = loginWindow.getUi();
        QListWidget listWidget = loginWindow.getListWidget();
        try (ObjectInputStream in =
                     new ObjectInputStream(new BufferedInputStream(new FileInputStream("loginData.txt")))) {
            //设置窗口信息
            ui.cb_autoLogin.setChecked(in.readBoolean());
            ui.cb_autoStart.setChecked(in.readBoolean());

            boolean isFirst = true;
            //读取登录账户信息
            List<?> loginList;
            Object obj = in.readObject();
            if (obj instanceof List<?>) {
                loginList = (List<?>) obj;
                for (Object o : loginList) {
                    Login login = (Login) o;
                    QListWidgetItem item = new QListWidgetItem(listWidget);
                    ComboItemWidget widget = new ComboItemWidget(login, listWidget);
                    listWidget.setItemWidget(item, widget);
                    widget.getPb_delete().clicked.connect(this, "clicked_on_pb_delete()");
                    //将读取到的第一个账户的信息显示到窗口上
                    if (isFirst) {
                        ui.combo_id.setEditText(login.getId());
                        ui.le_password.setText(login.getPassword());
                        ui.combo_isp.setCurrentIndex(login.getIspIndex());
                        isFirst = false;
                    }
                }
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
        QListWidget listWidget = loginWindow.getListWidget();

        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(
                "loginData.txt")))) {
            //保存窗口信息
            out.writeBoolean(ui.cb_autoLogin.isChecked());
            out.writeBoolean(ui.cb_autoStart.isChecked());

            if (!ui.combo_id.currentText().isEmpty() && !ui.le_password.text().isEmpty()) {
                //将最近一次登录的账户移到listWidget的第0行
                String curId = ui.combo_id.currentText();
                boolean isFind = false;
                for (int i = 0; i < listWidget.count() && !isFind; i++) {
                    QListWidgetItem curItem = listWidget.item(i);
                    ComboItemWidget curWidget = (ComboItemWidget) listWidget.itemWidget(curItem);
                    Login curLogin = curWidget.getLogin();

                    if (curLogin.getId().equals(curId)) {
                        curLogin.setPassword(ui.le_password.text());
                        curLogin.setIspByIndex(ui.combo_isp.currentIndex());
                        //交换curWidget和firstWidget的login
                        ComboItemWidget firstWidget = (ComboItemWidget) listWidget.itemWidget(listWidget.item(0));
                        Login tmp = firstWidget.getLogin();
                        firstWidget.setLogin(curLogin);
                        curWidget.setLogin(tmp);
                        isFind = true;
                    }
                }
                //若最近一次登录的账户并不存在于listWidget中
                //说明是新账户, 将新账户插入到listWidget的第0行
                if (!isFind) {
                    Login login = new Login();
                    login.setId(ui.combo_id.currentText());
                    login.setPassword(ui.le_password.text());
                    login.setIspByIndex(ui.combo_isp.currentIndex());
                    QListWidgetItem item = new QListWidgetItem(listWidget);
                    ComboItemWidget widget = new ComboItemWidget(login, loginWindow);
                    widget.getPb_delete().clicked.connect(this, "clicked_on_pb_delete()");

                    //先将新账户插入最后一行
                    listWidget.setItemWidget(item, widget);
                    ComboItemWidget firstWidget = (ComboItemWidget) listWidget.itemWidget(listWidget.item(0));
                    ComboItemWidget lastWidget =
                            (ComboItemWidget) listWidget.itemWidget(listWidget.item(listWidget.count() - 1));
                    //交换第0行和最后一行的login
                    Login tmp = firstWidget.getLogin();
                    firstWidget.setLogin(lastWidget.getLogin());
                    lastWidget.setLogin(tmp);

                    ui.combo_id.setEditText(firstWidget.getText());
                }
            }

            //按顺序保存账号密码
            List<Login> loginList = new ArrayList<>();
            for (int i = 0; i < listWidget.count(); i++) {
                QListWidgetItem item = listWidget.item(i);
                ComboItemWidget widget = (ComboItemWidget) listWidget.itemWidget(item);
                Login login = widget.getLogin();
                loginList.add(login);
            }
            out.writeObject(loginList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据被点击的按钮来删除listWidget中指定的item, 并清空密码
     */
    public void clicked_on_pb_delete() {
        Ui_LoginWindow ui = loginWindow.getUi();
        QListWidget listWidget = loginWindow.getListWidget();

        QPushButton pb_delete = (QPushButton) signalSender();
        ComboItemWidget curWidget = (ComboItemWidget) pb_delete.parent();

        //在listWidget中找到curWidget, 并删除对应的item
        for (int i = 0; i < listWidget.count(); i++) {
            QListWidgetItem item = listWidget.item(i);
            QWidget findWidget = listWidget.itemWidget(item);
            if (curWidget == findWidget) {
                listWidget.takeItem(i);
                ui.le_password.clear();
                ui.combo_isp.setCurrentIndex(0);
            }
        }

        saveLoginData();
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
        if (ui.combo_isp.currentIndex() == 0) {
            loginWindow.showMessage("请选择运营商!", "");
            return;
        }
        ui.pb_Login.setText("登录中...");
        ui.pb_Login.setEnabled(false);

        String id = ui.combo_id.currentText();
        String password = ui.le_password.text();
        int ispIndex = ui.combo_isp.currentIndex();
        Login login = new Login();
        login.setId(id);
        login.setPassword(password);
        login.setIspByIndex(ispIndex);

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

            Runnable closeTask = () -> {
                if (loginWindow.isHidden()) {
                    loginWindow.close();
                } else {
                    loginWindow.showMessage("自动关闭已取消", "");
                }
            };
            //closeSeconds后关闭程序
            QApplication.invokeLater(closeMills, closeTask);
            loginWindow.hide();
            loginWindow.showMessage(message.getTitle(), "程序将在" + closeMills / 1000 + "后关闭...");

        } else {
            //最大连续登录次数, 超过该次数就不再尝试自动登录
            int maxLoginCount = 3;
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
