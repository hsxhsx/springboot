package com.test.dbhappy.service.impl;

import com.test.dbhappy.service.AsyncTask;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class AsyncTaskImpl implements AsyncTask {

    @Async("taskExecutor")
    @Override
    public void tesTask(int i) throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"-----"+i);
        Thread.sleep(50);
    }

    @Async("taskExecutor")
    @Override
    synchronized public Future<String> stringTask(String str) throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+str);
        Thread.sleep(50);
        return new AsyncResult<String>(String.valueOf(str));
    }

}
