package com.view; /********************************************************************************
 ** Form generated from reading ui file 'LoginWindow.jui'
 **
 ** Created by: Qt User Interface Compiler version 4.8.7
 **
 ** WARNING! All changes made in this file will be lost when recompiling ui file!
 ********************************************************************************/
import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_LoginWindow implements com.trolltech.qt.QUiForm<QMainWindow>
{
    public QWidget centralwidget;
    public QVBoxLayout verticalLayout;
    public QSpacerItem verticalSpacer_2;
    public QHBoxLayout horizontalLayout_4;
    public QLabel lab_id;
    public QComboBox combo_id;
    public QHBoxLayout horizontalLayout_3;
    public QLabel lab_password;
    public QLineEdit le_password;
    public QHBoxLayout horizontalLayout_5;
    public QLabel lab_isp;
    public QComboBox combo_isp;
    public QHBoxLayout horizontalLayout;
    public QSpacerItem horizontalSpacer_4;
    public QCheckBox cb_autoLogin;
    public QPushButton pb_Login;
    public QPushButton pb_logout;
    public QSpacerItem verticalSpacer;
    public QHBoxLayout horizontalLayout_2;
    public QSpacerItem horizontalSpacer;
    public QCheckBox cb_autoStart;
    public QStatusBar statusbar;
    public QMenuBar menubar;

    public Ui_LoginWindow() { super(); }

    public void setupUi(QMainWindow LoginWindow)
    {
        LoginWindow.setObjectName("LoginWindow");
        LoginWindow.resize(new QSize(370, 470).expandedTo(LoginWindow.minimumSizeHint()));
        LoginWindow.setMinimumSize(new QSize(360, 460));
        QFont font = new QFont();
        font.setFamily("\u5fae\u8f6f\u96c5\u9ed1");
        font.setPointSize(10);
        LoginWindow.setFont(font);
        centralwidget = new QWidget(LoginWindow);
        centralwidget.setObjectName("centralwidget");
        verticalLayout = new QVBoxLayout(centralwidget);
        verticalLayout.setObjectName("verticalLayout");
        verticalSpacer_2 = new QSpacerItem(20, 46, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);

        verticalLayout.addItem(verticalSpacer_2);

        horizontalLayout_4 = new QHBoxLayout();
        horizontalLayout_4.setObjectName("horizontalLayout_4");
        lab_id = new QLabel(centralwidget);
        lab_id.setObjectName("lab_id");

        horizontalLayout_4.addWidget(lab_id);

        combo_id = new QComboBox(centralwidget);
        combo_id.setObjectName("combo_id");
        QSizePolicy sizePolicy = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);
        sizePolicy.setHorizontalStretch((byte)0);
        sizePolicy.setVerticalStretch((byte)0);
        sizePolicy.setHeightForWidth(combo_id.sizePolicy().hasHeightForWidth());
        combo_id.setSizePolicy(sizePolicy);
        QFont font1 = new QFont();
        font1.setPointSize(10);
        combo_id.setFont(font1);
        combo_id.setEditable(true);

        horizontalLayout_4.addWidget(combo_id);


        verticalLayout.addLayout(horizontalLayout_4);

        horizontalLayout_3 = new QHBoxLayout();
        horizontalLayout_3.setObjectName("horizontalLayout_3");
        lab_password = new QLabel(centralwidget);
        lab_password.setObjectName("lab_password");

        horizontalLayout_3.addWidget(lab_password);

        le_password = new QLineEdit(centralwidget);
        le_password.setObjectName("le_password");
        QSizePolicy sizePolicy1 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);
        sizePolicy1.setHorizontalStretch((byte)0);
        sizePolicy1.setVerticalStretch((byte)0);
        sizePolicy1.setHeightForWidth(le_password.sizePolicy().hasHeightForWidth());
        le_password.setSizePolicy(sizePolicy1);
        le_password.setMaxLength(16);
        le_password.setEchoMode(com.trolltech.qt.gui.QLineEdit.EchoMode.PasswordEchoOnEdit);

        horizontalLayout_3.addWidget(le_password);


        verticalLayout.addLayout(horizontalLayout_3);

        horizontalLayout_5 = new QHBoxLayout();
        horizontalLayout_5.setObjectName("horizontalLayout_5");
        lab_isp = new QLabel(centralwidget);
        lab_isp.setObjectName("lab_isp");

        horizontalLayout_5.addWidget(lab_isp);

        combo_isp = new QComboBox(centralwidget);
        combo_isp.setObjectName("combo_isp");
        QSizePolicy sizePolicy2 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);
        sizePolicy2.setHorizontalStretch((byte)0);
        sizePolicy2.setVerticalStretch((byte)0);
        sizePolicy2.setHeightForWidth(combo_isp.sizePolicy().hasHeightForWidth());
        combo_isp.setSizePolicy(sizePolicy2);
        combo_isp.setMinimumSize(new QSize(141, 30));
        combo_isp.setStyleSheet("QComboBox QAbstractItemView {\n"+
"outline: 0px;\n"+
"}\n"+
"\n"+
"QComboBox QAbstractItemView::item\n"+
"{\n"+
"    min-height:30px;\n"+
"    min-width:10px;    \n"+
"}\n"+
"");

        horizontalLayout_5.addWidget(combo_isp);


        verticalLayout.addLayout(horizontalLayout_5);

        horizontalLayout = new QHBoxLayout();
        horizontalLayout.setObjectName("horizontalLayout");
        horizontalLayout.setContentsMargins(-1, 5, -1, 5);
        horizontalSpacer_4 = new QSpacerItem(40, 20, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum);

        horizontalLayout.addItem(horizontalSpacer_4);

        cb_autoLogin = new QCheckBox(centralwidget);
        cb_autoLogin.setObjectName("cb_autoLogin");
        QSizePolicy sizePolicy3 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Fixed, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);
        sizePolicy3.setHorizontalStretch((byte)0);
        sizePolicy3.setVerticalStretch((byte)0);
        sizePolicy3.setHeightForWidth(cb_autoLogin.sizePolicy().hasHeightForWidth());
        cb_autoLogin.setSizePolicy(sizePolicy3);

        horizontalLayout.addWidget(cb_autoLogin);


        verticalLayout.addLayout(horizontalLayout);

        pb_Login = new QPushButton(centralwidget);
        pb_Login.setObjectName("pb_Login");
        QSizePolicy sizePolicy4 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);
        sizePolicy4.setHorizontalStretch((byte)0);
        sizePolicy4.setVerticalStretch((byte)0);
        sizePolicy4.setHeightForWidth(pb_Login.sizePolicy().hasHeightForWidth());
        pb_Login.setSizePolicy(sizePolicy4);
        pb_Login.setMinimumSize(new QSize(0, 30));

        verticalLayout.addWidget(pb_Login);

        pb_logout = new QPushButton(centralwidget);
        pb_logout.setObjectName("pb_logout");
        QSizePolicy sizePolicy5 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);
        sizePolicy5.setHorizontalStretch((byte)0);
        sizePolicy5.setVerticalStretch((byte)0);
        sizePolicy5.setHeightForWidth(pb_logout.sizePolicy().hasHeightForWidth());
        pb_logout.setSizePolicy(sizePolicy5);

        verticalLayout.addWidget(pb_logout);

        verticalSpacer = new QSpacerItem(20, 62, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);

        verticalLayout.addItem(verticalSpacer);

        horizontalLayout_2 = new QHBoxLayout();
        horizontalLayout_2.setObjectName("horizontalLayout_2");
        horizontalSpacer = new QSpacerItem(40, 20, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum);

        horizontalLayout_2.addItem(horizontalSpacer);

        cb_autoStart = new QCheckBox(centralwidget);
        cb_autoStart.setObjectName("cb_autoStart");
        QSizePolicy sizePolicy6 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Fixed, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);
        sizePolicy6.setHorizontalStretch((byte)0);
        sizePolicy6.setVerticalStretch((byte)0);
        sizePolicy6.setHeightForWidth(cb_autoStart.sizePolicy().hasHeightForWidth());
        cb_autoStart.setSizePolicy(sizePolicy6);

        horizontalLayout_2.addWidget(cb_autoStart);


        verticalLayout.addLayout(horizontalLayout_2);

        LoginWindow.setCentralWidget(centralwidget);
        statusbar = new QStatusBar(LoginWindow);
        statusbar.setObjectName("statusbar");
        statusbar.setSizeGripEnabled(false);
        LoginWindow.setStatusBar(statusbar);
        menubar = new QMenuBar(LoginWindow);
        menubar.setObjectName("menubar");
        menubar.setGeometry(new QRect(0, 0, 370, 26));
        LoginWindow.setMenuBar(menubar);
        retranslateUi(LoginWindow);
        le_password.returnPressed.connect(pb_Login, "click()");

        LoginWindow.connectSlotsByName();
    } // setupUi

    void retranslateUi(QMainWindow LoginWindow)
    {
        LoginWindow.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("LoginWindow", "\u6821\u56ed\u7f51\u81ea\u52a8\u767b\u5f55\u5668", null));
        lab_id.setText("");
        lab_password.setText("");
        le_password.setPlaceholderText(com.trolltech.qt.core.QCoreApplication.translate("LoginWindow", "\u5bc6\u7801", null));
        lab_isp.setText("");
        combo_isp.clear();
        combo_isp.addItem(com.trolltech.qt.core.QCoreApplication.translate("LoginWindow", "\u8fd0\u8425\u5546", null));
        combo_isp.addItem(com.trolltech.qt.core.QCoreApplication.translate("LoginWindow", "\u4e2d\u56fd\u7535\u4fe1", null));
        combo_isp.addItem(com.trolltech.qt.core.QCoreApplication.translate("LoginWindow", "\u4e2d\u56fd\u79fb\u52a8", null));
        combo_isp.addItem(com.trolltech.qt.core.QCoreApplication.translate("LoginWindow", "\u4e2d\u56fd\u8054\u901a", null));
        combo_isp.addItem(com.trolltech.qt.core.QCoreApplication.translate("LoginWindow", "\u6ee8\u6c5f\u5b66\u9662", null));
        cb_autoLogin.setText(com.trolltech.qt.core.QCoreApplication.translate("LoginWindow", "\u81ea\u52a8\u767b\u5f55", null));
        pb_Login.setText(com.trolltech.qt.core.QCoreApplication.translate("LoginWindow", "\u767b\u5f55", null));
        pb_logout.setText(com.trolltech.qt.core.QCoreApplication.translate("LoginWindow", "\u6ce8\u9500", null));
        cb_autoStart.setText(com.trolltech.qt.core.QCoreApplication.translate("LoginWindow", "\u5f00\u673a\u81ea\u542f", null));
    } // retranslateUi

}

