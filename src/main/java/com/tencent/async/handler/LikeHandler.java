package com.tencent.async.handler;

import com.tencent.async.EventHandler;
import com.tencent.async.EventModel;
import com.tencent.async.EventType;
import com.tencent.entity.Article;
import com.tencent.entity.Message;
import com.tencent.entity.User;
import com.tencent.service.IArticleService;
import com.tencent.service.IMessageService;
import com.tencent.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class LikeHandler implements EventHandler {


    @Autowired
    IMessageService messageService;

    @Autowired
    IUserService userService;

    @Autowired
    IArticleService articleService;

    @Override
    public void doHandler(EventModel model) {
        //System.out.println("LIKE");

        Message message = new Message();
        User user = userService.getUserById(model.getActorId());


        Article article = articleService.getArticleByStatusAndId(1, model.getEntityId());
        //发送给对象的拥有者
        message.setToId(model.getEntityOwnerId());
        message.setContent(user.getUserName() +
                " 赞了您的文章:"
                + article.getArticleTitle());
        // SYSTEM ACCOUNT
        message.setFromId(user.getUserId());
        message.setCreatedDate(new Date());
        String conversionId = "";
        //对话的id，统一把小id放在前面
        if (model.getEntityId() < model.getEntityOwnerId()) {
            conversionId = model.getEntityId() + "_" + model.getEntityOwnerId();
        } else{
            conversionId = model.getEntityOwnerId() + "_" + model.getEntityId();
        }
        message.setConversationId(conversionId);
        messageService.addMessage(message);


    }

    @Override
    public List<EventType> getSupportTypes() {
        return Arrays.asList(EventType.LIKE);
    }
}
