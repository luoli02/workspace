package com.example.concurrent.rocketmq.transaction;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.bean.TransactionProducerBean;
import com.aliyun.openservices.ons.api.exception.ONSClientException;
import com.aliyun.openservices.ons.api.transaction.LocalTransactionExecuter;
import com.aliyun.openservices.ons.api.transaction.TransactionStatus;
import com.example.concurrent.rocketmq.MqConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LuoLi
 * @version 1.0
 * @date 2020/12/20 23:15
 */

@RestController
public class TransactionProducerController {

    @Autowired
    private TransactionProducerBean transactionProducerBean;
    @Autowired
    private MqConfig mqConfig;


    @GetMapping("transaction")
    public void send(){

        Message msg = new Message( //
                // Message所属的Topic
                mqConfig.getTopic(),
                // Message Tag 可理解为Gmail中的标签，对消息进行再归类，方便Consumer指定过滤条件在MQ服务器过滤
                mqConfig.getTag(),
                // Message Body 可以是任何二进制形式的数据， MQ不做任何干预
                // 需要Producer与Consumer协商好一致的序列化和反序列化方式
                "Hello MQ Transaction".getBytes());
        // 设置代表消息的业务关键属性，请尽可能全局唯一
        // 以方便您在无法正常收到消息情况下，可通过MQ 控制台查询消息并补发
        // 注意：不设置也不会影响消息正常收发
        msg.setKey("ORDERID_100");
        try {
            SendResult sendResult = transactionProducerBean.send(msg, new LocalTransactionExecuter() {
                @Override
                public TransactionStatus execute(Message msg, Object arg) {
                    System.out.println("执行本地事务");

                    //int i = 1/0;
                    if (false) {
                        //提交事务，对应的事务消息将投递给消费者
                        return TransactionStatus.CommitTransaction;
                    } else {
                        //未知状态，一般在用户无法确定事务是成功还是失败时使用，对于未知状态的事务，服务端会定期进行事务回查
                        return TransactionStatus.RollbackTransaction;
                    }
                }
            }, null);
            System.out.println(sendResult);
        } catch (ONSClientException e) {
            e.printStackTrace();
            System.out.println("发送失败");
            //出现异常意味着发送失败，为了避免消息丢失，建议缓存该消息然后进行重试。
        }



    }


}


















