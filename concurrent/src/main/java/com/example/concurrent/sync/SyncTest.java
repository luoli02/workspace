package com.example.concurrent.sync;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author LuoLi
 * @version 1.0
 * @date 2020/12/5 14:17
 */
public class SyncTest {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList();
        List<Object> list = Collections.synchronizedList(new ArrayList<>());
        copyOnWriteArrayList.add(1);
        System.out.println(copyOnWriteArrayList);
    }

}
