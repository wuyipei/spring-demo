package com.example.springdemo.api;

import com.example.springdemo.vo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface HelloSpring {

    String name = "hello";

    @GetMapping("/hello/{id}")
    public User handle(@PathVariable Integer id) throws InterruptedException;

}
