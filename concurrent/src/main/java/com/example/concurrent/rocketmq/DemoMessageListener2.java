package com.example.concurrent.rocketmq;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class DemoMessageListener2 implements MessageListener {

    @Override
    public Action consume(Message message, ConsumeContext context) {

        System.out.println("DemoMessageListener2接收到消息: " + new String(message.getBody()));
        try {
            //do something..
            return Action.CommitMessage;
        } catch (Exception e) {
            //消费失败
            return Action.ReconsumeLater;
        }
    }
}
