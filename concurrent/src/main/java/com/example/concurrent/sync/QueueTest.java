package com.example.concurrent.sync;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author LuoLi
 * @version 1.0
 * @date 2020/12/8 9:32
 */
public class QueueTest {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<String>();
        queue.offer("Hello");//将指定的元素插入此队列（如果立即可行且不会违反容量限制），插入成功返回 true；否则返回 false。当使用有容量限制的队列时，offer方法通常要优于 add方法——add方法可能无法插入元素，而只是抛出一个IllegalStateException异常。
        queue.offer("World");
        queue.offer("!");
        System.out.println(queue.size());//输出：3
        String str;
        while((str=queue.poll())!=null){//获取并移除此队列的头，如果此队列为空，则返回 null。 remove方法也可以获取并移除此队列的头，但该方法与 poll方法的唯一不同在于：如果此队列为空，那么remove方法将抛出NoSuchElementException异常。
            System.out.println(str);//换行依次输出：Hello、World、!
        }
        System.out.println(queue.size());//输出：0
    }
}
