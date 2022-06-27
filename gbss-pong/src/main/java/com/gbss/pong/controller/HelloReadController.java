package com.gbss.pong.controller;


import com.gbss.commoncore.constants.FileNameConstants;
import com.gbss.pong.disruptor.service.DisruptorMqService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@Api("PongService服务接口")
@Slf4j
@RestController
@RequestMapping("/helloPong")
public class HelloReadController {

    @Autowired
    private DisruptorMqService disruptorMqService;

    @ApiImplicitParam(name = "name", value = "文件名", dataTypeClass = String.class)
    @ApiOperation(value = "进行读取文件内容",notes = "使用LMAX Disruptor")
    @GetMapping("/read")
    public String helloWorld(@RequestParam(value = "name", required = true) String name) throws IOException {
        log.info("request param:{}", name);
        String filePath = FileNameConstants.PONG_FILE_READ_NAME + name;
        log.info("filePath={}", filePath);
        disruptorMqService.produceMessageMQ(filePath);
        return "pong read end";
    }


}
