package com.gbss.pong.disruptor.service.impl;

import com.gbss.pong.disruptor.model.MessageVo;
import com.gbss.pong.disruptor.service.DisruptorMqService;
import com.lmax.disruptor.RingBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * <p>
 * <b>MessageVo </b>是消息的生产者具体实现业务 。
 * </p>
 *
 * @author hjzhou
 * @since 2022/6/25
 */

@Component
@Service
public class DisruptorMqServiceImpl implements DisruptorMqService {
    Logger log = LoggerFactory.getLogger(DisruptorMqServiceImpl.class);

    @Autowired
    private RingBuffer<MessageVo> MessageVoRingBuffer;


    @Override
    public void produceMessageMQ(String message) {
        //获取下一个Event槽的下标
        long sequence = MessageVoRingBuffer.next();
        try {
            //给Event填充数据
            MessageVo event = MessageVoRingBuffer.get(sequence);
            event.setCode("GOTOREAD");
            event.setValue(message);
            log.info("往消息队列中添加消息：{}", event);
        } catch (Exception e) {
            log.error("failed to add event to MessageVoRingBuffer for : e = {},{}", e, e.getMessage());
        } finally {
            //发布Event，激活观察者去消费，将sequence传递给改消费者
            //注意最后的publish方法必须放在finally中以确保必须得到调用；如果某个请求的sequence未被提交将会堵塞后续的发布操作或者其他的producer
            MessageVoRingBuffer.publish(sequence);
        }
    }
}

