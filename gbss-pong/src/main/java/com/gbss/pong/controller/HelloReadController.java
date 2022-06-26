package com.gbss.pong.controller;

import com.gbss.pong.constants.ReadFileNameConstants;
import com.gbss.pong.disruptor.service.DisruptorMqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * <p>
 * <b>HelloReadController </b>是pong service读取信息业务 。
 * </p>
 *
 * @author hjzhou
 * @since 2022/6/24
 */
@Slf4j
@RestController
@RequestMapping("/helloPong")
public class HelloReadController {

    @Autowired
    private DisruptorMqService disruptorMqService;

    @RequestMapping("/read")
    public String helloWorld(@RequestParam(value = "name", required = true) String name) throws IOException {
        log.info("request param:{}", name);
        String filePath = ReadFileNameConstants.PONG_FILE_READ_NAME + name;
        log.info("filePath={}", filePath);
        disruptorMqService.produceMessageMQ(filePath);
        return "pong read end";
    }


}
