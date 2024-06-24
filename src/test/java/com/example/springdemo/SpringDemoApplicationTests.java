package com.example.springdemo;

import com.example.springdemo.vo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootTest
@EnableFeignClients
class SpringDemoApplicationTests {

    @Autowired
    private HelloSpringClient client;

    @Test
    void contextLoads() throws InterruptedException {
        User handle = client.handle(1);
        System.out.println(handle.getName());
    }




}
