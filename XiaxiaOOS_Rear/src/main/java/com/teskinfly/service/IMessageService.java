package com.teskinfly.service;

import com.teskinfly.domain.Message;

import java.util.List;

public interface IMessageService {
    List<Message> findAll();

    Message findById(Integer mId);

    void addMessage(Integer mUId, Integer mFU, String mComment);

    void delMessage(Integer mId);
}
