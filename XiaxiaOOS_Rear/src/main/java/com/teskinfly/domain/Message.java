package com.teskinfly.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
//留言
//有时间再做
public class Message implements Serializable {
    Integer mId;
    Integer mFId;
    Integer mUId;
    String mComment;
    String mDate;
    Food food;
    User user;

    public Message() {

    }

    public Message(Integer mFId, Integer mUId, String mComment, String mDate) {
        this.mFId = mFId;
        this.mUId = mUId;
        this.mComment = mComment;
        this.mDate = mDate;
    }

    @Override
    public String toString() {
        return "Message{" +
                "mId=" + mId +
                ", mFId=" + mFId +
                ", mUId=" + mUId +
                ", mComment='" + mComment + '\'' +
                ", mDate='" + mDate + '\'' +
                ", food=" + food +
                '}';
    }
}
