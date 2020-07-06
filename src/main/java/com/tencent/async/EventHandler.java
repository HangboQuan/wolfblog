package com.tencent.async;

import java.util.List;

public interface EventHandler {

    void doHandler(EventModel model0);
    List<EventType> getSupportTypes();
}
