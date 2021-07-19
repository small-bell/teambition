package com.smallbell.teambition.rtmp.entity;

import java.io.Serializable;
import java.util.Calendar;

public class MsgEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String creator;
    private String msgBody;
    private Calendar sTime;

    public String getCreator() {
        return creator;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public Calendar getsTime() {
        return sTime;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    public void setsTime(Calendar sTime) {
        this.sTime = sTime;
    }

    @Override
    public String toString() {
        return "MsgEntity{" +
                "creator='" + creator + '\'' +
                ", msgBody='" + msgBody + '\'' +
                ", sTime=" + sTime +
                '}';
    }
}
