package com.xbb.Test;

import com.xbb.async.AsyncTask;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Future;

public class AsyncJunitTestCase {
    @Autowired
    private AsyncTask asyncTask;

    private static Logger log = LoggerFactory.getLogger(AsyncTask.class);

    @Before
    public void init(){
        asyncTask = new AsyncTask();
    }


    @Test
    public void testDealNoReturnTask(){
        asyncTask.dealNoReturnTask();
        try {
            log.info("begin to deal other Task!");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test    //Future类 类似中间件 ThreadLocal变量
    public void testDealHaveReturnTask() throws Exception {
        Future<String> future = asyncTask.dealHaveReturnTask();
        log.info("begin to deal other Task!");
        while (true) {
            if(future.isCancelled()){
                log.info("deal async task is Cancelled");
                break;
            }
            if (future.isDone() ) {
                log.info("deal async task is Done");
                log.info("return result is " + future.get());
                break;
            }
            log.info("wait async task to end ...");
            Thread.sleep(1000);
        }
    }

}
