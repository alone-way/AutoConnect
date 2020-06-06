package com.model.util;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author HRH
 * @version 1.0
 * @date 2020/5/23 18:08
 */
public class HttpUtil {

    /**
     * 获得URL的参数部分
     */
    public static Map<String, String> getUrlParam(URL url) {
        Map<String, String> map = new HashMap<>();
        String urlStr = url.toString();
        int i = urlStr.indexOf('?');
        if (i == -1)
            return map;
        String[] params = urlStr.substring(i).split("&");
        for (String param : params) {
            //limit=-1可以避免参数无值时的越界异常
            String[] kv = param.split("=", -1);
            map.put(kv[0], kv[1]);
        }
        return map;
    }


    /**
     * 将表单映射转换成字符串形式
     */
    public static String getFormStr(Map<String, String> formMap) {
        boolean isFirst = true;
        StringBuilder paramString = new StringBuilder();
        for (Map.Entry<String, String> entry : formMap.entrySet()) {
            if (isFirst) {
                isFirst = false;
            } else {
                paramString.append('&');
            }
            paramString.append(entry.getKey());
            if (entry.getValue() != null) {
                paramString.append('=');
                paramString.append(entry.getValue());
            }
        }
        return paramString.toString();
    }

    /**
     * 发送POST请求
     *
     * @param url     要请求的url
     * @param formMap 要提交的表单映射
     * @return 响应信息
     */
    public static CloseableHttpResponse doPost(String url, Map<String, String> formMap) {
        try {
            //创建执行者
            CloseableHttpClient client = HttpClientBuilder.create().build();

            //构造请求头
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(2000)
                    .setSocketTimeout(2000)
                    .setConnectionRequestTimeout(2000)
                    .setRedirectsEnabled(false)
                    .setCookieSpec(CookieSpecs.IGNORE_COOKIES)
                    .build();

            //构造POST请求
            HttpPost post = new HttpPost(url);
            //设置请求头
            post.setConfig(requestConfig);
            post.setHeader("Content-Type", "application/x-www-form-urlencoded");

//            ArrayList<BasicNameValuePair> list = new ArrayList<>();
//            for (Map.Entry<String, String> entry : formMap.entrySet()) {
//                if (entry.getValue().equals(""))
//                    list.add(new BasicNameValuePair(entry.getKey(), null));
//                else
//                    list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
//                    System.out.println(entry.getKey() + ":" + entry.getValue());
//            }
//            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "UTF-8");

            //构造请求主体(表单)
            StringEntity entity = new StringEntity(getFormStr(formMap));
            //设置请求主体
            post.setEntity(entity);


            //发送POST请求, 获得响应
            CloseableHttpResponse response = client.execute(post);
            return response;


        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
