package com.teskinfly.utils.email;

import com.rabbitmq.client.Channel;
import com.teskinfly.domain.User;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender implements ChannelAwareMessageListener {
    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        User user = ((User) new Jackson2JsonMessageConverter().fromMessage(message));
        Properties properties = new Properties();
        properties.load(this.getClass().getResourceAsStream("/email/email.properties"));
        String from = properties.get("spring.mail.username")+"";
        String to = user.getUEmail();
        helper.setTo(to);
        helper.setFrom(from);
        helper.setSubject("虾虾订餐系统注册通知");
        helper.setText("欢迎用户"+user.getUName()+"注册虾虾订餐系统。");
        javaMailSender.send(mimeMessage);
    }
}
