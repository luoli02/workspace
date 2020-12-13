package com.example.concurrent.sync;

import com.qcloud.cos.utils.Md5Utils;
import org.springframework.util.CollectionUtils;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
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
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
//        Field field = Unsafe.class.getDeclaredField("theUnsafe");
//        field.setAccessible(true);
//        Unsafe unsafe = (Unsafe) field.get(null);
//        Student student = new Student();
//        Class<? extends Student> aClass = student.getClass();
//        Field name = aClass.getDeclaredField("name");
//        long l = unsafe.objectFieldOffset(name);
//        unsafe.putObject(student,l,"luo");
//        System.out.println();
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

class Student{
    private String name = "1";
}
