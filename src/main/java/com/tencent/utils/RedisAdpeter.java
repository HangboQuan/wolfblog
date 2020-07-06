package com.tencent.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

@Component
public class RedisAdpeter implements InitializingBean {


    private static final Logger logger =  LoggerFactory.getLogger(RedisAdpeter.class);
    private Jedis jedis = null;
    private JedisPool jedisPool = null;


    @Override
    public void afterPropertiesSet() throws Exception {
        jedisPool = new JedisPool("localhost", 6379);
        logger.info("Connection redis success!");
    }

    public void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        } catch (Exception e) {
            logger.error("Redis异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } catch (Exception e) {
            logger.error("Redis异常" + e.getMessage());
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public long sadd(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.sadd(key, value);
        } catch (Exception e) {
            logger.error("Redis异常" + e.getMessage());
            return 0;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public long srem(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.srem(key);
        } catch (Exception e) {
            logger.error("Redis异常" + e.getMessage());
            return 0;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public boolean ismember(String key,String value) {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            return jedis.sismember(key,value);//判断是否在列表中
        } catch(Exception e) {
            logger.error("发生异常" + e.getMessage());
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public long scard(String key) {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            return jedis.scard(key);//判断redis中的个数
        } catch(Exception e) {
            logger.error("发生异常" + e.getMessage());
            return 0;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public List<String> brpop(int timeout, String key) {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            return jedis.brpop(timeout,key);
        } catch(Exception e) {
            logger.error("发生异常" + e.getMessage());
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public long lpush(String key,String value) {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            return jedis.lpush(key, value);//设置key的过期时间
        } catch(Exception e) {
            logger.error("发生异常" + e.getMessage());
            return 0;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
