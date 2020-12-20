package com.example.concurrent.sync;



import org.apache.commons.codec.binary.Base64;
import org.springframework.util.CollectionUtils;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.LongStream;

/**
 * @author LuoLi
 * @version 1.0
 * @date 2020/12/5 14:17
 */
public class SyncTest {
    public static void main(String[] args) throws Exception {

        try {
            int i = 1/0;
        }catch (Exception e){
           throw new Exception("异常");
        }
        System.out.println(111);

//        String md5str = DigestUtils.md5Hex("wx5e7b6ac7acadb5a3");
//        System.out.println("MD52加密后的字符串为:" + md5str + "\t长度：" + md5str.length());


//        ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>(){
//            @Override
//            protected DateFormat initialValue() {
//                return new SimpleDateFormat("yyyy-MM-dd");
//            }
//        };
//        DateFormat dateFormat = threadLocal.get();
//
//        long start = System.currentTimeMillis();
//        long reduce = LongStream.rangeClosed(0L, 10L).parallel().reduce(0, Long::sum);
//        long end = System.currentTimeMillis();
//        System.out.println(reduce + "===" + (end - start));

//        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList();
//        List<Object> list = Collections.synchronizedList(new ArrayList<>());
//        copyOnWriteArrayList.add(1);
//        System.out.println(copyOnWriteArrayList);
    }

}

