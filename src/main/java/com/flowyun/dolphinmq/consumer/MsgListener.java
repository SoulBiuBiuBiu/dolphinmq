package com.flowyun.dolphinmq.consumer;

/**
 * 消费接口
 * 〈功能详细描述〉
 *
 * @author Barry
 * @since 2021/6/30 14:55
 */
public abstract class MsgListener<T> {
    /**
     * 消费方法
     * @author  Barry
     * @since  2021/6/30 15:06
     * @param dto 消息信息
     **/
    public abstract void consume(T dto);
}