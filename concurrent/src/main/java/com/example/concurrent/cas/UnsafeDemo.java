package com.example.concurrent.cas;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author LuoLi
 * @version 1.0
 * @date 2020/12/14 0:16
 */
public class UnsafeDemo {
    public static void main(String[] args) throws Exception {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        Student student = new Student();
        Class<? extends Student> aClass = student.getClass();
        Field name = aClass.getDeclaredField("name");
        //获取变量的偏移值 进行修改对象
        long l = unsafe.objectFieldOffset(name);
        unsafe.putObject(student, l, "luo");
        //通过暴力反射修改对象
        name.setAccessible(true);
        name.set(student,"kk");
        System.out.println(student.getName());
    }
}

