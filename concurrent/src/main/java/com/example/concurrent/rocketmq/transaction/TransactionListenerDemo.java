package com.example.concurrent.rocketmq.transaction;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author LuoLi
 * @version 1.0
 * @date 2020/12/20 23:00
 */

@Component
public class TransactionListenerDemo implements MessageListener {

    @Override
    public Action consume(Message message, ConsumeContext context) {
        System.out.println("TransactionListenerDemo接收到消息: " + new String(message.getBody()));
        try {
            //do something..
            return Action.CommitMessage;
        } catch (Exception e) {
            //消费失败
            return Action.ReconsumeLater;
        }
    }


}
