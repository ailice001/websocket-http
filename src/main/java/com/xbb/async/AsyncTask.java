package com.xbb.async;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

/**
 * @author fengzp
 * @date 17/5/8
 * @email fengzp@gzyitop.com
 * @company 广州易站通计算机科技有限公司
 */
@Component
public class AsyncTask {

    private static Logger log = LoggerFactory.getLogger(AsyncTask.class);

    public static Random random =new Random();

    /**
     * @Async所修饰的函数不要定义为static类型，否则异步调用不会生效
     *
     * 这里通过返回Future<T>来返回异步调用的结果，实现异步回调
     */
    @Async
    public Future<String> test1() throws InterruptedException {
        System.out.println("test1 begin");
        long begin = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        System.out.println("test1 end " + (System.currentTimeMillis() - begin));
        return new AsyncResult<String>("test1 is done!");
    }

    @Async
    public Future<String> test2() throws InterruptedException {
        System.out.println("test2 begin");
        long begin = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        System.out.println("test2 end " + (System.currentTimeMillis() - begin));
        return new AsyncResult<String>("test2 is done!");
    }

    @Async
    public Future<String> test3() throws InterruptedException {
        System.out.println("test3 begin");
        long begin = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        System.out.println("test3 end " + (System.currentTimeMillis() - begin));
        return new AsyncResult<String>("test3 is done!");
    }

    @Async      // 异步不返回数据
    public void dealNoReturnTask(){
        log.info("Thread {} deal No Return Task start", Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Thread {} deal No Return Task end at {}", Thread.currentThread().getName(), System.currentTimeMillis());
    }



    @Async    // 可以返回数据
    public Future<String> dealHaveReturnTask() {
        try {
            Thread.sleep(3000); /*random.nextInt(20)*150*/
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("thread", Thread.currentThread().getName());
        jsonObject.put("time", System.currentTimeMillis());
        return new AsyncResult<String>(jsonObject.toJSONString());
    }

}
