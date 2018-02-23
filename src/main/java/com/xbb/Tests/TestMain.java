package com.xbb.Tests;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

public class TestMain {

    @Test
    public void mapTest(){
        Map<String,Boolean> mapRequest = new HashMap<>();
        if (mapRequest.get("d") == null)
            System.out.println("null");
        else
            System.out.println(mapRequest.get("d"));

    }

    @Test
    public void TestConcurrentHashMap(){
        ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.get("id");
        concurrentHashMap.remove("id");

    }
    @Test
    public void queueTest() {
        Queue<String> queue = new LinkedList<String>();
        //添加元素
        queue.offer("a");
        queue.offer("a");

        String a = queue.poll();
        a = queue.poll();
        a = queue.poll();

        if (a == null)
            System.out.println("a is null!");
        else
            System.out.println("over");
    }

}
