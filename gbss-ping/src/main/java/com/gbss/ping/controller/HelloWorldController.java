package com.gbss.ping.controller;

import com.gbss.commoncore.constants.FileNameConstants;
import com.gbss.ping.feign.HelloPongFeignService;
import com.gbss.ping.service.BasicTextService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api("PingService写入文件接口")
@RestController
@RequestMapping("/api")
public class HelloWorldController {

    @Autowired
    private BasicTextService basicTextService;

    @Autowired
    private HelloPongFeignService helloPongFeignService;


    @ApiOperation("通过feign方式调用PongService接口")
    @GetMapping("/hello")
    public String helloWorld() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(() -> (String) helloPongFeignService.helloPongRead("ping__20220625205027.txt"));
        String result = (String) future.get();
        executorService.shutdown();
        return "This is Jack World!" + result;
    }

    @ApiOperation("helloWorld2测试接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuName", value = "学生姓名", required = true),
    })
    @GetMapping("/hello2")
    public String helloWorld2(String stuName){
        String FileName = FileNameConstants.PONG_FILE_READ_NAME;
        return String.format("Hello for %s",stuName);
    }


    @ApiOperation("定时写入信息到本机磁盘的接口")
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
