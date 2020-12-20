package com.example.concurrent.event;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author LuoLi
 * @version 1.0
 * @date 2020/12/18 19:02
 */

@Component
public class TomcatListenerDemo implements ApplicationListener<WebServerInitializedEvent> {

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        System.out.println("接受到tomcat启动事件");
    }

}
