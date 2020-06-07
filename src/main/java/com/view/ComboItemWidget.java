package com.view;

import com.model.bean.Login;
import com.trolltech.qt.gui.*;

/**
 * @author HRH
 * @version 1.0
 * @date 2020/6/7 13:39
 */
public class ComboItemWidget extends QWidget {
    private QLabel lab_text = new QLabel(this);
    private QPushButton pb_delete = new QPushButton(this);
    private Login login = new Login("", "", "");

    public ComboItemWidget(QWidget parent) {
        super(parent);

        //设置按钮样式
        pb_delete.setIcon(new QIcon("images/delete.png"));
        pb_delete.setFixedSize(new QPixmap("images/delete.png").size());
        pb_delete.setFlat(true);
//        pb_image.setStyleSheet("background:transparent;");

        QHBoxLayout hLayout = new QHBoxLayout(this);
        hLayout.addWidget(lab_text);
        hLayout.addStretch();
        hLayout.addWidget(pb_delete);

        hLayout.setSpacing(5);
        hLayout.setContentsMargins(5, 5, 5, 5);

        this.setLayout(hLayout);
    }

    public ComboItemWidget(Login login, QWidget parent) {
        this(parent);
        lab_text.setText(login.getId());
        this.login = login;
    }

    public void setText(String text) {
        lab_text.setText(text);
        login.setId(text);
    }

    public String getText() {
        return lab_text.text();
    }

    public Login getLogin() {
        return login;
    }

    public ComboItemWidget setLogin(Login login) {
        this.login = login;
        lab_text.setText(login.getId());
        return this;
    }

    public QPushButton getPb_delete() {
        return pb_delete;
    }
}
