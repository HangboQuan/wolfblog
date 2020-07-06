package com.tencent.utils;

public class RedisUtil {


    private static String SPLIT = ":";
    private static String BIZ_LIKE = "LIKE";

    private static String BIZ_EVENT = "EVENT";

    public static String getEventQueueKey (){
        return BIZ_EVENT;
    }

    public static String getLikeKey(int entityId,int entityType) {
        return BIZ_LIKE + SPLIT + String.valueOf(entityId) + SPLIT + String.valueOf(entityType);
    }
}
