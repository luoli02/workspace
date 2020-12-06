package com.example.concurrent.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author LuoLi
 * @version 1.0
 * @date 2020/12/4 10:36
 */

@RestController
public class TestController {

    /**
     * 直接使用ArrayList会出现被覆盖的情况
     */
//    List<String> list = new ArrayList();
    /**
     * Vector可以防止被覆盖
     */
//    List<String> list = new Vector<>();

    /**
     * CopyOnWriteArrayList可以防止被覆盖
     */
    CopyOnWriteArrayList list = new CopyOnWriteArrayList();

    @GetMapping("add")
    public String add() {
        Collections.synchronizedList(new ArrayList<>());
        list.add(System.currentTimeMillis() + "");
        return "ok";
    }

    @GetMapping("get")
    public int get() {
        int size = list.size();
        return size;
    }

    public static void main(String[] args) {
//        List<String> list = new ArrayList();
//        list.add("a");
//        list.add("b");
//        list.add("b");
//        list.add("c");
//        list.add("d");
        /**
         * 不安全的删除集合中的元素 会出现ConcurrentModificationException
         */
//        list.forEach(x -> {
//            if (x.equals("b")) {
//                list.remove(x);
//            }
//        });

//        for (String item : list) {
//            if (item == "a"){
//                list.remove(item);
//            }
//        }
        /**
         * 不安全的删除集合中的元素 无法删除重复元素
         */
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i) == "b") {
//                list.remove(i);
//            }
//        }

        /**
         * 可以安全的删除 1
         */
//        Iterator<String> iterator = list.iterator();
//        while (iterator.hasNext()) {
//            String next = iterator.next();
//            if (next.equals("b")) {
//                iterator.remove();
//            }
//        }
        /**
         * 可以安全的删除 2
         */
//        list.removeIf(x -> x.equals("b"));

//        list.stream().forEach(x -> {
//            if (x.equals("b")) {
//                list.remove(x);
//            }
//        });


    }


}
