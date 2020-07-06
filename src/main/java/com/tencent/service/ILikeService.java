package com.tencent.service;

public interface ILikeService {

    long like(int userId, int articleId, int entityType);
}
