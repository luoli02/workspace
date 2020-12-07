package com.example.concurrent.sync;

import cn.hutool.core.thread.ThreadFactoryBuilder;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 *
 * @author LuoLi
 * @version 1.0
 * @date 2020/12/7 15:30
 */
public class ReadWriteLockTest {
    /**
     * ReadWriteLock 读可以多线程读，写只能一个线程写
     */
    public static void main(String[] args) {
        MyCacheLock cacheLock = new MyCacheLock();
        ThreadPoolExecutor statsThreadPool = new ThreadPoolExecutor(
                10, 10, 0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(200), new ThreadFactoryBuilder().build());
        for (int i = 0; i < 10; i++) {
            final int temp = i;
            statsThreadPool.execute(() -> {
                cacheLock.put(temp + "", temp + "");
            });
        }

        for (int i = 0; i < 10; i++) {
            final int temp = i;
            statsThreadPool.execute(() -> {
                cacheLock.get(temp + "");
            });
        }
        statsThreadPool.shutdown();



        /*for (int i = 0; i < 10; i++) {
            final int temp = i;
            new Thread(() -> {
                cacheLock.put(temp + "", temp + "");
            }).start();
        }

        for (int i = 0; i < 10; i++) {
            final int temp = i;
            new Thread(() -> {
                cacheLock.get(temp + "");
            }).start();
        }*/
    }
}

/**
 * 缓存类
 */
class MyCacheLock {

    private volatile Map<String, Object> map = new HashMap<>();

    /**
     * 创建一个读写锁
     */
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        Lock lock = readWriteLock.writeLock();
        //写锁只能有一个线程能拿到锁
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "写入" + key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入ok");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void get(String key) {
        Lock lock = readWriteLock.readLock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "获取" + key);
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + "获取ok");
        } finally {
            lock.unlock();
        }

    }
}
