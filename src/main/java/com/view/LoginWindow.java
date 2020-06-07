package com.view;

import com.trolltech.qt.core.QRegExp;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.gui.*;

/**
 * @author HRH
 * @version 1.0
 * @date 2020/5/24 21:11
 */
public class LoginWindow extends QMainWindow {
    private Ui_LoginWindow ui;

    //设置托盘样式
    private QSystemTrayIcon systemTrayIcon = new QSystemTrayIcon(this);
    private QListWidget listWidget;

    //    /**
//     * 设置背景透明度
//     */
//    @Override
//    protected void paintEvent(QPaintEvent arg__1) {
//        QPainter painter = new QPainter(this);
//        //透明度10
//        painter.fillRect(this.rect(), new QColor(0, 0, 0, 10));
//    }

    public LoginWindow() {
        ui = new Ui_LoginWindow();
        ui.setupUi(this);

        //设置窗口背景
//        setStyleSheet("com.view.LoginWindow{border-image: url(classpath:images/background.png);}");
        QPalette palette = palette();
        String backgroundPath = System.getProperty("user.dir") + "\\images\\background.png";
        palette.setBrush(QPalette.ColorRole.Window, new QBrush(new QPixmap(backgroundPath)));
        this.setPalette(palette);
        this.setAutoFillBackground(true);

        systemTrayIcon.setIcon(new QIcon("images/login.png"));
        systemTrayIcon.setToolTip("自动登录校园网");
        systemTrayIcon.activated.connect(this, "activated_on_systemTrayIcon(QSystemTrayIcon$ActivationReason)");
        //设置托盘右键菜单
        QMenu menu = new QMenu(this);
        QAction displayAction = new QAction("显示", this);
        displayAction.triggered.connect(this, "show()");
        menu.addAction(displayAction);
        QAction quitAction = new QAction("退出", this);
        Signal1<QSystemTrayIcon.ActivationReason> activationReasonSignal1 = new Signal1<>();
        quitAction.triggered.connect(this, "close()");
        menu.addAction(quitAction);
        systemTrayIcon.setContextMenu(menu);
        //显示托盘
        systemTrayIcon.show();

        initStyle();
        //设置图标
        setWindowIcon(new QIcon("images/login.png"));
        //禁止窗口最大化
        this.setWindowFlags(Qt.WindowType.WindowMinimizeButtonHint);
    }

    public void initStyle() {
        listWidget = new QListWidget(this);
        QItemDelegate delegate = new QItemDelegate(listWidget);
        ui.combo_id.setModel(listWidget.model());
        ui.combo_id.setView(listWidget);
        ui.combo_id.lineEdit().setPlaceholderText("账号");
        ui.combo_id.setValidator(new QRegExpValidator(new QRegExp("[0-9|A-Z|a-z]{1,16}")));

//        ui.le_password.setEchoMode(QLineEdit.EchoMode.Normal);
        ui.le_password.setPlaceholderText("密码");

        ui.lab_id.setPixmap(new QPixmap("images/id.png"));
        ui.lab_password.setPixmap(new QPixmap("images/password.png"));
        ui.lab_isp.setPixmap(new QPixmap("images/isp.png"));

    }

    /**
     * 当点击托盘时显示窗口
     */
    public void activated_on_systemTrayIcon(QSystemTrayIcon.ActivationReason activationReason) {
        if (activationReason == QSystemTrayIcon.ActivationReason.Trigger) {
            show();
        }
    }

    public void showMessage(String title, String text) {
        systemTrayIcon.showMessage(title, text);
    }

    public Ui_LoginWindow getUi() {
        return ui;
    }

    public QSystemTrayIcon getSystemTrayIcon() {
        return systemTrayIcon;
    }

    public QListWidget getListWidget() {
        return listWidget;
    }
}
