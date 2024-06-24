package com.example.springdemo;

import com.example.springdemo.api.HelloSpring;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient(value = HelloSpring.name, url = "http://localhost:8080/")
public interface HelloSpringClient extends HelloSpring {
}
