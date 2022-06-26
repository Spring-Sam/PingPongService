package com.gbss.pong.disruptor.model;



/**
 * <p>
 * <b>MessageVo </b>是消息队列的消息载体 。
 * </p>
 *
 * @author hjzhou
 * @since 2022/6/25
 */
public class MessageVo {

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
