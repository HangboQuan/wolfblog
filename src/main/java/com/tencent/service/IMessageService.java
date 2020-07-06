package com.tencent.service;

import com.tencent.entity.Message;

import java.util.List;

public interface IMessageService {

    int addMessage(Message message);

    List<Message> getConversationList(int userId, int offset, int limit);

    List<Message> getConversationDetail(String conversationId, int offset, int limit);

    int getUnreadCount(int userId, String conversationId) ;
}
