package com.model.bean;

import java.io.Serializable;

/**
 * @author HRH
 * @version 1.0
 * @date 2020/5/23 18:10
 */
public class Login implements Serializable{
    private static final long serialVersionUID = 5809782578272943999L;
    private String id;
    private String password;
    private String isp;

    public static final String BINJIANG_COLLEGE = "";
    public static final String CHINA_MOBILE = "@cmcc";
    public static final String CHINA_UNICOM = "@unicom";
    public static final String CHINA_TELECOM = "@telecom";

    public Login() {
    }

    public Login(String id, String password, String isp) {
        this.id = id;
        this.password = password;
        this.isp = isp;
    }

    @Override
    public String toString() {
        return "Login{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", isp='" + isp + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }
}
