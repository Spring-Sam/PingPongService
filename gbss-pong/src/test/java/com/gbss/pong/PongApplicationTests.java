package com.gbss.pong;

import com.gbss.pong.constants.ReadFileNameConstants;
import com.gbss.pong.disruptor.service.DisruptorMqService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SpringBootTest
class PongApplicationTests {

    @Autowired
    private DisruptorMqService disruptorMqService;

    @Test
    void contextLoads() {
        WebClient webClient = WebClient.create("http://localhost:8083");
        Mono<String> resp = webClient
                .get().uri("/helloPong/read?name=123.txt")
                .retrieve()
                .bodyToMono(String.class);
        resp.subscribe(System.out::println);

    }


    @Test
    void testDisruptorMq() {
        disruptorMqService.produceMessageMQ(ReadFileNameConstants.PONG_FILE_READ_NAME+"ping__20220625115735.txt");

    }
}
