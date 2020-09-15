package com.model.service;


import com.model.bean.Login;
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
 * @date 2020/5/23 18:08
 */
public class LoginService {
    //登录校园网的url格式
    public String loginUrlFormat = "http://10.1.99.100:801/eportal/?c=ACSetting&a=Login&protocol=http:&hostname=10.1" +
            ".99.100&iTermType=1&wlanuserip=%s&wlanacip=10.1.1" +
            ".1&wlanacname=null&mac=00-00-00-00-00-00&ip=%s&enAdvert=0&queryACIP=0&jsVersion=2.4.3&loginMethod=1";

    /**
     * 登录校园网
     *
     * @param login 登录账户
     */
    public Message login(Login login) {
        //检查账号密码的非空性
        if (login.getId().trim().isEmpty() || login.getPassword().trim().isEmpty()) {
            return new Message(false, "登录失败!", "请输入账号或密码!");
        }

        //检查是否处于断网时间
        if (!CampusNetWorkUtil.isNormalTime()) {
            return new Message(false, "登录失败!", "处于断网时间!");
        }

        //若无法ping通校园网服务器, 说明未连接校园wifi
        if (!CampusNetWorkUtil.isReachable("10.1.99.100")) {
            //连接校园wifi
            Message wifiMessage = CampusNetWorkUtil.connectWifi("i-bjxy", "WLAN-i-bjxy.xml");

            //若连接失败, 直接返回错误消息
            if (!wifiMessage.getSucceed())
                return wifiMessage;

            int count = 0;
            int maxCount = 5;
            //刚连上wifi要等一会儿才能ping通服务器10.1.99.100
            while (count < maxCount && !CampusNetWorkUtil.isReachable("10.1.99.100")) {
                count++;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //若多次ping服务器失败, 直接返回错误消息
            if (count >= maxCount) {
                return new Message(false, "登录失败!", "校园wifi已连接但无法ping通服务器!");

            }
        }

        try {
            String ip = CampusNetWorkUtil.getIp();
//            System.out.println(ip);
            URL url = new URL(String.format(loginUrlFormat, ip, ip));

            Map<String, String> loginFormMap = CampusNetWorkUtil.getLoginFormMap(login);

            //发送POST请求, 登录校园网
            CloseableHttpResponse response = HttpUtil.doPost(url.toString(), loginFormMap);
            if(response == null){
                 return new Message(false, "登录失败!", "POST出现异常");
            }

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                return new Message(true, "登录成功!", "状态码: " + statusCode);

            } else if (statusCode == 301 || statusCode == 302) {
                //获得重定向的URL
                Header location = response.getFirstHeader("location");
//                System.out.println(location.getValue());
                //获得URL的所有参数
                Map<String, String> param = HttpUtil.getUrlParam(new URL(location.getValue()));
                //获得参数RetCode的值
                String retCode = param.get("RetCode");

                //根据RetCode值确定登录结果
                if (retCode == null) {
                    return new Message(true, "登录成功!", "");
                }
                switch (Integer.parseInt(retCode)) {
                    case 2:
                        return new Message(false, "终端ip已在线", "");
                    case 0:
                        return new Message(false, "AC认证失败", "");
                    case 1:
                        return new Message(false, "登录失败!", "请检查网络配置及确认账号及密码是否正确!");
                    case 3:
                        return new Message(false, "登录失败!", "系统繁忙，请稍后再试");
                    case 4:
                        return new Message(false, "登录失败!", "发生未知错误，请稍后再试");
                    case 5:
                        return new Message(false, "登录失败!", "REQ_CHALLENGE失败，请联系AC确认");
                    case 6:
                        return new Message(false, "登录失败!", "REQ_CHALLENGE超时，请联系AC确认");
                    case 7:
                        return new Message(false, "登录失败!", "Radius认证失败");
                    case 8:
                        return new Message(false, "登录失败!", "Radius认证超时");
                    case 9:
                        return new Message(false, "登录失败!", "Radius下线失败");
                    case 10:
                        return new Message(false, "登录失败!", "Radius下线超时");
                    case 11:
                        return new Message(false, "登录失败!", "发生其他错误，请稍后再试");
                    default:
                        return new Message(false, "登录失败!", "AC认证失败");
                }

            } else {
                return new Message(true, "登录失败!", "状态码: " + statusCode);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return new Message(false, "登录失败!", e.getMessage());
        }

    }
}

