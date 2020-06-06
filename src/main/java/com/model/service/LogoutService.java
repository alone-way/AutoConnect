package com.model.service;

import com.model.bean.Message;
import com.model.util.CampusNetWorkUtil;
import com.model.util.HttpUtil;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * @author HRH
 * @version 1.0
 * @date 2020/6/5 16:52
 */
public class LogoutService {
    public static final String logoutUrlFormat = "http://10.1.99.100:801/eportal/?c=ACSetting&a=Logout&wlanuserip" +
            "=%s&wlanacip=10.1.1.1&wlanacname=%%26%%23230%%3B%%26%%23187%%3B%%A1%%A7%%26%%23230%%3B%%A1%%C0%%26" +
            "%%23159%%3B%%26%%23229" +
            "%%3B%%26%%23173%%3B%%26%%23166%%3B%%A8%%A6%%26%%23153%%3B%%26%%23162%%3B%%26%%23230%%3B%%26%%23181%%3B" +
            "%%26%%23139%%3B%%A8%%A8%%26%%23175%%3B%%26%%23149%%3B&port=&hostname=10.1.99" +
            ".100&iTermType=1&session=&queryACIP=0&mac=00-00-00-00-00-00&jsVersion=2.4.3";

    /**
     * 注销已登录的校园网
     */
    public Message logout() {
        //检查是否处于断网时间
        if (!CampusNetWorkUtil.isNormalTime()) {
            return new Message(false, "注销失败!", "处于断网时间!");
        }

        //检查是否能ping通校园网服务器
        if (!CampusNetWorkUtil.isReachable("10.1.99.100")) {
            return new Message(false, "注销失败!", "未登录校园网");
        }

        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            String url = String.format(logoutUrlFormat, ip);

            //发送POST请求, 注销登录
            CloseableHttpResponse response = HttpUtil.doPost(url, CampusNetWorkUtil.getLogoutFormMap());
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == 200) {
                return new Message(true, "注销成功!", "状态码:" + statusCode);
            } else if (statusCode == 301 || statusCode == 302) {
                Header location = response.getFirstHeader("location");
                URL redirectUrl = new URL(location.getValue());
                //获得重定向URL的参数部分
                Map<String, String> urlParams = HttpUtil.getUrlParam(redirectUrl);
                int acLogOut = Integer.parseInt(urlParams.get("ACLogOut"));
                switch (acLogOut) {
                    case 1:
                        return new Message(true, "注销成功!", "ACLogOut:" + acLogOut);
                    default:
                        return new Message(false, "注销失败!", "ACLogOut:" + acLogOut);
                }

            } else {
                return new Message(false, "注销失败!", "状态码:" + statusCode);
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
            return new Message(false, "注销失败!", e.getMessage());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return new Message(false, "注销失败!", e.getMessage());
        }
    }
}
