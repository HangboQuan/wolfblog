package com.tencent.async;

import com.alibaba.fastjson.JSON;
import com.tencent.utils.RedisAdpeter;
import com.tencent.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventProductor {

    private static final Logger logger = LoggerFactory.getLogger(EventProductor.class);


    @Autowired
    RedisAdpeter redisAdpeter;

    public boolean fireEvent(EventModel model) {
        try{
            String json = JSON.toJSONString(model);
            System.out.println(json);
            String key = RedisUtil.getEventQueueKey();
            redisAdpeter.lpush(key, json);
            return true;
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
            return false;
        }
    }
}
