package com.gbss.ping.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * <b>HelloPongFeignService</b>是调用pong service的接口 。
 * </p>
 *
 * @author hjzhou
 * @since 2022/6/25
 */
@FeignClient(value = "gbss-pong", contextId = "HelloPongFeignService", path = "/helloPong")
public interface HelloPongFeignService {

    @RequestMapping("/read")
    String helloPongRead(@RequestParam String name);


}
