package com.test.dbhappy.service.impl;

import com.test.dbhappy.service.AsyncTask;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncTaskImpl implements AsyncTask {

    @Async("taskExecutor")
    @Override
    public void tesTask(int i){
        System.out.println(Thread.currentThread().getName()+"-----"+i);
    }

    @Async("taskExecutor")
    @Override
    public void stringTask(String str){
        System.out.println(Thread.currentThread().getName()+str);
    }
}
