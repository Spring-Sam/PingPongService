package com.gbss.pong.disruptor.service;


/**
 * <p>
 * <b>DisruptorMqService </b>是消息生产者接口 。
 * </p>
 *
 * @author hjzhou
 * @since 2022/6/25
 */
public interface DisruptorMqService {

    /**
     * 生产消息
     * @param message
     */
    void produceMessageMQ(String message);
}


