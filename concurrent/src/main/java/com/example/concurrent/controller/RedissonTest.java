package com.example.concurrent.controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author LuoLi
 * @version 1.0
 * @date 2020/12/7 19:46
 */

@RestController
public class RedissonTest {

    private static int i = 100;

    @GetMapping("redisson")
    public int get() throws InterruptedException {
        RLock lock = Redisson.create().getLock("aa");
        lock.lock();
        lock.tryLock(2, 15, TimeUnit.MILLISECONDS);
        try {
            if (i > 0) {
                TimeUnit.MILLISECONDS.sleep(10);
                i--;
            }
            System.out.println(i);
            return i;
        } finally {
            lock.unlock();
        }

    }

}
