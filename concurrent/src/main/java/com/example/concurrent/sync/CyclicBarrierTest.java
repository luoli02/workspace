package com.example.concurrent.sync;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author LuoLi
 * @version 1.0
 * @date 2020/12/6 21:29
 * 递增线程计数器
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> {
            System.out.println("全部执行完成");
        });
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "-->" + "执行");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }, String.valueOf(i)).start();
        }


    }
}
