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
    public QVBoxLayout verticalLayout_2;
    public QSpacerItem verticalSpacer_2;
    public QVBoxLayout verticalLayout;
    public QComboBox combo_id;
    public QComboBox combo_password;
    public QComboBox combo_isp;
    public QHBoxLayout horizontalLayout;
    public QSpacerItem horizontalSpacer_4;
    public QCheckBox cb_autoLogin;
    public QSpacerItem verticalSpacer;
    public QPushButton pb_Login;
    public QPushButton pb_logout;
    public QSpacerItem verticalSpacer_3;
    public QHBoxLayout horizontalLayout_2;
    public QSpacerItem horizontalSpacer;
    public QCheckBox cb_autoStart;
    public QStatusBar statusbar;
    public QMenuBar menubar;

    public Ui_LoginWindow() { super(); }

    public void setupUi(QMainWindow LoginWindow)
    {
        LoginWindow.setObjectName("LoginWindow");
        LoginWindow.resize(new QSize(370, 480).expandedTo(LoginWindow.minimumSizeHint()));
        LoginWindow.setMinimumSize(new QSize(370, 480));
        LoginWindow.setMaximumSize(new QSize(370, 480));
        QFont font = new QFont();
        font.setFamily("\u5fae\u8f6f\u96c5\u9ed1");
        font.setPointSize(10);
        LoginWindow.setFont(font);
        centralwidget = new QWidget(LoginWindow);
        centralwidget.setObjectName("centralwidget");
        verticalLayout_2 = new QVBoxLayout(centralwidget);
        verticalLayout_2.setObjectName("verticalLayout_2");
        verticalSpacer_2 = new QSpacerItem(20, 46, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);

        verticalLayout_2.addItem(verticalSpacer_2);

        verticalLayout = new QVBoxLayout();
        verticalLayout.setSpacing(12);
        verticalLayout.setObjectName("verticalLayout");
        combo_id = new QComboBox(centralwidget);
        combo_id.setObjectName("combo_id");
        QSizePolicy sizePolicy = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy.setHorizontalStretch((byte)0);
        sizePolicy.setVerticalStretch((byte)0);
        sizePolicy.setHeightForWidth(combo_id.sizePolicy().hasHeightForWidth());
        combo_id.setSizePolicy(sizePolicy);
        combo_id.setMinimumSize(new QSize(0, 30));
        combo_id.setEditable(true);
        combo_id.setInsertPolicy(com.trolltech.qt.gui.QComboBox.InsertPolicy.InsertAtBottom);

        verticalLayout.addWidget(combo_id);

        combo_password = new QComboBox(centralwidget);
        combo_password.setObjectName("combo_password");
        combo_password.setEditable(true);

        verticalLayout.addWidget(combo_password);

        combo_isp = new QComboBox(centralwidget);
        combo_isp.setObjectName("combo_isp");
        QSizePolicy sizePolicy1 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy1.setHorizontalStretch((byte)0);
        sizePolicy1.setVerticalStretch((byte)0);
        sizePolicy1.setHeightForWidth(combo_isp.sizePolicy().hasHeightForWidth());
        combo_isp.setSizePolicy(sizePolicy1);
        combo_isp.setMinimumSize(new QSize(0, 30));

        verticalLayout.addWidget(combo_isp);

        horizontalLayout = new QHBoxLayout();
        horizontalLayout.setObjectName("horizontalLayout");
        horizontalLayout.setContentsMargins(-1, 5, -1, 5);
        horizontalSpacer_4 = new QSpacerItem(40, 20, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum);

        horizontalLayout.addItem(horizontalSpacer_4);

        cb_autoLogin = new QCheckBox(centralwidget);
        cb_autoLogin.setObjectName("cb_autoLogin");
        QSizePolicy sizePolicy2 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy2.setHorizontalStretch((byte)0);
        sizePolicy2.setVerticalStretch((byte)0);
        sizePolicy2.setHeightForWidth(cb_autoLogin.sizePolicy().hasHeightForWidth());
        cb_autoLogin.setSizePolicy(sizePolicy2);

        horizontalLayout.addWidget(cb_autoLogin);


        verticalLayout.addLayout(horizontalLayout);

        verticalSpacer = new QSpacerItem(327, 13, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);

        verticalLayout.addItem(verticalSpacer);

        pb_Login = new QPushButton(centralwidget);
        pb_Login.setObjectName("pb_Login");
        QSizePolicy sizePolicy3 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy3.setHorizontalStretch((byte)0);
        sizePolicy3.setVerticalStretch((byte)0);
        sizePolicy3.setHeightForWidth(pb_Login.sizePolicy().hasHeightForWidth());
        pb_Login.setSizePolicy(sizePolicy3);
        pb_Login.setMinimumSize(new QSize(0, 30));

        verticalLayout.addWidget(pb_Login);

        pb_logout = new QPushButton(centralwidget);
        pb_logout.setObjectName("pb_logout");

        verticalLayout.addWidget(pb_logout);


        verticalLayout_2.addLayout(verticalLayout);

        verticalSpacer_3 = new QSpacerItem(20, 46, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);

        verticalLayout_2.addItem(verticalSpacer_3);

        horizontalLayout_2 = new QHBoxLayout();
        horizontalLayout_2.setObjectName("horizontalLayout_2");
        horizontalSpacer = new QSpacerItem(40, 20, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum);

        horizontalLayout_2.addItem(horizontalSpacer);

        cb_autoStart = new QCheckBox(centralwidget);
        cb_autoStart.setObjectName("cb_autoStart");

        horizontalLayout_2.addWidget(cb_autoStart);


        verticalLayout_2.addLayout(horizontalLayout_2);

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

        combo_id.setCurrentIndex(-1);
        combo_password.setCurrentIndex(-1);


        LoginWindow.connectSlotsByName();
    } // setupUi

    void retranslateUi(QMainWindow LoginWindow)
    {
        LoginWindow.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("LoginWindow", "\u6ee8\u6c5f\u6821\u56ed\u7f51\u81ea\u52a8\u767b\u5f55\u5668", null));
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

