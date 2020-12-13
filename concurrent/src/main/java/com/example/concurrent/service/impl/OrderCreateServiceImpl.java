package com.example.concurrent.service.impl;

import com.example.concurrent.service.OrderService;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Calendar;
import java.util.UUID;

/**
 * @author LuoLi
 * @version 1.0
 * @date 2020/12/8 17:38
 */
@Service
public class OrderCreateServiceImpl implements OrderService {
    @Value("${cos.secretId}")
    private String secretId;

    @Value("${cos.secretKey}")
    private String secretKey;

    @Value("${cos.durationSeconds}")
    private int durationSeconds;

    @Value("${cos.bucket}")
    private String bucket;

    @Value("${cos.region}")
    private String region;

    @Value("${cos.allowPrefix}")
    private String allowPrefix;

    @Value("${cos.prefix}")
    private String prefix;

    @Value("${cos.path}")
    private String path;
    @Override
    public String getOrderType() {
        return "create";
    }

    @Override
    public String uploadServer(MultipartFile file) throws Exception {
        COSClient cosclient = null;
        String suffix = null;
        try {
            if (!StringUtils.isEmpty(file.getOriginalFilename())) {
                String originalFilename = file.getOriginalFilename();
                suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            } else {
                //支出上传生成的二维码.jpg 无名称
                suffix = ".jpg";
            }
            InputStream inputStream = file.getInputStream();
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            String newFileName = prefix + "/" + year + "/" + UUID.randomUUID() + suffix;
            COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
            Region regionId = new Region(region);
            ClientConfig clientConfig = new ClientConfig(regionId);
            cosclient = new COSClient(cred, clientConfig);
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(inputStream.available());
            cosclient.putObject(bucket, newFileName, inputStream, metadata);
            return this.path + "/" + newFileName;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            cosclient.shutdown();
        }
    }

    @Override
    public String uploadServer2(InputStream inputStream) throws Exception{
        COSClient cosclient = null;
        try {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            String newFileName = prefix + "/" + year + "/" + System.currentTimeMillis() + ".jpg";
            COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
            Region regionId = new Region(region);
            ClientConfig clientConfig = new ClientConfig(regionId);
            cosclient = new COSClient(cred, clientConfig);
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(inputStream.available());
            cosclient.putObject(bucket, newFileName, inputStream, metadata);
            return this.path + "/" + newFileName;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            cosclient.shutdown();
        }
    }
}
