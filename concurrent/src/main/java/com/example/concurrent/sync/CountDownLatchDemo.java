package com.example.concurrent.sync;

import java.util.concurrent.CountDownLatch;

/**
 * @author LuoLi
 * @version 1.0
 * @date 2020/12/6 21:11
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        //递减线程计数器
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                //计数器进行减一
                System.out.println(Thread.currentThread().getName() + "-->" + "到");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println("出发");


    }

}
