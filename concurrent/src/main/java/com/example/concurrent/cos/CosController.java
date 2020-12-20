package com.example.concurrent.cos;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.model.GeneratePresignedUrlRequest;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.region.Region;
import com.tencent.cloud.CosStsClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * @author LuoLi
 * @version 1.0
 * @date 2020/12/18 11:05
 */

@RestController
public class CosController {

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


    @GetMapping("uploading")
    public JSON uploading() throws Exception {
        //code
        TreeMap<String, Object> config = new TreeMap<String, Object>();
        config.put("SecretId", secretId);
        config.put("SecretKey", secretKey);
        config.put("durationSeconds", durationSeconds);
        config.put("bucket", bucket);
        config.put("region", region);
        config.put("allowPrefix", allowPrefix);
        String[] allowActions = new String[]{
                "name/cos:PutObject",
                "name/cos:PostObject",
                "name/cos:InitiateMultipartUpload",
                "name/cos:ListMultipartUploads",
                "name/cos:ListParts",
                "name/cos:UploadPart",
                "name/cos:CompleteMultipartUpload"
        };
        config.put("allowActions", allowActions);
        JSONObject credential = CosStsClient.getCredential(config);
        JSON parse = JSONUtil.parse(credential.toString());
        return parse;
    }

    @PostMapping("MultipartFile")
    public String uploadServer(MultipartFile file) throws Exception {
        COSClient cosclient = null;
        String suffix = null;
        try {
            if (!StringUtils.isEmpty(file.getOriginalFilename())) {
                String originalFilename = file.getOriginalFilename();
                suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            } else {
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
            assert cosclient != null;
            cosclient.shutdown();
        }

    }

    @PostMapping("get1")
    public void get(@RequestBody String str) {
        Map map = JSONUtil.toBean(str, Map.class);
        String path1 = (String) map.get("path1");
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        Region region1 = new Region(region);
        ClientConfig clientConfig = new ClientConfig(region1);
        COSClient cosClient = new COSClient(cred, clientConfig);
        String bucketName = bucket;
        String key = path1.split(path)[1];

        GeneratePresignedUrlRequest req =
                new GeneratePresignedUrlRequest(bucketName, key, HttpMethodName.GET);
        URL url = cosClient.generatePresignedUrl(req);
        System.out.println(url.toString());
        cosClient.shutdown();
    }
}
