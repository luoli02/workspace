package com.example.concurrent.sync;

import cn.hutool.core.thread.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author LuoLi
 * @version 1.0
 * @date 2020/12/7 16:54
 */
public class ThreadPoolTest {
    public static void main(String[] args) {

        ThreadPoolExecutor statsThreadPool = new ThreadPoolExecutor(
                10, 10, 0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(200), new ThreadFactoryBuilder().build());

        /**
         * 以下不建议使用
         */
        //单例线程池
//        ExecutorService service = Executors.newSingleThreadExecutor();
        //多例线程池
        ExecutorService service = Executors.newFixedThreadPool(2);
//        ExecutorService service = Executors.newCachedThreadPool();

//        ThreadPoolExecutor service = new ThreadPoolExecutor(
//                10, 10, 0L,
//                TimeUnit.MILLISECONDS,
//                new LinkedBlockingDeque<>(200), new ThreadFactoryBuilder().build());
        for (int i = 0; i < 5; i++) {
            service.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "执行了");
            });
        }


    }
}
