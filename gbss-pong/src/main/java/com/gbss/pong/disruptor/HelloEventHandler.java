package com.gbss.pong.disruptor;

import com.gbss.pong.disruptor.model.MessageVo;
import com.lmax.disruptor.EventHandler;
import lombok.extern.slf4j.Slf4j;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;


/**
 * <p>
 * <b>HelloEventHandler </b>是消息消费者，具体对接收到的事件进行消费处理 。
 * </p>
 *
 * @author hjzhou
 * @since 2022/6/25
 */

@Slf4j
public class HelloEventHandler implements EventHandler<MessageVo> {

    @Override
    public void onEvent(MessageVo event, long sequence, boolean endOfBatch) {
        try {

            log.info("消费者处理消息开始");
            if (event != null) {
                log.info("event.value={}",event.getValue());
                FileInputStream fin = new FileInputStream(event.getValue());
                InputStreamReader reader = new InputStreamReader(fin);
                BufferedReader buffReader = new BufferedReader(reader);
                String strTmp = "";
                StringBuffer stringBuffer = new StringBuffer();
                while ((strTmp = buffReader.readLine()) != null) {
                    stringBuffer.append(strTmp);
                }
                buffReader.close();
                log.info("消费者消费的信息是：{}", stringBuffer.toString());

            }
        } catch (Exception e) {
            log.info("消费者处理消息失败");
        }
        log.info("消费者处理消息结束");
    }
}
