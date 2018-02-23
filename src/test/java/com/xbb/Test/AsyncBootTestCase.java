package com.xbb.Test;

import com.xbb.ClientControlApplication;
import com.xbb.async.AsyncTask;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ClientControlApplication.class/*, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT*/)
//@WebAppConfiguration
//@ContextConfiguration(locations = {"/application.properties"})
@RestController
public class AsyncBootTestCase {
    //@Async 提供异步处理方案  方法异步执行  可以通过feature返回
    @Autowired
    private AsyncTask asyncTest;

    @Test
    public void testAsyncTest() throws InterruptedException {
        AsyncTest();
    }

    @Test
    public void testAsyncTestReturn() throws InterruptedException {
        JSONObject jsonObject = AsyncTestReturn();
        System.out.println(jsonObject.toJSONString());
    }

    @ResponseBody
    @RequestMapping("/task")
    public void AsyncTest() throws InterruptedException {
        System.out.println("begin");
        long begin = System.currentTimeMillis();
        Future<String> test1 = asyncTest.test1();
        Future<String> test2 = asyncTest.test2();
        Future<String> test3 = asyncTest.test3();

        while(true) {
            if(test1.isDone() && test2.isDone() && test3.isDone())
                break;

            Thread.sleep(500);
        }

        System.out.println("end 耗时: " + (System.currentTimeMillis() - begin));
    }

    @ResponseBody
    @RequestMapping("/all")
    public JSONObject AsyncTestReturn() throws InterruptedException {
        System.out.println("begin");
        long begin = System.currentTimeMillis();
        Future<String> test1 = asyncTest.test1();
        Future<String> test2 = asyncTest.test2();
        Future<String> test3 = asyncTest.test3();

        // 用循环会浪费cpu时间
        while(true) {
            if(test1.isDone() && test2.isDone() && test3.isDone())
                break;

            Thread.sleep(500);
        }
        JSONObject json = new JSONObject();
        System.out.println(" end 耗时: " + (System.currentTimeMillis() - begin));
        json.put("test1",test1);
        json.put("test2",test2);
        json.put("test3",test3);
        return json;
    }

}
