package com.gbss.pong.disruptor;

import com.gbss.pong.disruptor.model.MessageVo;
import com.lmax.disruptor.EventFactory;

/**
 * <p>
 * <b>HelloEventFactory </b>是事件工厂，返回实例 。
 * </p>
 *
 * @author hjzhou
 * @since 2022/6/25
 */
public class HelloEventFactory implements EventFactory<MessageVo> {
    @Override
    public MessageVo newInstance() {
        return new MessageVo();
    }
}