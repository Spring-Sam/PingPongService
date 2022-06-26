package com.gbss.ping.controller;

import com.gbss.ping.feign.HelloPongFeignService;
import com.gbss.ping.service.BasicTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


import java.time.Duration;
import java.util.concurrent.*;

/**
 * <p>
 * <b>HelloWorldController</b>是ping service写入文件的相关业务。
 * </p>
 *
 * @author hjzhou
 * @since 2022/6/24
 */
@RestController
@RequestMapping("/api")
public class HelloWorldController {

    @Autowired
    private BasicTextService basicTextService;

    @Autowired
    private HelloPongFeignService helloPongFeignService;


    @RequestMapping("/hello")
    public String helloWorld() throws InterruptedException, ExecutionException, TimeoutException {
        //TODO Test...
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(() -> (String) helloPongFeignService.helloPongRead("/Users/hjzhou/Documents/myCode/txtFolder/ping__20220625155443.txt"));
        String result = (String) future.get();
        executorService.shutdown();
        return "This is Jack World!" + result;
    }


    @GetMapping(path = "/basicText", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<String> getBasicText() {
        return Flux
                .interval(Duration.ofSeconds(1))
                .map(sequence -> {
                    try {
                        return basicTextService.getShowText();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                });
    }


}
