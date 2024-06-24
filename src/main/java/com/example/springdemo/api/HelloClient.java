package com.example.springdemo.api;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "helloService", url = "http://localhost:8080")
public interface HelloClient extends HelloSpring{
}
