package com.tencent.entity;

import java.util.Date;

public class Notice {

    private Integer noticeId;

    private String noticeContent;

    private String noticeTitle;

    private Date noticeCreateTime;

    private Date noticeUpdateTime;

    private Integer noticeStatus;

    private Integer noticeOrder;

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public Date getNoticeCreateTime() {
        return noticeCreateTime;
    }

    public void setNoticeCreateTime(Date noticeCreateTime) {
        this.noticeCreateTime = noticeCreateTime;
    }

    public Date getNoticeUpdateTime() {
        return noticeUpdateTime;
    }

    public void setNoticeUpdateTime(Date noticeUpdateTime) {
        this.noticeUpdateTime = noticeUpdateTime;
    }

    public Integer getNoticeStatus() {
        return noticeStatus;
    }

    public void setNoticeStatus(Integer noticeStatus) {
        this.noticeStatus = noticeStatus;
    }

    public Integer getNoticeOrder() {
        return noticeOrder;
    }

    public void setNoticeOrder(Integer noticeOrder) {
        this.noticeOrder = noticeOrder;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "noticeId=" + noticeId +
                ", noticeContent='" + noticeContent + '\'' +
                ", noticeTitle='" + noticeTitle + '\'' +
                ", noticeCreateTime=" + noticeCreateTime +
                ", noticeUpdateTime=" + noticeUpdateTime +
                ", noticeStatus=" + noticeStatus +
                ", noticeOrder=" + noticeOrder +
                '}';
    }
}
