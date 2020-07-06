package com.tencent.service.impl;

import com.tencent.service.ILikeService;
import com.tencent.utils.RedisAdpeter;
import com.tencent.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("likeService")
public class LikeServiceImpl implements ILikeService {

    private static Logger logger = LoggerFactory.getLogger(LikeServiceImpl.class);

    @Autowired
    RedisAdpeter redisAdpeter;

    @Override
    public long like(int userId, int articleId, int entityType) {
        //在喜欢的集合添加
        String key = RedisUtil.getLikeKey(articleId, entityType);



        logger.info("Redis_key:" + key);
        logger.info(String.valueOf(userId));

        redisAdpeter.sadd(key, String.valueOf(userId));
        long count = redisAdpeter.scard(key);
        logger.info("Like_key_counts:" + count);


        return redisAdpeter.scard(key);
    }
}
