package com.test.dbhappy;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 */
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class MyApplication {
    public static void main(String[]args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
