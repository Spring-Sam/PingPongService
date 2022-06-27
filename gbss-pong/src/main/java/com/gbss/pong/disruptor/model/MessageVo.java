package com.gbss.pong.disruptor.model;


import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * <b>MessageVo </b>是消息队列的消息载体 。
 * </p>
 *
 * @author hjzhou
 * @since 2022/6/25
 */
@Getter
@Setter
public class MessageVo {

    private String code;

    private String value;



}
