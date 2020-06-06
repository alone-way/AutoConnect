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
    private Ui_LoginWindow ui = new Ui_LoginWindow();
    private QSystemTrayIcon systemTrayIcon = new QSystemTrayIcon();

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
        ui.setupUi(this);

        //设置窗口背景
//        setStyleSheet("com.view.LoginWindow{border-image: url(classpath:images/background.png);}");
        QPalette palette = palette();
        String backgroundPath = System.getProperty("user.dir") + "\\images\\background.png";
        palette.setBrush(QPalette.ColorRole.Window, new QBrush(new QPixmap(backgroundPath)));
        this.setPalette(palette);
        this.setAutoFillBackground(true);

        //设置托盘样式
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

        //设置账号样式
        ui.combo_id.lineEdit().setPlaceholderText("账号");
        ui.combo_id.setValidator(new QRegExpValidator(new QRegExp("[0-9]{0,16}"), ui.combo_id));
        ui.combo_id.setStyleSheet("QComboBox::drop-down{border-style: none;}"); //隐藏右边箭头
        //设置密码样式
        ui.combo_password.lineEdit().setPlaceholderText("密码");
        ui.combo_password.lineEdit().setEchoMode(QLineEdit.EchoMode.Password);
        ui.combo_password.lineEdit().setMaxLength(16);
        ui.combo_password.setStyleSheet("QComboBox::drop-down{border-style: none;}"); //隐藏右边箭头
        //禁止窗口最大化
        this.setWindowFlags(Qt.WindowType.WindowMinimizeButtonHint);

        //设置图标
        setWindowIcon(new QIcon("images/login.png"));

        ui.combo_id.addItem("");
        ui.combo_password.addItem("");
        ui.combo_id.editTextChanged.connect(this, "currentStringChanged_on_combo_id(String)");
        ui.combo_password.editTextChanged.connect(this, "currentStringChanged_on_combo_password(String)");
        ui.combo_id.currentIndexChanged.connect(ui.combo_isp, "setCurrentIndex(int)");
    }

    /**
     * 把正在编辑的账号文本放到第0行
     */
    public void currentStringChanged_on_combo_id(String text) {
        ui.combo_id.setItemText(0, text);
        ui.combo_id.setCurrentIndex(0);
    }

    /**
     * 把正在编辑的密码文本放到第0行
     */
    public void currentStringChanged_on_combo_password(String text) {
        ui.combo_password.setItemText(0, text);
        ui.combo_password.setCurrentIndex(0);
    }

//
//    /**
//     * 加载数据
//     */
//    public void loadLoginData(String loginDataPath) {
//        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(loginDataPath));) {
//            Properties pro = new Properties();
//            pro.load(in);
//
//            ui.combo_id.setEditText(pro.getProperty("id"));
//            ui.le_password.setText(pro.getProperty("password"));
//            ui.combo_isp.setCurrentIndex(Integer.parseInt(pro.getProperty("isp")));
//            ui.cb_autoLogin.setChecked(Boolean.parseBoolean(pro.getProperty("autoLogin")));
//            ui.cb_autoStart.setChecked(Boolean.parseBoolean(pro.getProperty("autoStart")));
//
//        } catch (
//                FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (
//                IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    /**
//     * 保存数据
//     */
//    public void saveLoginData(String loginDataPath) {
//        Properties pro = new Properties();
//        pro.setProperty("id", ui.combo_id.currentText());
//        pro.setProperty("password", ui.le_password.text());
//        pro.setProperty("isp", String.valueOf(ui.combo_isp.currentIndex()));
//        pro.setProperty("autoLogin", String.valueOf(ui.cb_autoLogin.isChecked()));
//        pro.setProperty("autoStart", String.valueOf(ui.cb_autoStart.isChecked()));
//
//        try (OutputStream out = new FileOutputStream(loginDataPath);) {
//            pro.save(out, LocalDateTime.now().toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    /**
//     * 将窗口隐藏到托盘
//     */
//    @Override
//    protected void closeEvent(QCloseEvent arg__1) {
//        arg__1.ignore();
//        //隐藏窗口
//        hide();
//        //显示托盘
//        systemTrayIcon.show();
//        //显示提示消息
//        systemTrayIcon.showMessage("隐藏到托盘", "");
//    }


    /**
     * 当点击托盘时显示窗口
     */
    public void activated_on_systemTrayIcon(QSystemTrayIcon.ActivationReason activationReason) {
        if (activationReason == QSystemTrayIcon.ActivationReason.Trigger)
            show();
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
}
