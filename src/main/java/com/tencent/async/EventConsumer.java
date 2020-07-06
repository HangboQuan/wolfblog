package com.tencent.async;


import com.alibaba.fastjson.JSON;

import com.tencent.utils.RedisAdpeter;
import com.tencent.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EventConsumer implements InitializingBean, ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(EventConsumer.class);
    private ApplicationContext applicationContext;
    private Map<EventType, List<EventHandler>> config = new HashMap<>();


    @Autowired
    RedisAdpeter redisAdpeter;

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, EventHandler> beans = applicationContext.getBeansOfType(EventHandler.class);
        if (beans != null) {
            for (Map.Entry<String, EventHandler> entry : beans.entrySet()) {
                List<EventType> eventTypes = entry.getValue().getSupportTypes();
                for (EventType type : eventTypes) {
                    if (!config.containsKey(type)) {
                        config.put(type, new ArrayList<EventHandler>());
                    }

                    //注册每个事件的处理函数
                    config.get(type).add(entry.getValue());
                }


            }
        }
        //启动线程去消费事件
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //从队列中一直消费
                while(true) {
                    String key = RedisUtil.getEventQueueKey();
                    //brpop的作用是：等待弹出key的尾/头元素
                    List<String> messages = redisAdpeter.brpop(0, key);

                    for (String message : messages) {
                        if (message.equals(key)) {
                            continue;
                        }

                        EventModel eventModel = JSON.parseObject(message, EventModel.class);

                        if (!config.containsKey(eventModel.getType())) {
                            logger.error("不包含该事件");
                            continue;
                        }

                        for (EventHandler handler : config.get(eventModel.getType())) {
                            handler.doHandler(eventModel);
                        }

                    }
                }
            }
        });
        thread.start();

    }



    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
