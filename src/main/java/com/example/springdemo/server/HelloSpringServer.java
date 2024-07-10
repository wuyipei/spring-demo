package com.example.springdemo.server;


import com.example.springdemo.api.HelloClient;
import com.example.springdemo.api.HelloSpring;
import com.example.springdemo.vo.User;
import io.micrometer.core.instrument.MeterRegistry;
import io.prometheus.metrics.core.metrics.Counter;
import io.prometheus.metrics.model.registry.PrometheusRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class HelloSpringServer implements HelloSpring {

    @Autowired
    HelloClient client;

    Counter visitCounter;

    public HelloSpringServer() {
        visitCounter = Counter.builder()
                .name("spring_requests_total")
                .help("Total number of requests")
                .labelNames("path", "status")
                .register();
    }

    @GetMapping("/hello")
    public List<User> getUser() throws InterruptedException {
        visitCounter.labelValues("/hello", "200").inc();
        Flux<User> productFlux = WebClient.create()
                .get()
                .uri("http://localhost:8080/hello/1")
                .retrieve()
                .bodyToFlux(User.class);

        List<User> objects = new ArrayList<>();
        objects.add(new User("test", 100));

        productFlux.subscribe(users -> objects.add(users));

        return objects;
    }

    @Override
    public User handle(Integer id)  throws InterruptedException {
        Thread.sleep(2000L); // 模拟耗时2秒
        return Stream.of(
                new User("Fancy Smartphone", 1),
                new User("Cool Watch", 2),
                new User("Smart TV", 3)).filter(user -> Objects.equals(user.getAge(), id)).limit(1).collect(Collectors.toList()).get(0);
    }
}
