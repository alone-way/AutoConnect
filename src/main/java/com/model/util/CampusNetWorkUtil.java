package com.model.util;

import com.model.bean.Login;
import com.model.bean.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author HRH
 * @version 1.0
 * @date 2020/6/5 17:01
 */
public class CampusNetWorkUtil {
    /**
     * 检查是否处于正常可联网时间
     */
    public static boolean isNormalTime() {
        Calendar instance = Calendar.getInstance();
        boolean isNormalTime = true;
        if (instance.get(Calendar.HOUR_OF_DAY) < 7) {
            isNormalTime = false;
        }
        if ((instance.get(Calendar.DAY_OF_WEEK) < 6)) {
            if (instance.get(Calendar.HOUR_OF_DAY) >= 23) {
                isNormalTime = false;
            }
        } else {
            if ((instance.get(Calendar.HOUR_OF_DAY) >= 23 && instance.get(Calendar.MINUTE) >= 30)) {
                isNormalTime = false;
            }
        }
        return isNormalTime;
    }

    /**
     * 判断目标主机是否可达
     */
    public static boolean isReachable(String ip) {
//        try {
//            Process proPing = Runtime.getRuntime().exec("cmd /c ping " + ip);
//            BufferedReader reader = new BufferedReader(new InputStreamReader(proPing.getInputStream(), "GBk"));
//            String line = "";
//            while ((line = reader.readLine()) != null) {
////                System.out.println(line);
//                if (line.indexOf("TTL") != -1) {
//                    //可以ping通
//                    return true;
//                } else if (line.indexOf("请求超时") != -1 || line.indexOf("无法访问目标主机") != -1 || line.indexOf("一般故障") != -1) {
//                    //为了快速获得结果, 遇到这种情况直接返回false
//                    return false;
//                }
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return false;


//        用下面的方法有时会报SocketException, 暂不知啥原因
        try {
            InetAddress address = InetAddress.getByName(ip);
            return address.isReachable(500);

        } catch (UnknownHostException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获得用于登录的表单映射
     */
    public static Map<String, String> getLoginFormMap(Login login) {
        Map<String, String> params = new HashMap<>();

        try {
            params.put("DDDDD", URLEncoder.encode(String.format(",0,%s%s", login.getId(), login.getIsp()), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        params.put("upass", login.getPassword());
        params.put("R1", "0");
        params.put("R2", "0");
        params.put("R3", "0");
        params.put("R6", "0");
        params.put("para", "00");
        params.put("0MKKey", "123456");
        params.put("buttonClicked", null);
        params.put("redirect_url", null);
        params.put("err_flag", null);
        params.put("username", null);
        params.put("password", null);
        params.put("user", null);
        params.put("Login", null);
        params.put("v6ip", null);

        return params;
    }

//    /**
//     * 获得用于注销的表单映射
//     */
//    public static Map<String, String> getLogoutFormMap() {
//        Map<String, String> params = new HashMap<>();
//        return params;

//        params.put("c", "ACSetting");
//        params.put("a", "Logout");
//        try {
//            params.put("wlanuserip", InetAddress.getLocalHost().getHostAddress());
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//        params.put("wlanacip", "10.1.1.1");
//        params.put("wlanacname", "%26%23230%3B%26%23187%3B%A1%A7%26%23230%3B%A1%C0%26%23159%3B%26%23229%3B-|%A8%A6%26" +
//                "%23153%3B%A1%E9%26%23230%3B%A6%CC%26%23139%3B%A8%A8%A1%A5%26%23149%3B");
//        params.put("port", null);
//        params.put("hostname", "10.1.99.100");
//        params.put("iTermType", "1");
//        params.put("session", null);
//        params.put("queryACIP", "0");
//        params.put("mac", "00-00-00-00-00-00");
//        params.put("jsVersion", "2.4.3");

//        return params;
//    }

    /**
     * 连接指定的wifi(若已连接, 则重新连接)
     *
     * @return 连接成功返回true，否则返回false
     */
    public static Message connectWifi(String wifiName, String wifiConfigPath) {
        String cmdCheckWifi = "cmd /c netsh wlan show interfaces";
        String cmdAddWifi = String.format("cmd /c netsh wlan add profile filename = \"%s\"", wifiConfigPath);
        String cmdConnectWifi = String.format("cmd /c netsh wlan connect name=\"%s\"", wifiName);

        try {
//            //检查该wifiName是否已连接, 若已连接直接返回true
//            Process proCheckWifi = Runtime.getRuntime().exec(cmdCheckWifi);
//            try (BufferedReader reader = new BufferedReader(new InputStreamReader(proCheckWifi.getInputStream(), "UTF" +
//                    "-8"));) {
//                String oneLine = "";
//                while ((oneLine = reader.readLine()) != null) {
//                    oneLine = oneLine.trim();
//                    if (oneLine.startsWith("SSID")) {
//                        String SSID = oneLine.split(" +")[2];
////                        System.out.println(wifiName);
//                        if (SSID.equals(wifiName)) {
////                            System.out.println("wifi已连接");
//                            return new Message(true, "wifi连接成功!", "");
//                        }
//                    }
//                }
//            }
//            System.out.println("wifi未连接, 尝试连接wifi...");

            //将wifi配置文件增加到系统
            Process proAddWifi = Runtime.getRuntime().exec(cmdAddWifi);
            proAddWifi.waitFor(1, TimeUnit.SECONDS);

            //连接wifi
            Process proConnectWifi = Runtime.getRuntime().exec(cmdConnectWifi);

            //获得执行结果
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(proConnectWifi.getInputStream(),
                    System.getProperty("sun.jnu.encoding")))) {
                String line;
                StringBuilder totalLine = new StringBuilder();
                while ((line = reader.readLine()) != null) {
//                    System.out.println(line);
                    totalLine.append(line + "\n");
                }

                //根据执行结果返回消息
                if (totalLine.indexOf("已成功完成连接请求") != -1) {
                    return new Message(true, totalLine.toString(), "");
                } else {
                    return new Message(false, totalLine.toString(), "");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            return new Message(false, e.getMessage(), "");
        } catch (InterruptedException e) {
            e.printStackTrace();
            return new Message(false, e.getMessage(), "");
        }
    }

    /**
     * 获得无线网卡上的ip (避免获得到本机回环ip和虚拟机ip)
     */
    public static String getIp() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                List<InterfaceAddress> ips = networkInterface.getInterfaceAddresses();
                for (InterfaceAddress ip : ips) {
                    if (ip.getNetworkPrefixLength() == 24 && !ip.getAddress().isLoopbackAddress()) {
                        String ipStr = ip.getAddress().getHostAddress();
                        if (ipStr.startsWith("10.")) {
                            return ipStr;
                        }
                    }
                }
            }
            throw new RuntimeException("没有找到符合格式的ip");

        } catch (SocketException e) {
            e.printStackTrace();
        }

        return "";
    }
}
