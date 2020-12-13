package com.example.concurrent.rocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LuoLi
 * @version 1.0
 * @date 2020/12/9 19:30
 */

@RestController
public class RocketController {

    @Autowired
    private RocketmqProducerService service;

    @GetMapping("send/rocketmq")
    public void send() {
        service.sendMsg("2020-12-09 19:30 发送消息");
    }

}
