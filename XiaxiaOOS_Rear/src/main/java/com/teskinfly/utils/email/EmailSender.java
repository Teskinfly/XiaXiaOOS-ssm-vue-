package com.teskinfly.utils.email;

import com.rabbitmq.client.Channel;
import com.teskinfly.domain.User;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

public class EmailSender implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        System.out.println("接收到了message----");
        User user = ((User) new Jackson2JsonMessageConverter().fromMessage(message));
        System.out.println(user);
    }
}
