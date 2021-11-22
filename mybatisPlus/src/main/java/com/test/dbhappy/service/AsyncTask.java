package com.test.dbhappy.service;

import java.util.concurrent.Future;

/**
 * 只能外部类调用，返回值只能是void或者Future,要配合@EnableAsync，@Async注解
 *
 * @param <T>
 */
public interface AsyncTask<T> {

    void tesTask(int i) throws InterruptedException;

    Future<String> stringTask(String str) throws InterruptedException;

}
