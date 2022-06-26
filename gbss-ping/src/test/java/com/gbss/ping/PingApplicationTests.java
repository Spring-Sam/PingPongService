package com.gbss.ping;

import com.gbss.ping.feign.HelloPongFeignService;
import com.gbss.ping.service.BasicTextService;
import com.gbss.ping.utils.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@SpringBootTest
class PingApplicationTests {

    @Autowired
    private BasicTextService basicTextService;

    @Autowired
    private HelloPongFeignService helloPongFeignService;

    @Test
    void contextLoads() {
        WebClient webClient = WebClient.create("http://localhost:8081");
        Mono<String> resp = webClient
                .get().uri("/api/hello")
                .retrieve()
                .bodyToMono(String.class);
        resp.subscribe(System.out::println);



    }

    @Test
    void testHelloWorld() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();;
        // WebFlux异步调用，同步会报错触发阻塞，
        Future future = executorService.submit(() -> (String) helloPongFeignService.helloPongRead("/Users/hjzhou/Documents/myCode/txtFolder/ping__20220625155443.txt"));
        String result = (String)future.get();
        executorService.shutdown();

    }

    @Test
    void testHelloWorld2() throws ExecutionException, InterruptedException {
        DateUtils.parseDateToStr("yyyyMMddHHmmss",null);
    }

}
