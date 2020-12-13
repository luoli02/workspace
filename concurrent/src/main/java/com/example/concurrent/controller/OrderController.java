package com.example.concurrent.controller;

import cn.hutool.json.JSONUtil;
import com.example.concurrent.service.OrderService;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author LuoLi
 * @version 1.0
 * @date 2020/12/8 17:41
 */

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/code")
    public String code(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> paramMap = new HashMap<>(16);
        paramMap.put("grant_type", "client_credential");
        paramMap.put("appid", "wx5e7b6ac7acadb5a3");
        paramMap.put("secret", "5ac0a74aba9a4eebbdea7da298a6fceb");
        String token = getForObject("https://api.weixin.qq.com/cgi-bin/token", paramMap);
        Map<String, String> map2 = JSONUtil.toBean(token, Map.class);
        String access_token = map2.get("access_token");
        Map<String,String> param = new HashMap<>();
        param.put("path","page/index/index");
        param.put("width","100");
        String s1 = JSONUtil.toJsonStr(param);
        URI getCodeUrl = new URIBuilder("https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode")
                .setParameter("access_token", access_token).build();
        String s =null;
        try {
            URL url = new URL("https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token=" + access_token);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url
                    .openConnection();
            httpURLConnection.setRequestMethod("POST");// 提交模式
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            printWriter.write(s1);
            // flush输出流的缓冲
            printWriter.flush();
            // 开始获取数据
            InputStream inputStream = httpURLConnection.getInputStream();
            MultipartFile multipartFile = new MockMultipartFile("aa.jpg", inputStream);
            String s2 = orderService.uploadServer(multipartFile);
            return s2;
        } catch (ConnectException ce) {
            ce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private String getForObject(String url, Object object) {
        StringBuffer stringBuffer = new StringBuffer(url);
        if (object instanceof Map) {
            Iterator iterator = ((Map) object).entrySet().iterator();
            if (iterator.hasNext()) {
                stringBuffer.append("?");
                Object element;
                while (iterator.hasNext()) {
                    element = iterator.next();
                    Map.Entry<String, Object> entry = (Map.Entry) element;
                    if (entry.getValue() != null) {
                        stringBuffer.append(element).append("&");
                    }
                    url = stringBuffer.substring(0, stringBuffer.length() - 1);
                }
            }
        } else {
            throw new RuntimeException("url请求:" + url + "请求参数有误不是map类型");
        }

        return restTemplate.getForObject(url, String.class);
    }
}













