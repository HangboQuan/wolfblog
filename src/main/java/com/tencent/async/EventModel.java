package com.tencent.async;

import java.util.HashMap;
import java.util.Map;

public class EventModel {
    private EventType type;
    //事件的触发这
    private int actorId;
    private int entityId;
    private int entityType;
    //触发对象的拥有者
    private int entityOwnerId;
    private Map<String, String> exts = new HashMap<>();


    public Map<String, String> getExts() {
        return exts;
    }
    public EventModel() {

    }
    public EventModel(EventType type) {
        this.type = type;
    }

    public String getExt(String name) {
        return exts.get(name);
    }

    public EventModel setExt(String name, String value) {
        exts.put(name, value);
        return this;
    }

    public EventType getType() {
        return type;
    }

    public EventModel setType(EventType type) {
        this.type = type;
        return this;
    }

    public int getActorId() {
        return actorId;
    }

    public EventModel setActorId(int actorId) {
        this.actorId = actorId;
        return this;
    }

    public int getEntityId() {
        return entityId;
    }

    public EventModel setEntityId(int entityId) {
        this.entityId = entityId;
        return this;
    }

    public int getEntityType() {
        return entityType;
    }

    public EventModel setEntityType(int entityType) {
        this.entityType = entityType;
        return this;
    }

    public int getEntityOwnerId() {
        return entityOwnerId;
    }

    public EventModel setEntityOwnerId(int entityOwnerId) {
        this.entityOwnerId = entityOwnerId;
        return this;
    }

    @Override
    public String toString() {
        return "EventModel{" +
                "type=" + type +
                ", actorId=" + actorId +
                ", entityId=" + entityId +
                ", entityType=" + entityType +
                ", entityOwnerId=" + entityOwnerId +
                ", exts=" + exts +
                '}';
    }
}
