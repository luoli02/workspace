package com.example.concurrent.service;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author LuoLi
 * @version 1.0
 * @date 2020/12/8 17:37
 */
public interface OrderService {

    String getOrderType();
    String uploadServer(MultipartFile file) throws Exception;
    public String uploadServer2(InputStream inputStream) throws Exception;



}
