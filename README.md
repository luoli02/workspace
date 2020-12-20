正确的删除集合中的元素
---
- 使用迭代器Iterator;
- list.removeIf();

如何创建并发安全的集合
---
- new Vector<>();
- new CopyOnWriteArrayList();
- Collections.synchronizedList(new ArrayList<>());
- Collections集合工具也提供了其他集合类型的创建api

创建线程的三种方式
---
- extends Thread;
- implements Runnable
- implements Callable 
- Thread thread = new Thread(new FutureTask<String>(new CallableTest()));

线程计数器
---
- CountDownLatch 减法计数器
```javascript
CountDownLatch countDownLatch = new CountDownLatch(10);
//每创建一个线程 值就会减一
countDownLatch.countDown();
//等待，当值为0时继续往下执行
countDownLatch.await();
```
- CyclicBarrier 加法计数器
```javascript
CyclicBarrier cyclicBarrier = new CyclicBarrier(8, () -> {
            System.out.println("全部执行完成");
        });
//每创建一个线程值就会累计加一，当累计到为8时就会执行Runnable方法。
cyclicBarrier.await();
//当值阈值后执行
```

- Semaphore 信号量
```javascript
//当信号量没达到阈值就能一直创建线程。
Semaphore semaphore = new Semaphore(3);
//每执行一次acquire方法信号量就会加一，当达到信号量的阈值后，后面的线程就会等待
semaphore.acquire();
//信号量减一
semaphore.release();
```

- CAS
```javascript
AtomicInteger atomicInteger = new AtomicInteger(2020);
CAS自旋锁: expect值如果和当前值相同就进行updaye,否则一直进行循环
ABA问题(狸猫换太子): 
使用AtomicStampedReference带版本号
初始值：initialRef = "1"  初始版本号：1
新值和初始值相同进行跟新，且版本号进行加1
AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference("1", 1);
atomicStampedReference.compareAndSet("1", "2", atomicStampedReference.getStamp(),
        atomicStampedReference.getStamp() + 1);
String reference = atomicStampedReference.getReference();
System.out.println("新值" + reference);


```

RocketMq
---
- 发送消息出现ONSClientException异常时有可能是网络的问题，可以尝试切换网络环境重新发送消息。

