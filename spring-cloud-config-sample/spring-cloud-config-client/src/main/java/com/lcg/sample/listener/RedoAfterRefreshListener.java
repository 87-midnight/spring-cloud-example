package com.lcg.sample.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * <p>可以自定义扩展该事件，达到发布bus event机制,通知其他微服务干什么事情</p>
 * <p>需要配合@RemoteApplicationEventScan注解才能生效</p>
 * @author AnnieCattice
 * @create 2019-04-21 02:03
 */
@Component
@Slf4j
@RefreshScope // allow refresh single been properties
public class RedoAfterRefreshListener implements ApplicationListener<RemoteApplicationEvent> {

    @Override
    public void onApplicationEvent(RemoteApplicationEvent remoteApplicationEvent) {
        //这里可以干很多事情，当属性变更后，自定义处理逻辑
        log.info("I was been executed after bus event publish...");
    }
}
