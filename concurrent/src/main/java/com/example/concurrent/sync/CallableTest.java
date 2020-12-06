package com.example.concurrent.sync;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author LuoLi
 * @version 1.0
 * @date 2020/12/6 21:17
 *
 * 创建一个异步的线程
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        FutureTask futureTask = new FutureTask(new Callable() {
//            @Override
//            public Object call() throws Exception {
//                System.out.println("1");
//                return 111;
//            }
//        });
//        Thread thread = new Thread(futureTask);
//        thread.start();
//        Object o = futureTask.get();
//        System.out.println(o);

        //第二种写法
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(new FutureTask<String>(()->{
                //计算器进行减一
                countDownLatch.countDown();
                System.out.println("线程执行");
                return "ok";
            })).start();
        }
        //等待值为0进行往下执行
        countDownLatch.await();

        System.out.println("全部执行完成");


    }

}
