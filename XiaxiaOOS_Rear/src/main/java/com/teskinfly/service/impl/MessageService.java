package com.teskinfly.service.impl;

import com.teskinfly.dao.IMessageDao;
import com.teskinfly.domain.Message;
import com.teskinfly.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MessageService implements IMessageService {
    @Autowired
    IMessageDao messageDao;

    @Override
    public List<Message> findAll() {
        return messageDao.findAll();
    }

    @Override
    public Message findById(Integer mId) {
        return messageDao.findById(mId);
    }

    @Override
    public void addMessage(Integer mUId, Integer mFId, String mComment) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Message message = new Message(mFId, mUId, mComment, format.format(new Date()));
        messageDao.addMessage(message);
    }

    @Override
    public void delMessage(Integer mId) {
        messageDao.delMessage(mId);
    }

}
