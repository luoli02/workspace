package com.example.concurrent.sync;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore信号量
 *
 * @author LuoLi
 * @version 1.0
 * @date 2020/12/6 21:53
 */
public class SemaphoreTest {
    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    /**
                     * 当semaphore.acquire信号量就会加一，当值大到三个后，后面的线程就会等待
                     * 当执行到semaphore.release信号量就会减一
                     */
                    semaphore.acquire();
                    System.out.println("线程" + Thread.currentThread().getName() + "抢到停车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("线程" + Thread.currentThread().getName() + "离开停车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
