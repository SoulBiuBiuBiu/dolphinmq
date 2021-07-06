package com.flowyun.dolphinmq;

import com.flowyun.dolphinmq.common.Message;
import com.flowyun.dolphinmq.producer.Producer;
import com.flowyun.dolphinmq.utils.BeanMapUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Barry
 * @since 2021/6/30 20:00
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProducerTest {
    public RedissonClient redisson;

    @BeforeAll
    void config() {
        // 1. Create config object
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        redisson = Redisson.create(config);
    }

    @Test
    void produce() throws InterruptedException {
        Producer producer = new Producer(redisson);
        Message msg = new Message();
        Testbean test = new Testbean("test2", 2);
        msg.setTopic("t1");
        try {
            msg.setProperties(BeanMapUtils.toMap(test));
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        producer.sendMessageAsync(msg);
        Thread.sleep(1000 * 60 * 60);
    }
}
